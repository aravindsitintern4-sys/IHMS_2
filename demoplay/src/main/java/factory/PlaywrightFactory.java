package factory;

import java.util.Arrays;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    public Page initBrowser(String browserName) {

        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {

        case "chromium":
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setSlowMo(500)
                            .setArgs(Arrays.asList("--start-maximized")));
            break;

        case "chrome":
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setChannel("chrome")
                            .setHeadless(false)
                            .setSlowMo(500)
                            .setArgs(Arrays.asList("--start-maximized")));
            break;

        case "edge":
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setChannel("msedge")
                            .setHeadless(false)
                            .setSlowMo(500)
                            .setArgs(Arrays.asList("--start-maximized")));
            break;

        case "firefox":
            browser = playwright.firefox().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setSlowMo(500));
            break;

        default:
            throw new IllegalArgumentException(
                    "Invalid browser name: " + browserName);
        }

        // Create Browser Context
        if (browserName.equalsIgnoreCase("firefox")) {
            context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setViewportSize(1920, 1080));
        } else {
            context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setViewportSize(null));
        }

        // Create Page
        page = context.newPage();

        return page;
    }

    public void closeBrowser() {

        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}          