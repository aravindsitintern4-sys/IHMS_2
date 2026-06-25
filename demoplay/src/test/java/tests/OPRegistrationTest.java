package tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import basetest.BaseTest;
import pages.DashboardPage;
import pages.OPRegistrationPage;
import utils.Excel;
import utils.ReusableCode;
import utils.Screenshots;

public class OPRegistrationTest extends BaseTest {

    @Test
    public void verifyPAYOPRegistration() {
      
        Map<String, String> data = Excel.getTestData("opRegistrationData");

        DashboardPage dashboard = new DashboardPage(page);

        Page ihmsPage = page.waitForPopup(() -> {
            dashboard.clickDashboardOption("IHMS");
        });

        ReusableCode reusableAction = new ReusableCode(ihmsPage);

        reusableAction.clickMenuAndSelectSubMenu("OP Modules","Outpatient Registration");

        OPRegistrationPage opPage = new OPRegistrationPage(ihmsPage);

        reusableAction.selectDropdownByLabel("Pay/Free",data.get("PayFree"));
        reusableAction.selectDropdownByLabel("Patient Type",data.get("PatientType"));             
        reusableAction.inputFieldByLabel("First Name",data.get("FirstName"));
        reusableAction.inputFieldByLabel("Last Name",data.get("LastName"));
        reusableAction.inputFieldByLabel("Age",data.get("Age"));
        reusableAction.selectRadioByLabel("Gender",data.get("Gender"));
        reusableAction.selectDropdownByLabel("Next of Kin",data.get("NextOfKinType"));
        reusableAction.inputFieldByLabel("Next of Kin",data.get("NextOfKinName"));
        reusableAction.selectRadioByLabel("Normal / Referral",data.get("ReferralType"));
        if ("Referral".equalsIgnoreCase(data.get("ReferralType"))) {
            verifyReferralForm(reusableAction, data);
        }

        Screenshots.takeScreenshot(ihmsPage,"PAYOPRegistration");

        reusableAction.selectRadioByLabel("Nationality",data.get("Nationality"));
        reusableAction.inputFieldByLabel("Door / Street",data.get("DoorStreet"));
        reusableAction.inputFieldByLabel("Locality",data.get("Locality"));
        reusableAction.inputFieldByLabel("City",data.get("City"));
        reusableAction.buttonClick("Area");
        opPage.selectSomeForceDropdownByLabel("City",data.get("Area"));
        reusableAction.inputFieldByLabel("PinCode",data.get("PinCode"));
        opPage.selectSomeForceDropdownByLabel("Taluk",data.get("Taluk"));
        reusableAction.inputFieldByLabel("Mobile No",data.get("MobileNo"));
        reusableAction.inputFieldByLabel("Email",data.get("Email"));
        reusableAction.selectDropdownByLabel("Purpose Of Visit",data.get("PurposeVisit"));
        reusableAction.selectDropdownByLabel("Mobile App ConsentForm",data.get("ConsentForm"));

        // verifySubsidySubCategory(reusableAction, data);
        
    }

    public void verifySubsidySubCategory(
            ReusableCode reusableAction,
            Map<String, String> data) {

        reusableAction.selectDropdownByLabel("Patient Sub Category:",data.get("PatientSubCategorySubsidy"));
        reusableAction.selectSearchableDropdownByLabel("Concession Approved By",data.get("ApprovedBy"));
        reusableAction.inputFieldByLabel("% Concession granted",data.get("ConcessionPercent"));
        reusableAction.selectSearchableDropdownByLabel("Reason",data.get("Reason"));
        reusableAction.inputFieldByLabel("Remarks",data.get("Remarks"));
        reusableAction.buttonClick("Save");
    }



    // REFERRAL FORM
    public void verifyReferralForm(ReusableCode reusableAction , Map<String, String> data) {
    

        reusableAction.inputFieldByLabel("Reference No",data.get("Reference No"));
        // reusableAction.checkboxByLabel("CRS");
        // opPage.selectSomeForceDropdownByLabel("District",data.get("MADURAI"));
        reusableAction.inputFieldByLabel("Reference Date",data.get("Reference date"));
        reusableAction.selectDropdownByLabel("Referral Name",data.get("Referral name"));
        reusableAction.selectDropdownByLabel("Clinic Referred to",data.get("Clinic Referred to"));
        reusableAction.selectDropdownByLabel("Doctor Referred to",data.get("Doctor Referred to"));
        reusableAction.buttonClick("Save");
    }

}