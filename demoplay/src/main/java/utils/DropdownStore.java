package utils;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DropdownStore {

    private final Page page;

    private static final Map<String, Set<String>> dropdownData = new HashMap<>();

    public DropdownStore(Page page) {
        this.page = page;
    }

    public void storeDropdownOptions(String labelName) {

        Set<String> optionsSet = new LinkedHashSet<>();

        // Locate and open the dropdown
        String dropdownXpath = String.format(
                "//label[contains(normalize-space(),'%s')]/following::div[contains(@class,'cursor-pointer')][1]",
                labelName);

        Locator dropdown = page.locator(dropdownXpath);

        dropdown.waitFor();
        dropdown.click();

        // Wait for dropdown options panel
        Locator options = page.locator(
                "//div[contains(@class,'overflow-y-auto')]//div[contains(@class,'cursor-pointer')]");

        options.first().waitFor();

        int count = options.count();

        System.out.println("Option Count : " + count);

        for (int i = 0; i < count; i++) {

            String text = options.nth(i).textContent();

            if (text != null && !text.trim().isEmpty()) {
                optionsSet.add(text.trim());
            }
        }

        dropdownData.put(labelName, optionsSet);

        System.out.println(labelName + " : " + optionsSet);

        page.keyboard().press("Escape");
    }

    public static Set<String> getDropdownOptions(String labelName) {
        return dropdownData.get(labelName);
    }

    public static void printAllDropdowns() {
        System.out.println(dropdownData);
    }
}