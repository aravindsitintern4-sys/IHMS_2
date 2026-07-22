package pages;

import java.util.List;

import com.microsoft.playwright.Keyboard;
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


    //  INPUT FIELD
    public void inputFieldByLabelPopup(String labelName, String inputValue) {
        String inputField ="//div[contains(@class,'fixed')]//label[contains(normalize-space(),'%s')]/following-sibling::input";
        String inputLoc = String.format(inputField, labelName);
        page.locator(inputLoc).click();
        page.locator(inputLoc).clear();  
        page.keyboard().type(inputValue, new Keyboard.TypeOptions().setDelay(50));
    } 
    
    public void buttonClickPopup(String btnName) {
        String buttonClick = "//div[contains(@class,'fixed')]//button[normalize-space()='%s']";
        page.click(String.format(buttonClick,btnName));
    } 

    public String getView(String value) {
        String xpath = "//h2[normalize-space()='%s']/following-sibling::div//div[contains(@class,'text-left')]";
        return page.locator(String.format(xpath, value))
                .textContent()
                .trim();
    }

    public boolean isPopupVisible(String title) {
        String xpath = "//h2[normalize-space()='%s']";
        return page.locator(String.format(xpath, title)).isVisible();
    }

    public String getUnitLoadTable(String loadTableValue) {
        // StringBuilder ---> USED TO STORE ALL TABLE VALUES (INITIALLY "TABLE_DATE ='' (empty)") ----> USED FOR STRING CONCATENATION
        StringBuilder tableData = new StringBuilder();
        String tableXpath = String.format("//h2[normalize-space()='%s']/following::table[1]//tr",loadTableValue);
        // COUNT THE NUMBER OF ROWS
        int rowCount = page.locator(tableXpath).count();

        // LOOP THROUGH EACH ROW 
        for (int i = 1; i <= rowCount; i++) {
            // GETS ALL TABLE DATAS
            List<String> cells = page.locator(String.format("(//h2[normalize-space()='%s']/following::table[1]//tr)[%d]//td",loadTableValue, i))
                    .allTextContents();
            // MULTIPLE TABLE DATAS STORED IN LIST THAT LIST IS CONVERT INTO SINGLE STRING
            tableData.append(String.join(" | ", cells)).append("\n");
        }
        // CONVERTS THE StringBuilder INTO A NORMAL STRING
        return tableData.toString().trim();
    }
    
}