package tests;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import basetest.BaseTest;
import pages.BillingPage;
import pages.DashboardPage;
import pages.OPRegistrationPage;
import tests.TestContext;
import utils.DropdownReader;
import utils.Excel;
import utils.JsonUtil;
import utils.ReusableCode;
import utils.Screenshots;

import tests.OPRegistrationTest;

public class BillingTest extends BaseTest {
    
    // @Test(priority = 3)
    public void verifyBilling() throws IOException {
        
        int lastColumn = Excel.getLastDataColumn("Billing Data");
        System.out.println("Last Column = " + lastColumn);


        for (int column = 1; column <= lastColumn; column++) {

            // INITIAL METHOS CALLING
            TestContext ctx = CallInitialMethods("Billing", "Bill Entry","Billing Data",column);

            boolean isColumnEmpty = ctx.data.values().stream().allMatch(value -> value == null || value.trim().isEmpty());
            if (isColumnEmpty) {
                System.out.println("Column " + column + " is empty. Stopping execution.");
                break;
            }
            System.out.println("Executing Person Data in Column: " + column);

            ctx.dropdownReader.captureAllDropdownOptions();

            ctx.reusableAction.inputFieldByLabel("UIN", ctx.data.get("uinBilling"));
            ctx.reusableAction.pressKey("Enter");
            ctx.reusableAction.inputFieldByLabelPopup("Enter 3 Digit Pin",ctx.data.get("pin number"));
            ctx.billPage.selectTableDropdown("Test Name",ctx.data.get("test name"));
        }    
    }

}