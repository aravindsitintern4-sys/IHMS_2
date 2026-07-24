package tests;

import java.io.IOException;

import org.testng.annotations.Test;

import utils.Excel;
import java.util.LinkedHashMap;
import java.util.Map;
import basetest.BaseTest;


public class EditAddressTest extends BaseTest {

    // OP MODULES -----> OUTPATIENT REGISTRATON ------> EDIT ADDRESS
    // @Test(priority = 3)
    public void verifyEditAddress() throws IOException {
    int lastColumn = Excel.getLastDataColumn("Extra collection");

    String[] extraSheets = {"opRegistrationData"}; 

    for (int column = 1; column <= lastColumn; column++) {

        TestContext ctx = CallInitialMethods("OP Modules", "Outpatient Registration", "Extra collection", column);

        boolean isColumnEmpty = ctx.data.values().stream().allMatch(value -> value == null || value.trim().isEmpty());
        if (isColumnEmpty) {
            System.out.println("Column " + column + " is empty. Stopping execution.");
            break;
        }


        // USED TO GET THE DATAS FROM MULTIPLE SHEETS
        Map<String, String> mergedData = new LinkedHashMap<>(ctx.data);
        for (String sheetName : extraSheets) {
            mergedData.putAll(Excel.getTestData(sheetName, column));
        }

        System.out.println("Executing Person Data in Column: " + column);

        ctx.reusableAction.navigateToSubmenu("Outpatient Registration", "Edit Address");
        ctx.reusableAction.inputFieldByLabel("UIN", mergedData.get("common uin"));
        ctx.reusableAction.pressKey("Enter");
        ctx.reusableAction.inputFieldByLabel("Mobile No", mergedData.get("MobileNo"));
        ctx.reusableAction.buttonClick("Save");
        ctx.reusableAction.buttonClickPopup("OK");
    }
}



    //  TOOLS -------> PATIENT INFORMATION 
    // @Test(priority = 4)
        public void verifyPatientInformation() throws IOException {
        int lastColumn = Excel.getLastDataColumn("Extra collection");

        String[] extraSheets = {"opRegistrationData"}; 

        for (int column = 1; column <= lastColumn; column++) {

            TestContext ctx = CallInitialMethods("Tools", "Patient Information", "Extra collection", column);                 

            boolean isColumnEmpty = ctx.data.values().stream().allMatch(value -> value == null || value.trim().isEmpty());
            if (isColumnEmpty) {
                System.out.println("Column " + column + " is empty. Stopping execution.");    
                break;
            }

            // USED TO GET THE DATAS FROM MULTIPLE SHEETS
            Map<String, String> mergedData = new LinkedHashMap<>(ctx.data);
            for (String sheetName : extraSheets) {
                mergedData.putAll(Excel.getTestData(sheetName, column));
            }

            System.out.println("Executing Person Data in Column: " + column);
            ctx.reusableAction.inputFieldByLabel("UIN", mergedData.get("common uin"));
            ctx.reusableAction.pressKey("Enter");
            ctx.reusableAction.buttonClick("Ok");
        }
    }



    //  TOOLS -------> PATIENT INFORMATION 
    // @Test(priority = 5)
        public void verifyPatientEnquiry() throws IOException {
        int lastColumn = Excel.getLastDataColumn("Extra collection");

        String[] extraSheets = {"opRegistrationData"}; 

        for (int column = 1; column <= lastColumn; column++) {

            TestContext ctx = CallInitialMethods("Tools", "Patient Enquiry", "Extra collection", column);                 

            boolean isColumnEmpty = ctx.data.values().stream().allMatch(value -> value == null || value.trim().isEmpty());
            if (isColumnEmpty) {
                System.out.println("Column " + column + " is empty. Stopping execution.");    
                break;
            }

            // USED TO GET THE DATAS FROM MULTIPLE SHEETS
            Map<String, String> mergedData = new LinkedHashMap<>(ctx.data);
            for (String sheetName : extraSheets) {
                mergedData.putAll(Excel.getTestData(sheetName, column));
            }

            System.out.println("Executing Person Data in Column: " + column);

            ctx.reusableAction.selectRadioByLabel("Type", ctx.data.get("Enquiry patient type"));
            // ctx.reusableAction.checkboxByLabel("Today's Registration");




            // ctx.reusableAction.inputFieldByLabel("UIN", mergedData.get("common uin"));
            // ctx.reusableAction.pressKey("Enter");
            // ctx.reusableAction.buttonClick("Ok");
        }
    }

}
