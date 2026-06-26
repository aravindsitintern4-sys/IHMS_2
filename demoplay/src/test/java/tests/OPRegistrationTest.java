package tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import basetest.BaseTest;
import pages.DashboardPage;
import pages.OPRegistrationPage;
import utils.DropdownStore;
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

        DropdownStore dropdownStore = new DropdownStore(ihmsPage);

        dropdownStore.storeDropdownOptions("Taluk");

        reusableAction.selectDropdownByLabel("Pay/Free",data.get("PayFree"));
        reusableAction.selectDropdownByLabel("Patient Type",data.get("PatientType"));             
        reusableAction.inputFieldByLabel("First Name",data.get("FirstName"));
        reusableAction.inputFieldByLabel("Last Name",data.get("LastName"));
        reusableAction.inputFieldByLabel("Age",data.get("Age"));
        reusableAction.inputFieldByLabel("DOB",data.get("Date of Birth"));
        reusableAction.selectRadioByLabel("Gender",data.get("Gender"));
        reusableAction.selectDropdownByLabel("Next of Kin",data.get("NextOfKinType"));
        reusableAction.inputFieldByLabel("Next of Kin",data.get("NextOfKinName"));
        reusableAction.selectRadioByLabel("Normal / Referral",data.get("ReferralType"));
        if ("Referral".equalsIgnoreCase(data.get("ReferralType"))) {
            verifyReferralForm(reusableAction, data);
        }

        // Screenshots.takeScreenshot(ihmsPage,"PAYOPRegistration");

        reusableAction.selectRadioByLabel("Nationality",data.get("Nationality"));
        reusableAction.inputFieldByLabel("Door / Street",data.get("DoorStreet"));
        reusableAction.inputFieldByLabel("Locality",data.get("locality"));
        reusableAction.inputFieldByLabel("City",data.get("City"));
        reusableAction.buttonClick("Area");
        opPage.selectSomeForceDropdownByLabel("City",data.get("Area"));
        reusableAction.inputFieldByLabel("PinCode",data.get("PinCode"));
        opPage.selectSomeForceDropdownByLabel("Taluk",data.get("Taluk"));
        reusableAction.inputFieldByLabel("Mobile No",data.get("MobileNo"));
        reusableAction.inputFieldByLabel("Email",data.get("Email"));
        reusableAction.selectDropdownByLabel("Purpose Of Visit",data.get("PurposeVisit"));
        reusableAction.selectDropdownByLabel("Mobile App ConsentForm",data.get("ConsentForm"));
        reusableAction.selectDropdownByLabel("Patient Category:",data.get("Patient category"));
        // reusableAction.selectDropdownByLabel("Patient Sub Category:",data.get("PatientSubCategory"));

        // verifySubsidySubCategory(reusableAction, data);
        verifyCorporateCategory(reusableAction,data);
                        
   
    }
   
    // CORPORATE PATIENT CATEGORY
    public void verifyCorporateCategory(ReusableCode reusableAction,Map<String, String> data) {
        reusableAction.selectDropdownByLabel("Corporate Name", data.get("Corporate name"));
        reusableAction.inputFieldByLabel("Document Ref.No",data.get("Document Ref.no"));
        reusableAction.selectDropdownByLabel("Employee Grade", data.get("Employee grade"));
        reusableAction.inputFieldByLabel("Employee Code",data.get("Employee code"));
        reusableAction.inputFieldByLabel("Claim ID",data.get("Claim id"));
        reusableAction.textAreaFieldByLabel("Remarks / Registration No",data.get("Corporate remarks"));
        reusableAction.buttonClick("Submit");
    }

    // SUBSIDY 
    public void verifySubsidySubCategory(ReusableCode reusableAction,Map<String, String> data) {
        reusableAction.selectSearchableDropdownByLabel("Subsidy Approved By",data.get("SubsidyApprovedBy"));
        reusableAction.inputFieldByLabel("% Subsidy granted",data.get("Subsidy granted"));
        reusableAction.selectSearchableDropdownByLabel("Reason",data.get("Subsidy Reason"));
        reusableAction.inputFieldByLabel("Remarks",data.get("Subsidy remarks"));
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