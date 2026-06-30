package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DropdownReader {

    private Page page;

    // MAP FOR FULL EXECUTION (IF WE GIVE SEPERATELY THE DATAS STORES FOR EVERY TEST RUN)
    private Map<String, List<String>> dropdownMap = new LinkedHashMap<>();

    public DropdownReader(Page page) {
        this.page = page;
    }

    // NORMAL DROPDOWN
    public void captureAllDropdowns() throws IOException {

        Locator dropdowns = page.locator("//select");

        int count = dropdowns.count();

        System.out.println("Total Dropdowns : " + count);

        for (int i = 0; i < count; i++) {

            Locator dropdown = dropdowns.nth(i);

            String labelXpath = "(//select)[" + (i + 1) + "]/preceding::label[1]";

            String labelName = page.locator("xpath=" + labelXpath)
                    .textContent()
                    .replace("*", "")
                    .trim();

            List<String> options = dropdown.locator("option").allTextContents();

            List<String> uniqueOptions =
                    new ArrayList<>(new LinkedHashSet<>(options));

            dropdownMap.put(labelName, uniqueOptions);
        }

        JsonUtil.writeJson(dropdownMap);

        System.out.println("JSON data file created successfully");
    }



    // CUSTOM DROPDOWN
    public void captureAllCustomDropdowns() throws IOException {

        Locator dropdowns = page.locator("app-single-select-with-search");

        int count = dropdowns.count();

        System.out.println("Total Custom Dropdowns : " + count);

        Locator optionsPanel = page.locator("//div[contains(@class,'max-h-48')]");

        for (int i = 0; i < count; i++) {

            Locator dropdown = dropdowns.nth(i);

            String labelXpath ="(//app-single-select-with-search)[" + (i + 1) + "]/preceding::label[1]";

            String labelName = page.locator("xpath=" + labelXpath)
                    .textContent()
                    .replace("*", "")
                    .trim();

            System.out.println("Reading : " + labelName);

            // CLOSE IF ALREADY DROPDOWN IS OPEN
            if (optionsPanel.isVisible()) {
                page.locator("body").click();
                page.waitForTimeout(300);
            }

            // OPEN DROPDOWN
            dropdown.locator("div.cursor-pointer").click();
            optionsPanel.waitFor();

            // READ OPTIONS
            List<String> options = optionsPanel
                    .locator("div.cursor-pointer")
                    .allTextContents();

            List<String> uniqueOptions =new ArrayList<>(new LinkedHashSet<>(options));

            dropdownMap.put(labelName, uniqueOptions);

            System.out.println("Stored : " + labelName);

            // CLOSE DROPDOWN
            page.keyboard().press("Escape");
            page.locator("body").click();
        }

        JsonUtil.writeJson(dropdownMap);

        System.out.println("Custom dropdown JSON created successfully");
    }
}