package pages;

import com.microsoft.playwright.Page;
import locators.Locator;

public class DashboardPage {

    private Page page;

    public DashboardPage(Page page) {
        this.page = page;
    }

    public void clickDashboardOption(String optionName) {
        page.click(String.format(Locator.DashboardOption,optionName));
    }
}