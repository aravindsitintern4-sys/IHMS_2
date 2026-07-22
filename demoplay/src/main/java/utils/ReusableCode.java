package utils;


import java.util.List;

import org.testng.Assert;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import com.microsoft.playwright.Locator.ClickOptions;


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
        String mainMenuOption ="//div[normalize-space()='%s']";
        String subMenuOption ="//a[normalize-space()='%s']";

        String mainLocator =String.format(mainMenuOption, mainMenu);
        String subLocator =String.format(subMenuOption, subMenu);

        page.locator(mainLocator).click();
        page.locator(subLocator).click();
    }

    // BUTTON CLICK
    public void buttonClick(String btnName) {
        String buttonClick = "//button[normalize-space()='%s']";
        page.click(String.format(buttonClick,btnName));
        // String buttonClick =
        //         "//button[normalize-space()='%s'] | " +
        //         "//button[.//span[normalize-space(.)='%s']]";

        //     page.locator(String.format(buttonClick, btnName, btnName)).click();
    } 


    // DROPDOWN WITH LABEL
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
        // page.locator(inputLoc).fill(inputValue);
        // page.locator(inputLoc).pressSequentially(inputValue);    
        page.keyboard().type(inputValue, new Keyboard.TypeOptions().setDelay(50));
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

    // DROPDOWN WITHOUT LABEL
    public void selectDropdownWithoutLabel(String heading, String option) {
        String dropdownXpath ="//*[normalize-space()='%s']/following::select[1]";
        page.locator(String.format(dropdownXpath, heading)).selectOption(new SelectOption().setLabel(option));
    }
    

    // CLOSE (X) ICON
    public void closeIcon(String heading) {   
        String closeIcon ="//*[name()='svg' and @class='lucide'] | " + "//*[name()='svg' and contains(@class,'absolute')]";;
        page.locator(String.format(closeIcon, heading)).click();
    }

    
    // HOME ICON
    public void homeIcon() {   
        String homeIcon ="//lucide-icon[contains(@class,'cursor-pointer')]"; 
        page.locator(String.format(homeIcon)).click();
    }

  

    // PLUS (+) ICON
    public void linkIcon(String icon) {   
        String iconLink ="//a[normalize-space()='%s']";
        page.locator(String.format(iconLink, icon)).click();
    }

    // PAYMENT TYPE OTHERS TEST PURPOSE
    public void testClick(String btn) {
        String buttonXpath = "//h3[normalize-space()='Payment Methods']/following-sibling::div//button[.//span[normalize-space()='%s']]";
        page.locator(String.format(buttonXpath, btn))
            .click(new ClickOptions().setForce(true));
    }

    //  STORING PURPOSE OF UIN,MRN
    public String getPopupValue(String label) {
        String xpath ="//label[normalize-space()='%s']/following-sibling::div";
        return page.locator(String.format(xpath, label))
                .textContent()
                .trim();
    }

    // POPUP VISIBILITY
    public boolean isPopupVisible(String title) {
        String xpath = "//*[normalize-space()='%s']";
        return page.locator(String.format(xpath, title)).isVisible();        
    } 


    // NULL VALUE CHECKING 
    public boolean hasValue(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public void pressKey(String key) {
        page.keyboard().press(key);
    }

    // CLOSE WINDOW  ----> "X" IN WIDOW
    public void closeNewWindow() {
        List<Page> pages = page.context().pages();

        if (pages.size() > 1) {
            Page newPage = pages.get(pages.size() - 1);
            newPage.close();
            page.bringToFront();
        }
    }



}