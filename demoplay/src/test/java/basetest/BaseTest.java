package basetest;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.AfterMethod;
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
        page = pf.initBrowser();

        page.navigate(config.get("url"));

        LoginPage login = new LoginPage(page);

        login.login(
            config.get("Username"),
            config.get("Password"));

    }

    @AfterMethod
    public void tearDown() {
        pf.closeBrowser();
    }
}