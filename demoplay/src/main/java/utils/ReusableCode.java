package utils;

import org.testng.Assert;
import com.microsoft.playwright.Page;
import locators.Locator;

public class ReusableCode {

    private Page page;

    public ReusableCode(Page page) {
        this.page = page;
    }

    // TOAST MESSAGE VALIDATION
    public void validateToastMessage(String expectedToast) {

        page.locator(Locator.TOAST_MESSAGE).waitFor();

        String actualToast = page.locator(Locator.TOAST_MESSAGE)
                                 .textContent()
                                 .trim();

        Assert.assertEquals(
                actualToast,
                expectedToast,
                "Toast message validation failed");
    }

    public String getToastMessage() {

        page.locator(Locator.TOAST_MESSAGE).waitFor();

        return page.locator(Locator.TOAST_MESSAGE)
                .textContent()
                .trim();
    }

    //  MAIN MENU AND SUB MENU SELECTION IN IHMS DASHBOARD  (OP Modules -----> Outpatient Registration)
    public void clickMenuAndSelectSubMenu(String mainMenu, String subMenu) {

        String mainMenuOption ="//span[normalize-space()='%s']";

        String subMenuOption ="//a[normalize-space()='%s']";

        String mainLocator =String.format(mainMenuOption, mainMenu);

        String subLocator =String.format(subMenuOption, subMenu);

        page.locator(mainLocator).hover();
        page.locator(subLocator).click();
    }


}