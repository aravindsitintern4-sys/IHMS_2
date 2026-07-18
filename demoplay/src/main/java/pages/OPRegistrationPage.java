package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import locators.Locator;

public class OPRegistrationPage {

    private Page page;

    public OPRegistrationPage(Page page) {
        this.page = page;
    }

    public void selectSomeForceDropdownByLabel(String labelName, String option) {
        String droploc = String.format(Locator.dropdownForceLoc,labelName);
        page.locator(droploc).first().selectOption(new SelectOption().setLabel(option));
    }

    public void newChangeDropdown(String labelName, String option) {

        String appDropdownButton = "//label[contains(normalize-space(),'%s')]/following::app-dropdown[1]//button";

        String optionInput = "//input[@placeholder='Search...']";
        String optionSelect = "//li[normalize-space()='%s']";

        page.locator(String.format(appDropdownButton, labelName)).click();
        page.locator(optionInput).fill(option);
        page.locator(String.format(optionSelect, option)).last().click();
    }

    

   
}