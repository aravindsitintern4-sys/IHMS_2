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
    
}