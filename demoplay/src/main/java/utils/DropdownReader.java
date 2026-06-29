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

    public DropdownReader(Page page) {
        this.page = page;
    }

    public void captureAllDropdowns() throws IOException {
    
        Map<String, List<String>> dropdownMap = new LinkedHashMap<>();

        // FIND DROPDOWNS BY SELECT LABEL
        Locator dropdowns = page.locator("//select");

        int count = dropdowns.count();

        System.out.println("Total Dropdowns : " + count);

        for (int i = 0; i < count; i++) {

            Locator dropdown = dropdowns.nth(i);

            String labelXpath = "(//select)[" + (i + 1) + "]/preceding::label[1]";

            String labelName = page.locator("xpath=" + labelXpath)
                                .textContent()
                                .trim();

            List<String> options = dropdown.locator("option").allTextContents();

            // REMOVE DUPLICATE USING "LinkedHashSet"
            List<String> uniqueOptions = new ArrayList<>(new LinkedHashSet<>(options));

            dropdownMap.put(labelName, uniqueOptions);
        }

        JsonUtil.writeJson(dropdownMap);

        System.out.println("JSON data file created successfully");
    }

    public void captureAllCustomDropdowns() throws IOException {

    Map<String, List<String>> dropdownMap = new LinkedHashMap<>();

    // Find all custom dropdowns
    Locator dropdowns = page.locator("app-single-select-with-search");

    int count = dropdowns.count();

    System.out.println("Total Custom Dropdowns : " + count);

    for (int i = 0; i < count; i++) {

        Locator dropdown = dropdowns.nth(i);

        String labelXpath = "(//app-single-select-with-search)[" + (i + 1) + "]/preceding::label[1]";

        String labelName = page.locator("xpath=" + labelXpath)
                               .textContent()
                               .replace("*", "")
                               .trim();

        // Open dropdown
        dropdown.locator("div.cursor-pointer").click();

        // Wait for options to appear
        page.locator("//div[contains(@class,'max-h-48')]").waitFor();

        // Read all options
        List<String> options =
                page.locator("//div[contains(@class,'max-h-48')]//div[contains(@class,'cursor-pointer')]")
                    .allTextContents();

        // Remove duplicates
        List<String> uniqueOptions =
                new ArrayList<>(new LinkedHashSet<>(options));

        dropdownMap.put(labelName, uniqueOptions);

        // Close dropdown
        page.keyboard().press("Escape");
    }

    JsonUtil.writeJson(dropdownMap);

    System.out.println("Custom dropdown JSON created successfully");
}
                                   
}