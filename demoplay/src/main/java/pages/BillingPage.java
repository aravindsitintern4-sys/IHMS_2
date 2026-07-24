package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

public class BillingPage {

    private Page page;

    public BillingPage(Page page) {
        this.page = page;
    }

    //  TABLE CUSTOM DROPDOWN
    public void selectCustomTableDropdown(String tableHeader, String option) {
        int tdIndex = -1;
        Locator headers = page.locator("//table//thead//th");
        for (int i = 0; i < headers.count(); i++) {
            if (headers.nth(i).textContent().replace("*", "").trim().equalsIgnoreCase(tableHeader)) {
                tdIndex = i - 1; // S.No is <th scope="row">, not a <td>
                break;
            }
        }

        page.locator("//table//tbody//tr[1]/td").nth(tdIndex).locator("app-dropdown button").click();
        page.locator("//input[@placeholder='Search...']").pressSequentially(option);
        page.locator("//li[normalize-space()='" + option + "']").last().click();
    }

    // TABLE NORMAL DROPDOWN
    public void selectNormalTableDropdown(String tableHeader, String option) {
        int tdIndex = -1;
        Locator headers = page.locator("//table//thead//th");
        for (int i = 0; i < headers.count(); i++) {
            if (headers.nth(i).textContent().replace("*", "").trim().equalsIgnoreCase(tableHeader)) {
                tdIndex = i - 1; // S.No is <th scope="row">, not a <td>
                break;
            }
        }

        page.locator("//table//tbody//tr[1]/td").nth(tdIndex)
            .locator("select")
            .selectOption(new SelectOption().setLabel(option));
    }

}

    // public void selectTableDropdown(String tableHeader, String option) {
    //     page.locator("//td[normalize-space()='" + tableHeader + "']/following-sibling::td//button").click();
    //     page.locator("//input[@placeholder='Search...']").fill(option);
    //     page.locator("//div[normalize-space()='" + option + "']").click();
    // } 
