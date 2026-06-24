package utils;

import org.testng.Assert;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import locators.Locator;

public class ReusableCode {

    private Page page;

    public ReusableCode(Page page) {
        this.page = page;
    } 

    // TOAST MESSAGE VALIDATION
    public void validateToastMessage(String expectedToast) {
        page.locator(Locator.toastMsg).waitFor();
        String actualToast = page.locator(Locator.toastMsg).textContent().trim();
        Assert.assertEquals(actualToast,expectedToast,"Toast message validation failed");
    }

    public String getToastMessage() {
        page.locator(Locator.toastMsg).waitFor();
        return page.locator(Locator.toastMsg).textContent().trim();
    }

    //  MAIN MENU AND SUB MENU SELECTION IN IHMS DASHBOARD  (e.g,. OP Modules -----> Outpatient Registration)
    public void clickMenuAndSelectSubMenu(String mainMenu, String subMenu) {

        String mainMenuOption ="//span[normalize-space()='%s']";

        String subMenuOption ="//a[normalize-space()='%s']";

        String mainLocator =String.format(mainMenuOption, mainMenu);

        String subLocator =String.format(subMenuOption, subMenu);

        // page.locator(mainLocator).click();
        page.locator(mainLocator).hover();
        page.locator(subLocator).click();
    }

    // BUTTON CLICK
    public void buttonClick(String btnName) {
        String buttonClick = "//button[normalize-space()='%s']";
        page.click(String.format(buttonClick,btnName));
    } 

    // DROPDOWN 
    public void selectDropdownByLabel(String labelName, String option) {
        String droploc = String.format(Locator.dropdownXpath,labelName);
        page.locator(droploc).selectOption(new SelectOption().setLabel(option));
    }
    
    // INPUT FIELD
    public void inputFieldByLabel(String labelName, String inputValue) {
        String inputLoc = String.format(Locator.inputField,labelName);
        page.locator(inputLoc).fill(inputValue);
    }

    // RADIO BUTTON
    public void selectRadioByLabel(String labelName, String option) {
        String radioLoc = String.format(Locator.radioBtn,labelName,option);
        page.locator(radioLoc).check();
    }

    // CHECK BOX 
    public void checkboxByLabel(String labelName) {
        String checkBoxLoc = String.format(Locator.checkbox,labelName);
        page.locator(checkBoxLoc).check();
    }


}