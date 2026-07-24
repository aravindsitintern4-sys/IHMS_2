// package basetest;

// import java.util.Map;

// import org.testng.annotations.AfterMethod;
// import org.testng.annotations.BeforeMethod;

// import com.microsoft.playwright.Page;

// import factory.PlaywrightFactory;
// import pages.BillingPage;
// import pages.DashboardPage;
// import pages.LoginPage;
// import pages.OPRegistrationPage;
// import tests.TestContext;
// import utils.ConfigReader;
// import utils.DropdownReader;
// import utils.Excel;
// import utils.ReusableCode;

// public class BaseTest {

//     protected Page page;
//     protected PlaywrightFactory pf;
//     protected ConfigReader config;

//     @BeforeMethod
//     public void setup() {

//         config = new ConfigReader();

//         pf = new PlaywrightFactory();

//         // READ BROWSER
//         String browserName = config.get("browser");

//         // LAUNCH BROWSER
//         page = pf.initBrowser(browserName);

//         // APPLICATION URL OPEN
//         page.navigate(config.get("url"));

//         // LOGIN
//         LoginPage login = new LoginPage(page);

//         login.login(
//                 config.get("Username"),
//                 config.get("Password"));
//     }

//     @AfterMethod
//     public void tearDown() {

//         if (pf != null) {
//             pf.closeBrowser();
//         }
//     }
// }









package basetest;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

import factory.PlaywrightFactory;
import pages.BillingPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OPRegistrationPage;
import tests.TestContext;
import utils.ConfigReader;
import utils.DropdownReader;
import utils.Excel;
import utils.ReusableCode;

public class BaseTest {

    protected Page page;
    protected PlaywrightFactory pf;
    protected ConfigReader config;

    @BeforeMethod
    public void setup() {

        config = new ConfigReader();
        pf = new PlaywrightFactory();

        String browserName = config.get("browser");

        page = pf.initBrowser(browserName);

        page.navigate(config.get("url"));

        LoginPage login = new LoginPage(page);
        login.login(
                config.get("Username"),
                config.get("Password"));

        page.waitForLoadState();

        System.out.println("Login Successful");
    }

    // COMMON INITIAL METHOD
    public TestContext CallInitialMethods(String mainMenu,String subMenu,String sheetName,int column) {

        Map<String, String> data = Excel.getTestData(sheetName, column);

        DashboardPage dashboard = new DashboardPage(page);

        Page ihmsPage;

        try {
            // If IHMS OPENS IN NEW TAB
            ihmsPage = page.waitForPopup(() -> {
                dashboard.clickDashboardOption("IHMS");
            });
        } catch (PlaywrightException e) {
            // If IHMS OPENS IN SAME TAB
            dashboard.clickDashboardOption("IHMS");
            page.waitForLoadState();
            ihmsPage = page;
        }

        ihmsPage.waitForLoadState();

        ReusableCode reusableAction = new ReusableCode(ihmsPage);

        reusableAction.clickMenuAndSelectSubMenu(mainMenu, subMenu);

        //  DYNAMIC FLOW FOR CHECKING DROPDOWN
        waitForDropdownIfPresent(ihmsPage);

        DropdownReader dropdownReader = new DropdownReader(ihmsPage);

        OPRegistrationPage opPage = new OPRegistrationPage(ihmsPage);

        BillingPage billPage = new BillingPage(ihmsPage);

        return new TestContext(
                reusableAction,
                dropdownReader,
                opPage,
                billPage,
                data,
                column);
    }

    @AfterMethod
    public void tearDown() {

        if (pf != null) {
            pf.closeBrowser();
        }
    }



    // WAIT FOR SELECT DROPDOWN WHEN PAGE OPEN
    private void waitForDropdownIfPresent(Page ihmsPage) {
        Locator selects = ihmsPage.locator("//select");
        try {
            selects.first().waitFor(new Locator.WaitForOptions().setTimeout(5000));
        } catch (PlaywrightException e) {
            System.out.println("No <select> dropdown found on this page - continuing without wait.");
        }
    }


    
}





