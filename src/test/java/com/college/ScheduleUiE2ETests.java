package com.college;

import static org.assertj.core.api.Assertions.assertThat;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.net.URI;
import java.time.Instant;
import java.util.function.BooleanSupplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScheduleUiE2ETests {

    private static final String BASE_URL_PROPERTY = "e2e.base-url";
    private static final String BASE_URL_ENV = "E2E_BASE_URL";
    private static final int WAIT_TIMEOUT_MILLIS = 15000;
    private static final int POLL_INTERVAL_MILLIS = 250;

    private String baseUrl;
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    @BeforeEach
    void setUp() {
        baseUrl = resolveBaseUrl();
        Assumptions.assumeTrue(baseUrl != null && !baseUrl.isBlank(),
            "Set e2e.base-url or E2E_BASE_URL to run E2E UI tests.");

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(true)
            .setArgs(java.util.List.of("--disable-dev-shm-usage", "--no-sandbox")));
        browserContext = browser.newContext(new Browser.NewContextOptions()
            .setViewportSize(1440, 1200));
        page = browserContext.newPage();
    }

    @AfterEach
    void tearDown() {
        if (browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @Test
    void homePageDisplaysScheduleAndAddLink() {
        page.navigate(baseUrl + "/");

        assertThat(page.locator("h1").textContent()).isNotBlank();
        assertThat(page.locator("a.btn.btn-success").count()).isEqualTo(1);
        assertThat(page.locator("table").count()).isEqualTo(1);
        assertThat(page.locator("a.btn.btn-success").getAttribute("href")).isEqualTo("/add");
    }

    @Test
    void addScheduleFlowCreatesAndDeletesEntry() {
        String uniqueSuffix = String.valueOf(Instant.now().toEpochMilli());
        String uniqueCourseName = "E2E Course " + uniqueSuffix;
        String uniqueStudentName = "E2E Student " + uniqueSuffix;

        try {
            page.navigate(baseUrl + "/add");
            assertThat(page.locator("form").count()).isEqualTo(1);

            setInputValue("studentFirstName", uniqueStudentName);
            setInputValue("studentLastName", "Tester");
            setInputValue("teacherFirstName", "UI");
            setInputValue("teacherLastName", "Teacher");
            setInputValue("courseName", uniqueCourseName);
            setInputValue("departmentName", "QA");
            setInputValue("roomNumber", "301");
            setInputValue("semester", "Spring");
            setInputValue("year", "2026");
            setInputValue("startTime", "09:00:00");
            setInputValue("endTime", "10:30:00");

            page.locator("button[type='submit']").click();
            page.waitForURL(baseUrl + "/");
            waitFor(() -> page.locator("body").textContent().contains(uniqueCourseName),
                "Created schedule row did not appear on the main page.");

            assertThat(page.locator("body").textContent())
                .contains(uniqueStudentName)
                .contains(uniqueCourseName);
        } finally {
            deleteScheduleRow(uniqueCourseName);
        }
    }

    private void deleteScheduleRow(String uniqueCourseName) {
        page.navigate(baseUrl + "/");

        Locator rowToDelete = findRowContaining(uniqueCourseName);
        if (rowToDelete == null) {
            return;
        }

        rowToDelete.locator("button[type='submit']").click();
        page.waitForURL(baseUrl + "/");
        waitFor(() -> !page.locator("body").textContent().contains(uniqueCourseName),
            "Deleted schedule row is still visible on the main page.");

        assertThat(page.locator("body").textContent()).doesNotContain(uniqueCourseName);
    }

    private Locator findRowContaining(String text) {
        Locator rows = page.locator("tbody tr");
        int count = rows.count();
        for (int index = 0; index < count; index++) {
            Locator row = rows.nth(index);
            if (row.textContent().contains(text)) {
                return row;
            }
        }
        return null;
    }

    private void setInputValue(String fieldName, String value) {
        page.locator("[name='" + fieldName + "']").fill(value);
    }

    private void waitFor(BooleanSupplier condition, String failureMessage) {
        long deadline = System.currentTimeMillis() + WAIT_TIMEOUT_MILLIS;
        while (System.currentTimeMillis() < deadline) {
            if (condition.getAsBoolean()) {
                return;
            }
            page.waitForTimeout(POLL_INTERVAL_MILLIS);
        }
        throw new AssertionError(failureMessage);
    }

    private String resolveBaseUrl() {
        String configuredUrl = System.getProperty(BASE_URL_PROPERTY);
        if (configuredUrl == null || configuredUrl.isBlank()) {
            configuredUrl = System.getenv(BASE_URL_ENV);
        }

        if (configuredUrl == null || configuredUrl.isBlank()) {
            return null;
        }

        String normalizedUrl = configuredUrl.endsWith("/")
            ? configuredUrl.substring(0, configuredUrl.length() - 1)
            : configuredUrl;

        URI uri = URI.create(normalizedUrl);
        Assumptions.assumeTrue(uri.getScheme() != null && uri.getHost() != null,
            "E2E base URL must be an absolute URL, for example https://example.onrender.com");

        return normalizedUrl;
    }
}
