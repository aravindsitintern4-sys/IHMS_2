package factory;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    public Page initBrowser(String browserName) {

        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {

        case "chromium":
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setSlowMo(1000));
            break;

        case "chrome":
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setChannel("chrome")
                            .setHeadless(false)
                            .setSlowMo(1000));
            break;

        case "edge":
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setChannel("msedge")
                            .setHeadless(false)
                            .setSlowMo(1000));
            break;

        case "firefox":
            browser = playwright.firefox().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setSlowMo(1000));
            break;

        default:
            throw new IllegalArgumentException(
                    "Invalid browser name: " + browserName);
        }

        context = browser.newContext();
        page = context.newPage();

        return page;
    }

    public void closeBrowser() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}