package basetest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Page;

import factory.PlaywrightFactory;
import pages.LoginPage;
import utils.ConfigReader;

public class BaseTest {

    protected Page page;
    protected PlaywrightFactory pf;
    protected ConfigReader config;

    @BeforeMethod
    public void setup() {

        config = new ConfigReader();

        pf = new PlaywrightFactory();

        // READ BROWSER
        String browserName = config.get("browser");

        // LAUNCH BROWSER
        page = pf.initBrowser(browserName);

        // APPLICATION URL OPEN
        page.navigate(config.get("url"));

        // LOGIN
        LoginPage login = new LoginPage(page);

        login.login(
                config.get("Username"),
                config.get("Password"));
    }

    @AfterMethod
    public void tearDown() {

        if (pf != null) {
            pf.closeBrowser();
        }
    }
}