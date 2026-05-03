package com.college;

import static org.assertj.core.api.Assertions.assertThat;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ScreenshotType;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.function.BooleanSupplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScheduleHappyPathE2ETests {

    private static final String BASE_URL_PROPERTY = "e2e.base-url";
    private static final String BASE_URL_ENV = "E2E_BASE_URL";
    private static final String SELENIUM_CDP_URL_PROPERTY = "e2e.selenium-cdp-url";
    private static final String SELENIUM_CDP_URL_ENV = "E2E_SELENIUM_CDP_URL";
    private static final String ARTIFACTS_DIR_PROPERTY = "e2e.artifacts-dir";
    private static final String ARTIFACTS_DIR_ENV = "E2E_ARTIFACTS_DIR";
    private static final String PAGE_READY_TIMEOUT_MILLIS_PROPERTY = "e2e.page-ready-timeout-millis";
    private static final String PAGE_READY_TIMEOUT_MILLIS_ENV = "E2E_PAGE_READY_TIMEOUT_MILLIS";
    private static final String SCHEDULE_PAGE_TITLE = "\u0420\u043e\u0437\u043a\u043b\u0430\u0434 \u043a\u043e\u043b\u0435\u0434\u0436\u0443";
    private static final String RENDER_HOST_SUFFIX = ".onrender.com";
    private static final int WAIT_TIMEOUT_MILLIS = 15000;
    private static final int POLL_INTERVAL_MILLIS = 250;
    private static final int DEFAULT_PAGE_READY_TIMEOUT_MILLIS = 180000;
    private static final int DEFAULT_TIMEOUT_MILLIS = 30000;
    private static final int RENDER_TIMEOUT_MILLIS = 180000;
    private static final int VIEWPORT_WIDTH = 1440;
    private static final int VIEWPORT_HEIGHT = 1200;
    private static final int VIDEO_WIDTH = 1280;
    private static final int VIDEO_HEIGHT = 720;

    private String baseUrl;
    private String seleniumCdpUrl;
    private int pageReadyTimeoutMillis;
    private Path artifactsDir;
    private Path videosDir;
    private Path screenshotsDir;
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private String createdCourseName;

    @BeforeEach
    void setUp() {
        baseUrl = resolveBaseUrl();
        Assumptions.assumeTrue(baseUrl != null && !baseUrl.isBlank(),
            "Set e2e.base-url or E2E_BASE_URL to run E2E UI tests.");
        seleniumCdpUrl = resolveOptionalValue(SELENIUM_CDP_URL_PROPERTY, SELENIUM_CDP_URL_ENV);
        pageReadyTimeoutMillis = resolvePageReadyTimeoutMillis();
        artifactsDir = resolveArtifactsDir();
        videosDir = artifactsDir.resolve("videos");
        screenshotsDir = artifactsDir.resolve("screenshots");
        createDirectory(videosDir);
        createDirectory(screenshotsDir);

        playwright = Playwright.create();
        browser = createBrowser();
        browserContext = browser.newContext(new Browser.NewContextOptions()
            .setRecordVideoDir(videosDir)
            .setRecordVideoSize(VIDEO_WIDTH, VIDEO_HEIGHT)
            .setViewportSize(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
        page = browserContext.newPage();
        page.setDefaultTimeout(resolvePlaywrightTimeoutMillis());
        page.setDefaultNavigationTimeout(resolvePlaywrightTimeoutMillis());
    }

    @AfterEach
    void tearDown() {
        if (page != null && createdCourseName != null) {
            try {
                deleteScheduleRow(createdCourseName);
            } catch (Exception exception) {
                // Preserve the original test failure; cleanup is best effort.
            } finally {
                createdCourseName = null;
            }
        }

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
    void scheduleHappyPathCreatesAndDeletesEntry() {
        runWithDiagnostics("schedule-happy-path-creates-and-deletes-entry", () -> {
            String uniqueSuffix = String.valueOf(Instant.now().toEpochMilli());
            String uniqueCourseName = "E2E Course " + uniqueSuffix;
            String uniqueStudentName = "E2E Student " + uniqueSuffix;
            createdCourseName = uniqueCourseName;

            page.navigate(baseUrl + "/add");
            waitForAddPageForm();
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
            waitForSchedulePageHeader();

            Locator createdRow = waitForRowContaining(uniqueCourseName,
                "Created schedule row did not appear on the main page.");
            assertThat(createdRow.textContent())
                .contains(uniqueStudentName)
                .contains(uniqueCourseName);

            deleteScheduleRow(uniqueCourseName);
            createdCourseName = null;
        });
    }

    private void deleteScheduleRow(String uniqueCourseName) {
        page.navigate(baseUrl + "/");
        waitForSchedulePageHeader();

        Locator rowToDelete = findRowContaining(uniqueCourseName);
        if (rowToDelete == null) {
            return;
        }

        rowToDelete.locator("button[type='submit']").click();
        page.waitForURL(baseUrl + "/");
        waitForSchedulePageHeader();
        waitFor(() -> findRowContaining(uniqueCourseName) == null,
            "Deleted schedule row is still visible on the main page.");

        assertThat(page.locator("body").textContent()).doesNotContain(uniqueCourseName);
    }

    private Locator waitForRowContaining(String text, String failureMessage) {
        waitFor(() -> findRowContaining(text) != null, failureMessage);
        return findRowContaining(text);
    }

    private Locator findRowContaining(String text) {
        Locator rows = page.locator("tbody tr");
        int count = rows.count();
        for (int index = 0; index < count; index++) {
            Locator row = rows.nth(index);
            String rowText = row.textContent();
            if (rowText != null && rowText.contains(text)) {
                return row;
            }
        }
        return null;
    }

    private void setInputValue(String fieldName, String value) {
        page.locator("[name='" + fieldName + "']").fill(value);
    }

    private void waitForSchedulePageHeader() {
        page.locator("h1")
            .filter(new Locator.FilterOptions().setHasText(SCHEDULE_PAGE_TITLE))
            .first()
            .waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout((double) pageReadyTimeoutMillis));
    }

    private void waitForAddPageForm() {
        page.locator("form")
            .first()
            .waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout((double) pageReadyTimeoutMillis));
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
        String configuredUrl = resolveOptionalValue(BASE_URL_PROPERTY, BASE_URL_ENV);
        if (configuredUrl == null || configuredUrl.isBlank()) {
            return null;
        }

        return normalizeBaseUrl(configuredUrl);
    }

    private String normalizeBaseUrl(String configuredUrl) {
        String normalizedUrl = configuredUrl.endsWith("/")
            ? configuredUrl.substring(0, configuredUrl.length() - 1)
            : configuredUrl;

        URI uri = URI.create(normalizedUrl);
        if (uri.getScheme() == null || uri.getHost() == null) {
            throw new IllegalStateException(
                "E2E base URL must be an absolute URL, for example https://example.onrender.com"
            );
        }

        return normalizedUrl;
    }

    private String resolveOptionalValue(String propertyName, String envName) {
        String configuredValue = System.getProperty(propertyName);
        if (configuredValue == null || configuredValue.isBlank()) {
            configuredValue = System.getenv(envName);
        }
        return configuredValue;
    }

    private Browser createBrowser() {
        if (seleniumCdpUrl != null && !seleniumCdpUrl.isBlank()) {
            return playwright.chromium().connectOverCDP(seleniumCdpUrl);
        }

        return playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(true)
            .setArgs(List.of("--disable-dev-shm-usage", "--no-sandbox")));
    }

    private Path resolveArtifactsDir() {
        String configuredArtifactsDir = resolveOptionalValue(ARTIFACTS_DIR_PROPERTY, ARTIFACTS_DIR_ENV);
        if (configuredArtifactsDir == null || configuredArtifactsDir.isBlank()) {
            configuredArtifactsDir = "target/e2e-artifacts";
        }
        return Paths.get(configuredArtifactsDir).toAbsolutePath().normalize();
    }

    private int resolvePageReadyTimeoutMillis() {
        String configuredTimeout = resolveOptionalValue(
            PAGE_READY_TIMEOUT_MILLIS_PROPERTY,
            PAGE_READY_TIMEOUT_MILLIS_ENV
        );
        if (configuredTimeout == null || configuredTimeout.isBlank()) {
            return isRenderBaseUrl() ? RENDER_TIMEOUT_MILLIS : DEFAULT_PAGE_READY_TIMEOUT_MILLIS;
        }
        return Integer.parseInt(configuredTimeout);
    }

    private double resolvePlaywrightTimeoutMillis() {
        return isRenderBaseUrl() ? RENDER_TIMEOUT_MILLIS : DEFAULT_TIMEOUT_MILLIS;
    }

    private boolean isRenderBaseUrl() {
        if (baseUrl == null || baseUrl.isBlank()) {
            return false;
        }

        URI uri = URI.create(baseUrl);
        String host = uri.getHost();
        return host != null && host.endsWith(RENDER_HOST_SUFFIX);
    }

    private void createDirectory(Path directory) {
        try {
            Files.createDirectories(directory);
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to create E2E artifacts directory " + directory, exception);
        }
    }

    private void runWithDiagnostics(String testName, CheckedRunnable testBody) {
        try {
            testBody.run();
        } catch (Throwable throwable) {
            captureFailureScreenshot(testName);
            rethrowUnchecked(throwable);
        }
    }

    private void captureFailureScreenshot(String testName) {
        if (page == null) {
            return;
        }

        Path screenshotPath = screenshotsDir.resolve(buildArtifactFileName(testName, "png"));
        page.screenshot(new Page.ScreenshotOptions()
            .setPath(screenshotPath)
            .setFullPage(true)
            .setType(ScreenshotType.PNG));
    }

    private String buildArtifactFileName(String testName, String extension) {
        return sanitizeFileName(testName) + "-" + Instant.now().toEpochMilli() + "." + extension;
    }

    private String sanitizeFileName(String value) {
        return value.replaceAll("[^a-zA-Z0-9-_]+", "-");
    }

    private void rethrowUnchecked(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        }
        if (throwable instanceof Error) {
            throw (Error) throwable;
        }
        throw new IllegalStateException(throwable);
    }

    @FunctionalInterface
    private interface CheckedRunnable {
        void run() throws Exception;
    }
}
