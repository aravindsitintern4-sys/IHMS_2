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






// EXCEL TO EXCEL
// package utils;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.LinkedHashMap;
// import java.util.LinkedHashSet;
// import java.util.List;
// import java.util.Map;

// import com.microsoft.playwright.Locator;
// import com.microsoft.playwright.Page;

// public class DropdownReader {

//     private Page page;

//     // STORE ALL DROPDOWN FOR CURRENT EXECUTION
//     private Map<String, List<String>> dropdownMap = new LinkedHashMap<>();
//     public DropdownReader(Page page) {
//         this.page = page;
//     }

//     // NORMAL DROPDOWNS
//     public void captureAllDropdowns() {

//         Locator dropdowns = page.locator("//select");
//         int count = dropdowns.count();
//         System.out.println("Total Normal Dropdowns : " + count);

//         for (int i = 0; i < count; i++) {
//             Locator dropdown = dropdowns.nth(i);
//             String labelXpath = "(//select)[" + (i + 1) + "]/preceding::label[1]";
//             String labelName = page.locator("xpath=" + labelXpath)
//                     .textContent()
//                     .replace("*", "")
//                     .trim();

//             List<String> options = dropdown.locator("option").allTextContents();

//             // REMOVE DUPLICATES
//             List<String> uniqueOptions = new ArrayList<>(new LinkedHashSet<>(options));

//             dropdownMap.put(labelName, uniqueOptions);
//             System.out.println(labelName + " --> " + uniqueOptions);
//         }
//     }


//     // CUSTOM DROPDOWNS
//     public void captureAllCustomDropdowns() {

//         Locator dropdowns = page.locator("app-single-select-with-search");
//         int count = dropdowns.count();
//         System.out.println("Total Custom Dropdowns : " + count);
//         Locator optionsPanel =page.locator("//div[contains(@class,'max-h-48')]");

//         for (int i = 0; i < count; i++) {
//             Locator dropdown = dropdowns.nth(i);
//             String labelXpath = "(//app-single-select-with-search)[" + (i + 1) + "]/preceding::label[1]";

//             String labelName = page.locator("xpath=" + labelXpath)
//                     .textContent()
//                     .replace("*", "")
//                     .trim();
//             System.out.println("Reading : " + labelName);

//             // CLOSE PREVIOUSLY OPENED DROPDOWN
//             if (optionsPanel.isVisible()) {
//                 page.keyboard().press("Escape");
//                 page.waitForTimeout(300);
//             }
                    
//             // OPEN CURRENT DROPDOWN
//             dropdown.locator("div.cursor-pointer").click();
//             optionsPanel.waitFor();

//             // READ OPTIONS
//             List<String> options = optionsPanel
//                     .locator("div.cursor-pointer")
//                     .allTextContents();

//             // REMOVE DUPLICATES
//             List<String> uniqueOptions = new ArrayList<>(new LinkedHashSet<>(options));

//             // STORE IT IN MEMORY
//             dropdownMap.put(labelName, uniqueOptions);
//             System.out.println("Stored : " + labelName);

//             // CCLOSE DROPDOWN AFTER STORING
//             page.keyboard().press("Escape");
//             page.waitForTimeout(300);
//         }
//     }


//     // SOME DROPDOWNS ARE ENABLE AFTER SOME ACTIONS DONE FOR THOSE DROPDOWN THIS CAPTURESINGLEDROPDOWN METHOD IS USED ----> NORMAL DROPDOWN
//     public void captureNormalDropdown(String labelName) {

//         Locator dropdown = page.locator("//label[contains(normalize-space(),'" + labelName + "')]/following::select[1]");

//         // WAIT UNTIL DROPDOWN ENABLE
//         dropdown.waitFor();

//         int retry = 20;

//         while (retry-- > 0) {
//             List<String> options = dropdown.locator("option").allTextContents();
//             List<String> uniqueOptions = new ArrayList<>(new LinkedHashSet<>(options));

//             // IGNORE TEMPORARY STATE, BECAUSE THAT HOLD ONLY TWO OPTIONS WHEN DISABLED STATE (SELECT AND NO DATA FOUND)
//             if (uniqueOptions.size() > 2 && !uniqueOptions.contains("No data Found")) {
//                 dropdownMap.put(labelName, uniqueOptions);
//                 System.out.println(labelName + " Loaded --> " + uniqueOptions);
//                 return;
//             }
//             page.waitForTimeout(500);
//         }
//         throw new RuntimeException(labelName + " dropdown options were not loaded.");
//     }


//     // SOME DROPDOWNS ARE ENABLE AFTER SOME ACTIONS DONE FOR THOSE DROPDOWN THIS CAPTURESINGLEDROPDOWN METHOD IS USED ----> CUSTOM DROPDOWN
//     public void captureSingleCustomDropdown(String labelName) {

//         Locator dropdown = page.locator("//label[contains(normalize-space(),'" + labelName + "')]/following::app-single-select-with-search[1]");
//         Locator optionsPanel = page.locator("//div[contains(@class,'max-h-48')]");
//         System.out.println("Reading : " + labelName);

//         // CLOSE ALREADY OPENED DROPDOWN
//         if (optionsPanel.isVisible()) {
//             page.keyboard().press("Escape");
//             page.waitForTimeout(300);

//             // IF STILL OPEN CLICK OUTSIDE OF PAGE
//             if (optionsPanel.isVisible()) {
//                 page.locator("body").click();
//                 page.waitForTimeout(300);
//             }
//         }

//         // OPEN CURRENT DROPDOWN
//         dropdown.locator("div.cursor-pointer").click();
//         optionsPanel.waitFor();

//         // READ OPTIONS
//         List<String> options = optionsPanel
//                 .locator("div.cursor-pointer")
//                 .allTextContents();

//         // REMOVE DUPLICATES
//         List<String> uniqueOptions =
//                 new ArrayList<>(new LinkedHashSet<>(options));

//         // STORE IT IN MAP
//         dropdownMap.put(labelName, uniqueOptions);
//         System.out.println("Stored : " + labelName + " --> " + uniqueOptions);

//         // CLOSE DROPDOWN AFTER STORING
//         page.keyboard().press("Escape");
//         page.waitForTimeout(300);

//         // IF STILL OPEN CLICK OUTSIDE OF PAGE
//         if (optionsPanel.isVisible()) {
//             page.locator("body").click();
//             page.waitForTimeout(300);
//         }
//     }


//     // CAPUTURE DROPDOWNS ---> FOR CAPTURESIGLE AND CUSTOM METHODS 
//     public void captureDropdowns(String... labels) {

//         for (String label : labels) {
//             Locator normal = page.locator("//label[contains(normalize-space(),'" + label + "')]/following::select[1]");
//             if (normal.count() > 0) {
//                 captureNormalDropdown(label);
//             } else {
//                 captureSingleCustomDropdown(label);
//             }
//         }
//     }


//     // WRITE TO EXCEL
//     public void saveDropdownOptions() throws IOException {
//         Excel.writeDropdownOptions(dropdownMap);
//         System.out.println("Dropdown options written to Excel successfully.");
//     }


//     // GETTER METHOD ALLOWS OTHER CLASSES TO READ THE MAP 
//     public Map<String, List<String>> getDropdownMap() {
//         return dropdownMap;
//     }

// }