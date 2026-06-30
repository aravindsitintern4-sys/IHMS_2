package tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import basetest.BaseTest;
import pages.DashboardPage;
import pages.OPRegistrationPage;
import utils.DropdownReader;
import utils.Excel;
import utils.JsonUtil;
import utils.ReusableCode;
import utils.Screenshots;

public class OPRegistrationTest extends BaseTest {
    
//     @Test
//     public void verifyPAYOPRegistration() throws IOException {
      
//         Map<String, String> data = Excel.getTestData("opRegistrationData");
        
        

//         DashboardPage dashboard = new DashboardPage(page);

//         Page ihmsPage = page.waitForPopup(() -> {
//             dashboard.clickDashboardOption("IHMS");
//         });

//         ReusableCode reusableAction = new ReusableCode(ihmsPage);

//         reusableAction.clickMenuAndSelectSubMenu("OP Modules","Outpatient Registration");

//         // WAIT FOR ALL SELECT LABEL VISIBLE
//         ihmsPage.locator("//select").first().waitFor();

//         DropdownReader dropdownReader = new DropdownReader(ihmsPage);
 
//         dropdownReader.captureAllDropdowns();
//         dropdownReader.captureAllCustomDropdowns();

//         OPRegistrationPage opPage = new OPRegistrationPage(ihmsPage); 
    
//         reusableAction.selectDropdownByLabel("Pay/Free",data.get("PayFree"));
//         reusableAction.selectDropdownByLabel("Patient Type",data.get("PatientType"));             
//         reusableAction.inputFieldByLabel("First Name",data.get("FirstName"));
//         reusableAction.inputFieldByLabel("Last Name",data.get("LastName"));
//         // reusableAction.inputFieldByLabel("Age",data.get("Age"));
//         reusableAction.inputFieldByLabel("DOB",data.get("Date of Birth"));
//         reusableAction.selectRadioByLabel("Gender",data.get("Gender"));
//         reusableAction.selectDropdownByLabel("Next of Kin",data.get("NextOfKinType"));
//         reusableAction.inputFieldByLabel("Next of Kin",data.get("NextOfKinName"));
//         reusableAction.selectRadioByLabel("Normal / Referral",data.get("ReferralType"));
//         if ("Referral".equalsIgnoreCase(data.get("ReferralType"))) {
//             verifyReferralForm(reusableAction,dropdownReader, data);
//         }
                                  
//         // Screenshots.takeScreenshot(ihmsPage,"PAYOPRegistration");

//         reusableAction.selectRadioByLabel("Nationality",data.get("Nationality"));
//         reusableAction.inputFieldByLabel("Door / Street",data.get("DoorStreet"));
//         reusableAction.inputFieldByLabel("Locality",data.get("locality"));
//         reusableAction.inputFieldByLabel("City",data.get("City"));
//         reusableAction.buttonClick("Area");
//         opPage.selectSomeForceDropdownByLabel("City",data.get("Area"));
//         reusableAction.inputFieldByLabel("PinCode",data.get("PinCode"));
//         opPage.selectSomeForceDropdownByLabel("Taluk",data.get("Taluk"));
//         reusableAction.inputFieldByLabel("Mobile No",data.get("MobileNo"));
//         reusableAction.inputFieldByLabel("Email",data.get("Email"));
//         reusableAction.selectDropdownByLabel("Purpose Of Visit",data.get("PurposeVisit"));
//         reusableAction.selectDropdownByLabel("Mobile App ConsentForm",data.get("ConsentForm"));
//         reusableAction.selectDropdownByLabel("Assign Doctor:", data.get("Assign doctor"));
//         reusableAction.selectDropdownByLabel("Patient Category:",data.get("Patient category"));
//         reusableAction.selectDropdownByLabel("Patient Sub Category:",data.get("PatientSubCategory"));
           
//         verifySubsidySubCategory(reusableAction,dropdownReader,data);
//         // verifyCorporateCategory(reusableAction,dropdownReader,data);   
//         reusableAction.buttonClick("Submit");            
//     }
       
//     // CORPORATE PATIENT CATEGORY
//     public void verifyCorporateCategory(ReusableCode reusableAction,DropdownReader dropdownReader,Map<String, String> data) throws IOException {
//         dropdownReader.captureAllDropdowns();
//         dropdownReader.captureAllCustomDropdowns();
//         reusableAction.selectDropdownByLabel("Corporate Name", data.get("Corporate name"));
//         reusableAction.inputFieldByLabel("Document Ref.No",data.get("Document Ref.no"));
//         reusableAction.selectDropdownByLabel("Employee Grade", data.get("Employee grade"));
//         reusableAction.inputFieldByLabel("Employee Code",data.get("Employee code"));
//         reusableAction.inputFieldByLabel("Claim ID",data.get("Claim id"));
//         reusableAction.textAreaFieldByLabel("Remarks / Registration No",data.get("Corporate remarks"));
//         reusableAction.buttonClick("Submit");
//     }
        
//     // SUBSIDY 
//     public void verifySubsidySubCategory(ReusableCode reusableAction,DropdownReader dropdownReader,Map<String, String> data) throws IOException {
//         dropdownReader.captureAllDropdowns();
//         dropdownReader.captureAllCustomDropdowns();
//         reusableAction.selectSearchableDropdownByLabel("Subsidy Approved By",data.get("SubsidyApprovedBy"));
//         reusableAction.inputFieldByLabel("% Subsidy granted",data.get("Subsidy granted"));
//         reusableAction.selectSearchableDropdownByLabel("Reason",data.get("Subsidy Reason"));
//         reusableAction.inputFieldByLabel("Remarks",data.get("Subsidy remarks"));
//         reusableAction.buttonClick("Save");
//     }                                             
     
//     // REFERRAL FORM
//     public void verifyReferralForm(ReusableCode reusableAction ,DropdownReader dropdownReader, Map<String, String> data) throws IOException {
//         dropdownReader.captureAllDropdowns();
//         dropdownReader.captureAllCustomDropdowns();
//         reusableAction.inputFieldByLabel("Reference No",data.get("Reference No"));
//         // reusableAction.checkboxByLabel("CRS");
//         // opPage.selectSomeForceDropdownByLabel("District",data.get("MADURAI"));
//         reusableAction.inputFieldByLabel("Reference Date",data.get("Reference date"));
//         reusableAction.selectDropdownByLabel("Referral Name",data.get("Referral name"));
//         reusableAction.selectDropdownByLabel("Clinic Referred to",data.get("Clinic Referred to"));
//         reusableAction.selectDropdownByLabel("Doctor Referred to",data.get("Doctor Referred to"));
//         reusableAction.buttonClick("Save");
//     }

// }






//   GET TEST DATA FROM EXCEL AND COMAPARE THE DROPDOWN VALUES FROM JSON
    // @Test
    // public void verifyPAYOPRegistration() throws IOException {

    //     Map<String, String> data = Excel.getTestData("opRegistrationData");

    //     DashboardPage dashboard = new DashboardPage(page);

    //     Page ihmsPage = page.waitForPopup(() -> {
    //         dashboard.clickDashboardOption("IHMS");
    //     });

    //     ReusableCode reusableAction = new ReusableCode(ihmsPage);

    //     reusableAction.clickMenuAndSelectSubMenu("OP Modules","Outpatient Registration");

    //     ihmsPage.locator("//select").first().waitFor();

    //     OPRegistrationPage opPage = new OPRegistrationPage(ihmsPage);

    //     validateAndSelectDropdown(reusableAction,"Pay/Free",data.get("PayFree"));
    //     validateAndSelectDropdown(reusableAction,"Patient Type",data.get("PatientType"));
    //     reusableAction.inputFieldByLabel("First Name", data.get("FirstName"));
    //     reusableAction.inputFieldByLabel("Last Name", data.get("LastName"));
    //     reusableAction.inputFieldByLabel("DOB", data.get("Date of Birth"));
    //     reusableAction.selectRadioByLabel("Gender", data.get("Gender"));
    //     validateAndSelectDropdown(reusableAction,"Next of Kin",data.get("NextOfKinType"));
    //     reusableAction.inputFieldByLabel("Next of Kin", data.get("NextOfKinName"));

    //     reusableAction.selectRadioByLabel("Normal / Referral",data.get("ReferralType"));
    //     if ("Referral".equalsIgnoreCase(data.get("ReferralType"))) {
    //         verifyReferralForm(reusableAction, data);
    //     }

    //     reusableAction.selectRadioByLabel("Nationality",data.get("Nationality"));
    //     reusableAction.inputFieldByLabel("Door / Street", data.get("DoorStreet"));
    //     reusableAction.inputFieldByLabel("Locality", data.get("locality"));
    //     reusableAction.inputFieldByLabel("City", data.get("City"));
    //     reusableAction.buttonClick("Area");
    //     validateAndSelectForceDropdown(opPage,"City /", data.get("Area"));
    //     reusableAction.inputFieldByLabel("PinCode", data.get("PinCode"));
    //     validateAndSelectForceDropdown(opPage,"Taluk", data.get("Taluk"));
    //     reusableAction.inputFieldByLabel("Mobile No", data.get("MobileNo"));
    //     reusableAction.inputFieldByLabel("Email", data.get("Email"));
    //     validateAndSelectDropdown(reusableAction,"Purpose Of Visit",data.get("PurposeVisit"));
    //     validateAndSelectDropdown(reusableAction,"Mobile App ConsentForm",data.get("ConsentForm"));
    //     validateAndSelectDropdown(reusableAction,"Assign Doctor:",data.get("Assign doctor"));
    //     validateAndSelectDropdown(reusableAction,"Patient Category:",data.get("Patient category"));
    //     validateAndSelectDropdown(reusableAction,"Patient Sub Category:",data.get("PatientSubCategory"));

    //     verifySubsidySubCategory(reusableAction, data);

    //     reusableAction.buttonClick("Submit");
    // }


    // public void verifyReferralForm(ReusableCode reusableAction,Map<String, String> data) {
    //     reusableAction.inputFieldByLabel("Reference No", data.get("Reference No"));
    //     reusableAction.inputFieldByLabel("Reference Date",data.get("Reference date"));
    //     validateAndSelectDropdown(reusableAction,"Referral Name",data.get("Referral name"));
    //     validateAndSelectDropdown(reusableAction,"Clinic Referred to",data.get("Clinic Referred to"));
    //     validateAndSelectDropdown(reusableAction,"Doctor Referred to",data.get("Doctor Referred to"));
    //     reusableAction.buttonClick("Save");
    // }

    // public void verifySubsidySubCategory(ReusableCode reusableAction, Map<String, String> data) {
    //     validateAndSelectSearchableDropdown(reusableAction,"Subsidy Approved By",data.get("SubsidyApprovedBy"));
    //     reusableAction.inputFieldByLabel("% Subsidy granted",data.get("Subsidy granted"));
    //     validateAndSelectSearchableDropdown(reusableAction,"Reason",data.get("Subsidy Reason"));
    //     reusableAction.inputFieldByLabel("Remarks",data.get("Subsidy remarks"));
    //     reusableAction.buttonClick("Save");
    // }

    // public void verifyCorporateCategory(ReusableCode reusableAction,Map<String, String> data) {
    //     validateAndSelectDropdown(reusableAction,"Corporate Name",data.get("Corporate name"));
    //     reusableAction.inputFieldByLabel("Document Ref.No",data.get("Document Ref.no"));
    //     validateAndSelectDropdown(reusableAction,"Employee Grade",data.get("Employee grade"));
    //     reusableAction.inputFieldByLabel("Employee Code",data.get("Employee code"));
    //     reusableAction.inputFieldByLabel("Claim ID",data.get("Claim id"));
    //     reusableAction.textAreaFieldByLabel("Remarks / Registration No",data.get("Corporate remarks"));
    //     reusableAction.buttonClick("Submit");
    // }

    // private void validateAndSelectDropdown(ReusableCode reusableAction,String label,String value) {
    //     if (!JsonUtil.containsOption(label, value)) {
    //         throw new RuntimeException("Option '" + value +"' not found under '" + label + "' in JSON.");
    //     }
    //  reusableAction.selectDropdownByLabel(label, value);
    // }   
                  
    // private void validateAndSelectSearchableDropdown(ReusableCode reusableAction,String label,String value) {
    //     if (!JsonUtil.containsOption(label, value)) {
    //         throw new RuntimeException("Option '" + value +"' not found under '" + label + "' in JSON.");
    //     }      
    //     reusableAction.selectSearchableDropdownByLabel(label, value);
    // }

    // private void validateAndSelectForceDropdown(OPRegistrationPage opPage,String label,String value) {
    //     if (!JsonUtil.containsOption(label, value)) {
    //         throw new RuntimeException("Option '" + value +"' not found under '" + label + "' in JSON.");
    //     }
    //     opPage.selectSomeForceDropdownByLabel(label, value);
    // }






@Test
public void verifyPAYOPRegistration() throws IOException {

    Map<String, String> data = Excel.getTestData("opRegistrationData");

    Map<String, List<String>> dropdownOptions =
            Excel.getDropdownOptions("DropdownOptions");

    DashboardPage dashboard = new DashboardPage(page);

    Page ihmsPage = page.waitForPopup(() -> {
        dashboard.clickDashboardOption("IHMS");
    });

    ReusableCode reusableAction = new ReusableCode(ihmsPage);

    reusableAction.clickMenuAndSelectSubMenu(
            "OP Modules",
            "Outpatient Registration");

    ihmsPage.locator("//select").first().waitFor();

    OPRegistrationPage opPage = new OPRegistrationPage(ihmsPage);

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Pay/Free", data.get("PayFree"));

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Patient Type", data.get("PatientType"));

    reusableAction.inputFieldByLabel("First Name", data.get("FirstName"));
    reusableAction.inputFieldByLabel("Last Name", data.get("LastName"));
    reusableAction.inputFieldByLabel("DOB", data.get("Date of Birth"));
    reusableAction.selectRadioByLabel("Gender", data.get("Gender"));

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Next of Kin", data.get("NextOfKinType"));

    reusableAction.inputFieldByLabel("Next of Kin", data.get("NextOfKinName"));

    reusableAction.selectRadioByLabel("Normal / Referral",
            data.get("ReferralType"));

    if ("Referral".equalsIgnoreCase(data.get("ReferralType"))) {
        verifyReferralForm(reusableAction, dropdownOptions, data);
    }

    reusableAction.selectRadioByLabel("Nationality",
            data.get("Nationality"));

    reusableAction.inputFieldByLabel("Door / Street",
            data.get("DoorStreet"));

    reusableAction.inputFieldByLabel("Locality",
            data.get("locality"));

    reusableAction.inputFieldByLabel("City",
            data.get("City"));

    reusableAction.buttonClick("Area");

    validateAndSelectForceDropdown(opPage, dropdownOptions,
            "City /", data.get("Area"));

    reusableAction.inputFieldByLabel("PinCode",
            data.get("PinCode"));

    validateAndSelectForceDropdown(opPage, dropdownOptions,
            "Taluk", data.get("Taluk"));

    reusableAction.inputFieldByLabel("Mobile No",
            data.get("MobileNo"));

    reusableAction.inputFieldByLabel("Email",
            data.get("Email"));

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Purpose Of Visit", data.get("PurposeVisit"));

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Mobile App ConsentForm", data.get("ConsentForm"));

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Assign Doctor:", data.get("Assign doctor"));

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Patient Category:", data.get("Patient category"));

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Patient Sub Category:", data.get("PatientSubCategory"));

    verifySubsidySubCategory(reusableAction, dropdownOptions, data);

    reusableAction.buttonClick("Submit");
}

public void verifyReferralForm(
        ReusableCode reusableAction,
        Map<String, List<String>> dropdownOptions,
        Map<String, String> data) {

    reusableAction.inputFieldByLabel("Reference No",
            data.get("Reference No"));

    reusableAction.inputFieldByLabel("Reference Date",
            data.get("Reference date"));

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Referral Name", data.get("Referral name"));

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Clinic Referred to", data.get("Clinic Referred to"));

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Doctor Referred to", data.get("Doctor Referred to"));

    reusableAction.buttonClick("Save");
}

public void verifySubsidySubCategory(
        ReusableCode reusableAction,
        Map<String, List<String>> dropdownOptions,
        Map<String, String> data) {

    validateAndSelectSearchableDropdown(
            reusableAction,
            dropdownOptions,
            "Subsidy Approved By",
            data.get("SubsidyApprovedBy"));

    reusableAction.inputFieldByLabel("% Subsidy granted",
            data.get("Subsidy granted"));

    validateAndSelectSearchableDropdown(
            reusableAction,
            dropdownOptions,
            "Reason",
            data.get("Subsidy Reason"));

    reusableAction.inputFieldByLabel("Remarks",
            data.get("Subsidy remarks"));

    reusableAction.buttonClick("Save");
}


public void verifyCorporateCategory(
        ReusableCode reusableAction,
        Map<String, List<String>> dropdownOptions,
        Map<String, String> data) {

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Corporate Name", data.get("Corporate name"));

    reusableAction.inputFieldByLabel("Document Ref.No",
            data.get("Document Ref.no"));

    validateAndSelectDropdown(reusableAction, dropdownOptions,
            "Employee Grade", data.get("Employee grade"));

    reusableAction.inputFieldByLabel("Employee Code",
            data.get("Employee code"));

    reusableAction.inputFieldByLabel("Claim ID",
            data.get("Claim id"));

    reusableAction.textAreaFieldByLabel(
            "Remarks / Registration No",
            data.get("Corporate remarks"));

    reusableAction.buttonClick("Submit");
}


private void validateAndSelectDropdown(
        ReusableCode reusableAction,
        Map<String, List<String>> dropdownOptions,
        String label,
        String value) {

    List<String> options = dropdownOptions.get(label);

    if (options == null) {
        throw new RuntimeException(
                "Dropdown '" + label +
                "' not found in DropdownOptions sheet.");
    }

    if (!options.contains(value)) {
        throw new RuntimeException(
                "Option '" + value +
                "' not found under '" + label +
                "' in DropdownOptions sheet.");
    }

    reusableAction.selectDropdownByLabel(label, value);
}

private void validateAndSelectSearchableDropdown(
        ReusableCode reusableAction,
        Map<String, List<String>> dropdownOptions,
        String label,
        String value) {

    List<String> options = dropdownOptions.get(label);

    if (options == null) {
        throw new RuntimeException(
                "Dropdown '" + label +
                "' not found in DropdownOptions sheet.");
    }

    if (!options.contains(value)) {
        throw new RuntimeException(
                "Option '" + value +
                "' not found under '" + label +
                "' in DropdownOptions sheet.");
    }

    reusableAction.selectSearchableDropdownByLabel(label, value);
}

private void validateAndSelectForceDropdown(
        OPRegistrationPage opPage,
        Map<String, List<String>> dropdownOptions,
        String label,
        String value) {

    List<String> options = dropdownOptions.get(label);

    if (options == null) {
        throw new RuntimeException(
                "Dropdown '" + label +
                "' not found in DropdownOptions sheet.");
    }

    if (!options.contains(value)) {
        throw new RuntimeException(
                "Option '" + value +
                "' not found under '" + label +
                "' in DropdownOptions sheet.");
    }

    opPage.selectSomeForceDropdownByLabel(label, value);
}



}