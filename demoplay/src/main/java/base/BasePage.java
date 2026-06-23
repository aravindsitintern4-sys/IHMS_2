package base;

import com.microsoft.playwright.Page;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void click(String locator) {
        page.locator(locator).click();
    }

    public void fill(String locator, String value) {
        page.locator(locator).fill(value);
    }

    public void waitFor(String locator) {
        page.locator(locator).waitFor();
    }
}