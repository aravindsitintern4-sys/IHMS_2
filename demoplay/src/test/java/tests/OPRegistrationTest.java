package tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
   
    // GET THE TEST DATA FROM EXCEL WITHOUT DROPDOWN VALIDATION
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
    @Test
    public void verifyPAYOPRegistration() throws IOException {

    int lastColumn = Excel.getLastDataColumn("opRegistrationData");
    System.out.println("LAST COLUMN COUNT: " + lastColumn);

    for (int column = 1; column <= lastColumn; column++) {  
        
        // JsonUtil.clearJson();
        // JsonUtil.reloadJson(); 

        System.out.println("Executing Person Data in Column: " + column); 

        Map<String, String> data = Excel.getTestData("opRegistrationData", column);

        // Map<String, String> data = Excel.getTestData("opRegistrationData");

        DashboardPage dashboard = new DashboardPage(page);

        Page ihmsPage = page.waitForPopup(() -> {
            dashboard.clickDashboardOption("IHMS");
        });

        ReusableCode reusableAction = new ReusableCode(ihmsPage);

        reusableAction.clickMenuAndSelectSubMenu("OP Modules","Outpatient Registration");                         

        ihmsPage.locator("//select").first().waitFor();

        // CAPTURE ALL DROPDOWN AND STORE IT IN JASON
        DropdownReader dropdownReader = new DropdownReader(ihmsPage);
        // dropdownReader.captureAllDropdowns();
        // dropdownReader.captureAllCustomDropdowns();
        dropdownReader.captureAllDropdownOptions();     

        OPRegistrationPage opPage = new OPRegistrationPage(ihmsPage);

        validateAndSelectDropdown(reusableAction,"Pay/Free",data.get("PayFree"));
        dropdownReader.refreshDropdown("Patient Category:");
        System.out.println(JsonUtil.getOptions("Patient Category:"));  
        String PayFreeSelection = data.get("PayFree");
        if ((!"CAMP".equalsIgnoreCase(PayFreeSelection))){
            validateAndSelectDropdown(reusableAction,"Patient Type",data.get("PatientType"));
        }

        reusableAction.inputFieldByLabel("First Name", data.get("FirstName"));
        reusableAction.inputFieldByLabel("Last Name", data.get("LastName"));
        reusableAction.inputFieldByLabel("DOB", data.get("Date of Birth"));
        reusableAction.selectRadioByLabel("Gender", data.get("Gender"));
        // ihmsPage.waitForTimeout(1000);
        dropdownReader.refreshDropdown("Next of Kin");
        validateAndSelectDropdown(reusableAction, "Next of Kin", data.get("NextOfKinType"));
        reusableAction.inputFieldByLabel("Next of Kin", data.get("NextOfKinName"));

        reusableAction.selectRadioByLabel("Normal / Referral",data.get("ReferralType"));
        if ("Referral".equalsIgnoreCase(data.get("ReferralType"))) {
            dropdownReader.captureDropdowns("Referral Name","Clinic Referred to","Doctor Referred to");
            verifyReferralForm(reusableAction, data,dropdownReader,opPage);
        }
                             
        reusableAction.selectRadioByLabel("Nationality",data.get("Nationality"));
        reusableAction.inputFieldByLabel("Door / Street", data.get("DoorStreet"));
        reusableAction.inputFieldByLabel("Locality", data.get("locality"));
        reusableAction.inputFieldByLabel("City", data.get("City"));
        reusableAction.buttonClick("Area");
        dropdownReader.refreshDropdown("City /");
        validateAndSelectForceDropdown(opPage,"City /", data.get("Area"));
        reusableAction.inputFieldByLabel("PinCode", data.get("PinCode"));
        validateAndSelectForceDropdown(opPage,"Taluk", data.get("Taluk"));
        reusableAction.inputFieldByLabel("Mobile No", data.get("MobileNo"));
        reusableAction.inputFieldByLabel("Email", data.get("Email"));
        validateAndSelectDropdown(reusableAction,"Purpose Of Visit",data.get("PurposeVisit"));

        if (("PAY".equalsIgnoreCase(PayFreeSelection))){
        validateAndSelectDropdown(reusableAction,"Mobile App Consent Form",data.get("ConsentForm"));
        validateAndSelectDropdown(reusableAction,"Assign Doctor:",data.get("Assign doctor"));
        }

        // PATIENT CATEGORY
        validateAndSelectDropdown(reusableAction,"Patient Category:",data.get("Patient category"));
        if ("CORPORATE".equalsIgnoreCase(data.get("Patient category"))) {
            dropdownReader.captureDropdowns("Corporate Name","Employee Grade");
            verifyCorporateCategory(reusableAction, data,dropdownReader);
        }
        else if ("COMMUNITY CENTER".equalsIgnoreCase(data.get("Patient category")) || ("VISION CENTER".equalsIgnoreCase(data.get("Patient category")))) {
                dropdownReader.captureDropdowns("Referral Name","Clinic Referred to","Doctor Referred to");
                verifyReferralForm(reusableAction, data,dropdownReader,opPage);       
        }

        String patientCategory = data.get("Patient category");
        if ((!"CORPORATE".equalsIgnoreCase(patientCategory)) && (!"FREE".equalsIgnoreCase(PayFreeSelection)) && (!"CAMP".equalsIgnoreCase(PayFreeSelection))){
            // PATIENT SUB CATEGORY 
            validateAndSelectDropdown(reusableAction,"Patient Sub Category:",data.get("PatientSubCategory"));
            if ("Subsidy".equalsIgnoreCase(data.get("PatientSubCategory"))) {
                dropdownReader.captureDropdowns("Subsidy Approved By","Reason");
                verifySubsidySubCategory(reusableAction, data);           
            }
            else if ("Concession".equalsIgnoreCase(data.get("PatientSubCategory"))) {
                dropdownReader.captureDropdowns("Concession Approved By","Reason");
                verifyConcessionSubCategory(reusableAction, data);                            
            }              
        }

        // PAYMENT TYPE
        if ((!"CORPORATE".equalsIgnoreCase(patientCategory)) && (!"FREE".equalsIgnoreCase(PayFreeSelection)) && (!"CAMP".equalsIgnoreCase(PayFreeSelection))){
             validateAndSelectDropdown(reusableAction,"Payment Type:",data.get("PaymentType"));
        }
        
        reusableAction.buttonClick("Submit");

       // NEXT PATIENT DATA INTEGRATION'S CONFIRMATION (VIA COLUMN COUNT)
       System.out.println("Completed Column: " + column);
    }
       
    }


    // public void verifyReferralForm(ReusableCode reusableAction,Map<String, String> data, DropdownReader dropdownReader, OPRegistrationPage opPage) throws IOException {
    //     reusableAction.inputFieldByLabel("Reference No", data.get("Reference No"));
    //     reusableAction.inputFieldByLabel("Reference Date",data.get("Reference date"));
    //     newValidateAndSelectForceDropdown(opPage,"Referral Name",data.get("Referral name"));
    //     validateAndSelectDropdown(reusableAction,"Clinic Referred to",data.get("Clinic Referred to")); 
    //     validateAndSelectDropdown(reusableAction,"Doctor Referred to",data.get("Doctor Referred to"));   
    //     reusableAction.buttonClick("Save");   
    // }

    // REFERRAL FORM
    public void verifyReferralForm(ReusableCode reusableAction,Map<String, String> data,DropdownReader dropdownReader,OPRegistrationPage opPage) throws IOException {
        reusableAction.inputFieldByLabel("Reference No", data.get("Reference No"));
        reusableAction.inputFieldByLabel("Reference Date", data.get("Reference date"));

        // DIFFERENT OPTIONS ARE PRESENT BASED ON PATIENT CATEGORY
        String patientCategory = data.get("Patient category");
        if ("COMMUNITY CENTER".equalsIgnoreCase(patientCategory)) {
            newValidateAndSelectForceDropdown(opPage,"Referral Name",data.get("Referral name from community center"));
        } else if ("VISION CENTER".equalsIgnoreCase(patientCategory)) {
            newValidateAndSelectForceDropdown(opPage,"Referral Name",data.get("Referral name from vision center"));
        } else {
            newValidateAndSelectForceDropdown(opPage,"Referral Name",data.get("Referral name"));
        }

        // DIFFERENT OPTIONS ARE PRESENT BASED ON PAY/FREE
        String PayFreeSelection = data.get("PayFree");
        if (("FREE".equalsIgnoreCase(PayFreeSelection)) || ("CAMP".equalsIgnoreCase(PayFreeSelection))){
            validateAndSelectDropdown(reusableAction,"Clinic Referred to",data.get("Clinic Referred to Free or Camp"));
        }else{
            validateAndSelectDropdown(reusableAction,"Clinic Referred to",data.get("Clinic Referred to"));
        }

        validateAndSelectDropdown(reusableAction,"Doctor Referred to",data.get("Doctor Referred to"));
        reusableAction.buttonClick("Save");
    }
      
    // SUBSIDY
    public void verifySubsidySubCategory(ReusableCode reusableAction, Map<String, String> data) {
        validateAndSelectSearchableDropdown(reusableAction,"Subsidy Approved By",data.get("SubsidyApprovedBy"));
        reusableAction.inputFieldByLabel("% Subsidy granted",data.get("Subsidy granted"));
        validateAndSelectSearchableDropdown(reusableAction,"Reason",data.get("Subsidy Reason"));
        reusableAction.inputFieldByLabel("Remarks",data.get("Subsidy remarks"));
        reusableAction.buttonClick("Save");
    }

    // CONCESSION
    public void verifyConcessionSubCategory(ReusableCode reusableAction, Map<String, String> data) {
        validateAndSelectSearchableDropdown(reusableAction,"Concession Approved By",data.get("ConcessionApprovedBy"));
        reusableAction.inputFieldByLabel("% Concession granted",data.get("Concession granted"));
        validateAndSelectSearchableDropdown(reusableAction,"Reason",data.get("Concession Reason"));
        reusableAction.inputFieldByLabel("Remarks",data.get("Concession remarks"));
        reusableAction.buttonClick("Save");
    }

    // CORPORATE
    public void verifyCorporateCategory(ReusableCode reusableAction,Map<String, String> data, DropdownReader dropdownReader) throws IOException {
        validateAndSelectDropdown(reusableAction,"Corporate Name",data.get("Corporate name"));
        reusableAction.inputFieldByLabel("Document Ref.No",data.get("Document Ref.no"));
        dropdownReader.refreshDropdown("Employee Grade");
        validateAndSelectDropdown(reusableAction,"Employee Grade",data.get("Employee grade"));
        reusableAction.inputFieldByLabel("Employee Code",data.get("Employee code"));
        reusableAction.inputFieldByLabel("Claim ID",data.get("Claim id"));
        reusableAction.textAreaFieldByLabel("Remarks / Registration No",data.get("Corporate remarks"));
        reusableAction.buttonClick("Submit");
    }

    private void validateAndSelectDropdown(ReusableCode reusableAction,String label,String value) {
        if (!JsonUtil.containsOption(label, value)) {
            throw new RuntimeException("Option '" + value +"' not found under '" + label + "' in JSON.");
        }
     reusableAction.selectDropdownByLabel(label, value);
    }   
             
    private void validateAndSelectSearchableDropdown(ReusableCode reusableAction,String label,String value) {
        if (!JsonUtil.containsOption(label, value)) {
            throw new RuntimeException("Option '" + value +"' not found under '" + label + "' in JSON.");
        } 
        reusableAction.selectSearchableDropdownByLabel(label, value);
    }

    private void validateAndSelectForceDropdown(OPRegistrationPage opPage,String label,String value) {
        if (!JsonUtil.containsOption(label, value)) {
            throw new RuntimeException("Option '" + value +"' not found under '" + label + "' in JSON.");
        }
        opPage.selectSomeForceDropdownByLabel(label, value);
    }

    private void newValidateAndSelectForceDropdown(OPRegistrationPage opPage,String label,String value) {
        if (!JsonUtil.containsOption(label, value)) {
            throw new RuntimeException("Option '" + value +"' not found under '" + label + "' in JSON.");
        }
        opPage.newChangeDropdown(label, value);
    }



    

    
}

















// //  GET TEST DATA FROM EXCEL AND COMAPARE THE DROPDOWN VALUES FROM EXCEL
//     @Test
//     public void verifyPAYOPRegistration() throws IOException {

//         // READ THE TEST DATA
//         Map<String, String> data = Excel.getTestData("opRegistrationData");

//         DashboardPage dashboard = new DashboardPage(page);

//         Page ihmsPage = page.waitForPopup(() -> {
//             dashboard.clickDashboardOption("IHMS");
//         });

//         ReusableCode reusableAction = new ReusableCode(ihmsPage);

//         reusableAction.clickMenuAndSelectSubMenu("OP Modules","Outpatient Registration");

//         ihmsPage.locator("//select").first().waitFor();

//         OPRegistrationPage opPage = new OPRegistrationPage(ihmsPage);

//         // STORE THE DROPDOWNS IN EXCEL
//         DropdownReader dropdownReader = new DropdownReader(ihmsPage);
//         dropdownReader.captureAllDropdowns();
//         dropdownReader.captureAllCustomDropdowns();
//         dropdownReader.saveDropdownOptions();

//         // READ THE DROPDOWN VALUES FROM EXCEL
//         Map<String, List<String>> dropdownOptions =Excel.getDropdownOptions("DropdownOptions");

//         validateAndSelectDropdown(reusableAction, dropdownOptions,"Pay/Free", data.get("PayFree"));
//         validateAndSelectDropdown(reusableAction, dropdownOptions,"Patient Type", data.get("PatientType"));
//         reusableAction.inputFieldByLabel("First Name", data.get("FirstName"));
//         reusableAction.inputFieldByLabel("Last Name", data.get("LastName"));
//         reusableAction.inputFieldByLabel("DOB", data.get("Date of Birth"));
//         reusableAction.selectRadioByLabel("Gender", data.get("Gender"));
    
//         dropdownOptions = refreshDropdownOptions(dropdownReader, "Next of Kin");
//         validateAndSelectDropdown(reusableAction,dropdownOptions,"Next of Kin",data.get("NextOfKinType"));

//         reusableAction.inputFieldByLabel("Next of Kin",data.get("NextOfKinName"));
//         reusableAction.selectRadioByLabel("Normal / Referral",data.get("ReferralType"));
//             if ("Referral".equalsIgnoreCase(data.get("ReferralType"))) {
//                 dropdownReader.captureDropdownsNew("Referral Name","Clinic Referred to","Doctor Referred to");
//                 dropdownReader.saveDropdownOptions();
//                 dropdownOptions = Excel.getDropdownOptions("DropdownOptions");
//                 verifyReferralForm(opPage,reusableAction,dropdownOptions,data);
//             }                      
                  
//         reusableAction.selectRadioByLabel("Nationality",data.get("Nationality"));
//         reusableAction.inputFieldByLabel("Door / Street",data.get("DoorStreet"));
//         reusableAction.inputFieldByLabel("Locality",data.get("locality"));
//         reusableAction.inputFieldByLabel("City",data.get("City"));
            
//         reusableAction.buttonClick("Area");         
//         dropdownReader.captureNormalDropdown("City /");
//         dropdownReader.saveDropdownOptions();
//         dropdownOptions = Excel.getDropdownOptions("DropdownOptions");
//         validateAndSelectForceDropdown(opPage,dropdownOptions,"City /",data.get("Area"));

//         reusableAction.inputFieldByLabel("PinCode",data.get("PinCode"));
//         validateAndSelectForceDropdown(opPage,dropdownOptions,"Taluk",data.get("Taluk"));
//         reusableAction.inputFieldByLabel("Mobile No",data.get("MobileNo"));
//         reusableAction.inputFieldByLabel("Email",data.get("Email"));
//         validateAndSelectDropdown(reusableAction,dropdownOptions,"Purpose Of Visit",data.get("PurposeVisit"));
//         validateAndSelectDropdown(reusableAction,dropdownOptions,"Mobile App Consent Form",data.get("ConsentForm"));
//         validateAndSelectDropdown(reusableAction,dropdownOptions,"Assign Doctor:",data.get("Assign doctor"));
//         // validateAndSelectDropdown(reusableAction,dropdownOptions,"Patient Category:",data.get("Patient category"));
//         // if ("CORPORATE".equalsIgnoreCase(data.get("Patient category"))) {
//         //         dropdownOptions = refreshDropdownOptions(dropdownReader, "Corporate Name");
//         //         verifyCorporateCategory(reusableAction,dropdownOptions,data,dropdownReader);
//         //     }
//         // else if ("COMMUNITY CENTER".equalsIgnoreCase(data.get("Patient category")) || ("VISION CENTER".equalsIgnoreCase(data.get("Patient category")))) {
//         //         dropdownReader.captureDropdownsNew("Referral Name","Clinic Referred to","Doctor Referred to");
//         //         dropdownReader.saveDropdownOptions();
//         //         dropdownOptions = Excel.getDropdownOptions("DropdownOptions");
//         //         verifyReferralForm(opPage,reusableAction,dropdownOptions,data);
//         //     }
           
//         validateAndSelectDropdown(reusableAction, dropdownOptions,"Patient Category:", data.get("Patient category"));

//         String patientCategory = data.get("Patient category");

//         if ("CORPORATE".equalsIgnoreCase(patientCategory)) {
//             dropdownOptions = refreshDropdownOptions(dropdownReader, "Corporate Name");
//             verifyCorporateCategory(reusableAction, dropdownOptions, data, dropdownReader);
       
//         } else if ("COMMUNITY CENTER".equalsIgnoreCase(patientCategory)
//                 || "VISION CENTER".equalsIgnoreCase(patientCategory)) {

//             dropdownReader.captureDropdownsNew("Referral Name","Clinic Referred to","Doctor Referred to");

//             dropdownReader.saveDropdownOptions();
//             dropdownOptions = Excel.getDropdownOptions("DropdownOptions");      

//             verifyReferralForm(opPage, reusableAction, dropdownOptions, data);

//         }


        
     
//         // validateAndSelectDropdown(reusableAction,dropdownOptions,"Patient Sub Category:",data.get("PatientSubCategory"));
//         //     if ("Subsidy".equalsIgnoreCase(data.get("PatientSubCategory"))) {
//         //         dropdownReader.captureDropdowns("Subsidy Approved By","Reason");
//         //         dropdownReader.saveDropdownOptions();
//         //         dropdownOptions = Excel.getDropdownOptions("DropdownOptions");
//         //         verifySubsidySubCategory(reusableAction,dropdownOptions,data);
//         //     }
     
//         reusableAction.buttonClick("Submit");
//     }
   
//     // REFERRAL FORM
//     public void verifyReferralForm(
//             OPRegistrationPage opPage,
//             ReusableCode reusableAction,
//             Map<String, List<String>> dropdownOptions,
//             Map<String, String> data) {           

//         reusableAction.inputFieldByLabel("Reference No",data.get("Reference No"));
//         reusableAction.inputFieldByLabel("Reference Date",data.get("Reference date"));
//         selectNewChangeDropdownByLabel(opPage,dropdownOptions,"Referral Name",data.get("Referral name"));
//         validateAndSelectDropdown(reusableAction,dropdownOptions,"Clinic Referred to",data.get("Clinic Referred to"));
//         validateAndSelectDropdown(reusableAction,dropdownOptions,"Doctor Referred to",data.get("Doctor Referred to"));   
//         reusableAction.buttonClick("Save");
//     }

//     // SUBSIDY 
//     public void verifySubsidySubCategory(
//             ReusableCode reusableAction,
//             Map<String, List<String>> dropdownOptions,
//             Map<String, String> data) {

//         validateAndSelectSearchableDropdown(reusableAction,dropdownOptions,"Subsidy Approved By",data.get("SubsidyApprovedBy"));
//         reusableAction.inputFieldByLabel("% Subsidy granted",data.get("Subsidy granted"));
//         validateAndSelectSearchableDropdown(reusableAction,dropdownOptions,"Reason",data.get("Subsidy Reason"));
//         reusableAction.inputFieldByLabel("Remarks",data.get("Subsidy remarks"));
//         reusableAction.buttonClick("Save");
//     }
     
//     // CONCESSION
//     public void verifyConcessionSubCategory(
//             ReusableCode reusableAction,
//             Map<String, List<String>> dropdownOptions,
//             Map<String, String> data) {

//         validateAndSelectSearchableDropdown(reusableAction,dropdownOptions,"Concession Approved By",data.get("ConcessionApprovedBy"));
//         reusableAction.inputFieldByLabel("% Concession granted",data.get("Concession granted"));
//         validateAndSelectSearchableDropdown(reusableAction,dropdownOptions,"Reason",data.get("Concession Reason"));
//         reusableAction.inputFieldByLabel("Remarks",data.get("Concession remarks"));
//         reusableAction.buttonClick("Save");
//     }

//     // CORPORATE
//     public void verifyCorporateCategory(
//             ReusableCode reusableAction,
//             Map<String, List<String>> dropdownOptions,
//             Map<String, String> data, DropdownReader dropdownReader) throws IOException{

//         validateAndSelectDropdown(reusableAction, dropdownOptions,"Corporate Name", data.get("Corporate name"));
//         reusableAction.inputFieldByLabel("Document Ref.No",data.get("Document Ref.no"));
//         // validateAndSelectDropdown(reusableAction, dropdownOptions,"Employee Grade", data.get("Employee grade"));
//         reusableAction.inputFieldByLabel("Employee Code",data.get("Employee code"));
//         reusableAction.inputFieldByLabel("Claim ID",data.get("Claim id"));
//         reusableAction.textAreaFieldByLabel("Remarks / Registration No",data.get("Corporate remarks"));
//         reusableAction.buttonClick("Submit");
//     }
   
//     private void validateOption(Map<String, List<String>> dropdownOptions,String label,String value) {

//         List<String> options = dropdownOptions.get(label);
//         if (options == null) {
//             throw new RuntimeException("Dropdown '" + label +"' not found in DropdownOptions sheet.");
//         }
//         boolean found = options.stream()
//             .map(String::trim)
//             .anyMatch(option -> option.equalsIgnoreCase(value.trim()));
//         if (!found) {
//             throw new RuntimeException("Option '" + value +"' not found under '" + label +"' in DropdownOptions sheet.");
//         }
//     }
            
//     private void validateAndSelectDropdown(
//             ReusableCode reusableAction,
//             Map<String, List<String>> dropdownOptions,
//             String label,
//             String value) {

//         validateOption(dropdownOptions, label, value);
//         reusableAction.selectDropdownByLabel(label, value);
//     }
        
//     private void validateAndSelectSearchableDropdown(
//             ReusableCode reusableAction,
//             Map<String, List<String>> dropdownOptions,
//             String label,
//             String value) {
                
//         validateOption(dropdownOptions, label, value);
//         reusableAction.selectSearchableDropdownByLabel(label, value);
//     }

//     private void validateAndSelectForceDropdown(
//             OPRegistrationPage opPage,
//             Map<String, List<String>> dropdownOptions,
//             String label,
//             String value) {
        
//         validateOption(dropdownOptions, label, value);
//         opPage.selectSomeForceDropdownByLabel(label, value);
//     }

//     private void selectNewChangeDropdownByLabel(
//         OPRegistrationPage opPage,
//         Map<String, List<String>> dropdownOptions,
//         String label,
//         String value) {

//         validateOption(dropdownOptions, label, value);
//         opPage.newChangeDropdown(label, value);
//     }

//     private Map<String, List<String>> refreshDropdownOptions(
//             DropdownReader dropdownReader,
//             String... labels) throws IOException {

//         dropdownReader.captureDropdowns(labels);
//         dropdownReader.saveDropdownOptions();
//         return Excel.getDropdownOptions("DropdownOptions");
//     }

//     }