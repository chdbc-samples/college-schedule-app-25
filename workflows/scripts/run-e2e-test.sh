#!/usr/bin/env bash
set -euo pipefail

# Prepare directories used by Maven logs and Playwright artifacts.
mkdir -p target/ci-logs target/e2e-artifacts/videos target/e2e-artifacts/screenshots

# Wait until the Selenium service container is ready to accept sessions.
for attempt in {1..30}; do
  ready="$(curl --silent http://127.0.0.1:4444/status | jq -r '.value.ready // false')"
  if [[ "$ready" == "true" ]]; then
    break
  fi
  sleep 2
done

if [[ "${ready:-false}" != "true" ]]; then
  echo "Selenium Grid did not become ready in time." >&2
  exit 1
fi

# Open a Selenium Chromium session and extract the CDP endpoint that Playwright will use.
response="$(
  curl --silent --show-error --fail-with-body \
    --request POST \
    --url http://127.0.0.1:4444/session \
    --header "Content-Type: application/json" \
    --data '{
      "capabilities": {
        "alwaysMatch": {
          "browserName": "chrome",
          "goog:chromeOptions": {
            "args": ["--headless=new", "--disable-dev-shm-usage", "--no-sandbox"]
          }
        }
      }
    }'
)"

SESSION_ID="$(jq -r '.value.sessionId // empty' <<<"$response")"
CDP_URL="$(jq -r '.value.capabilities["se:cdp"] // empty' <<<"$response")"

if [[ -z "$SESSION_ID" || -z "$CDP_URL" ]]; then
  echo "Unable to resolve Selenium session metadata." >&2
  echo "$response" >&2
  exit 1
fi

cleanup() {
  if [[ -n "${SESSION_ID:-}" ]]; then
    curl --silent --show-error --fail-with-body \
      --request DELETE \
      --url "http://127.0.0.1:4444/session/${SESSION_ID}" >/dev/null || true
  fi
}

trap cleanup EXIT

# Rewrite the Selenium-reported CDP URL to the runner-visible localhost endpoint.
CDP_PATH="${CDP_URL#ws://*/}"
export E2E_SELENIUM_CDP_URL="ws://127.0.0.1:4444/${CDP_PATH}"
export E2E_ARTIFACTS_DIR="${E2E_ARTIFACTS_DIR:-target/e2e-artifacts}"

set -o pipefail
mvn -B -P e2e-ui -Dcheckstyle.skip=true -DskipUnitTests=true -DskipIntegrationTests=true verify \
  | tee target/ci-logs/e2e-ui-tests.log
