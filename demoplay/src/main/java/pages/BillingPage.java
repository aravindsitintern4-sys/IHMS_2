package pages;

import com.microsoft.playwright.Page;
import java.util.List;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.options.SelectOption;

import locators.Locator;

public class BillingPage {

    private Page page;

    public BillingPage(Page page) {
        this.page = page;
    }
    
    public void selectTableDropdown(String tableHeader, String option) {
        page.locator("//td[normalize-space()='" + tableHeader + "']/following-sibling::td//button").click();
        page.locator("//input[@placeholder='Search...']").fill(option);
        page.locator("//div[normalize-space()='" + option + "']").click();
    }
}
