package tests;

// import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import basetest.BaseTest;
import pages.DashboardPage;
import utils.ReusableCode;
// import pages.LoginPage;

public class LoginTest extends BaseTest {


     // POSITIVE SCENARIO
    // @Test
    // public void validLoginTest() {

    //     LoginPage login = new LoginPage(page);

    //     login.login(
    //         config.get("Username"),
    //         config.get("Password")
    //     );

    //     DashboardPage dashboard = new DashboardPage(page);

    //     dashboard.clickButton("IHMS");
    // }

    // // IHMS OPTION CLICK
    // @Test(priority = 1)
    // public void IHMSOptionClick() {
    //     DashboardPage dashboard = new DashboardPage(page);

    //     dashboard.clickDashboardOption("IHMS");
    // }

    // // EMR OPTION CLICK
    //  @Test(priority = 2)
    // public void EMROptionClick() {
    //     DashboardPage dashboard = new DashboardPage(page);

    //     dashboard.clickDashboardOption("EMR");
    // }

//    @Test
//     public void MainMenuOption() {

//         //  MAIN DASHBOARD IHMS OPTION SELECTION
//         DashboardPage dashboard = new DashboardPage(page);

//         //  USE LAMDA FUNCTION ---> BECAUSE IT'S OPENS NEW TAB AFTER THIS TEST ACTION
//         Page ihmsPage = page.waitForPopup(() -> {
//             dashboard.clickDashboardOption("IHMS");
//         });
                
//         // MAINMENU AND SUBMENU SELECTION , AND CALL THE NEW OPENED TAB 
//         ReusableCode ihmsDashboard = new ReusableCode(ihmsPage);

//          //WITHOUT USING CONFIG
//         ihmsDashboard.clickMenuAndSelectSubMenu(
//                 "OP Modules",
//                 "Outpatient Registration");
//     } 







}