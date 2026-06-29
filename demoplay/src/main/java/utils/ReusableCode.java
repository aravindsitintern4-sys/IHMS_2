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
        String dropdownXpath ="//label[contains(normalize-space(),'%s')]/parent::*//select";
        String dropLoc = String.format(dropdownXpath, labelName);
        page.locator(dropLoc).selectOption(new SelectOption().setLabel(option));
    }
    
    // INPUT FIELD
    public void inputFieldByLabel(String labelName, String inputValue) {
        String inputField ="//label[contains(normalize-space(),'%s')]/following::input[1]";
        String inputLoc = String.format(inputField, labelName);
        page.locator(inputLoc).click();
        page.locator(inputLoc).clear();
        page.locator(inputLoc).pressSequentially(inputValue);
    }    


    // RADIO BUTTON
    public void selectRadioByLabel(String labelName, String option) {
        String radioBtn ="//label[contains(normalize-space(),'%s')]/following-sibling::div//label[contains(normalize-space(),'%s')]/input";
        String radioLoc = String.format(radioBtn,labelName,option);
        page.locator(radioLoc).check();
    }
      
    // CHECK BOX 
    public void checkboxByLabel(String labelName) {
        String checkbox = "//label[contains(normalize-space(),'%s')]/preceding-sibling::input[@type='checkbox']";
        String checkBoxLoc = String.format(checkbox,labelName);
        page.locator(checkBoxLoc).check();
    }
 
    // INPUT FIELD
    public void textAreaFieldByLabel(String labelName, String inputValue) {
        String textAreaField =  "//label[contains(normalize-space(),'%s')]/following::textarea[1]";
        String textareainputLoc = String.format(textAreaField,labelName);
        page.locator(textareainputLoc).fill(inputValue);
    }

    // USING INPUT SEARCHABLE OPTION IN DROPDOWN
    public void selectSearchableDropdownByLabel(String labelName, String option) {
        String dropdownClickLoc ="//label[contains(normalize-space(),'%s')]/following::app-single-select-with-search[1]//div[contains(@class,'cursor-pointer')]";
        String optionInput ="//input[@placeholder='Search...']";
        String optionSelect ="//div[normalize-space()='%s']";

        page.locator(String.format(dropdownClickLoc, labelName)).click();
        page.locator(optionInput).pressSequentially(option);
        page.locator(String.format(optionSelect, option)).last().click();
    }  
    

}