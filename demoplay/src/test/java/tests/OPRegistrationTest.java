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
// @Test(priority = 1)
public void verifyOPRegistration() throws IOException {
    
    int lastColumn = Excel.getLastDataColumn("opRegistrationData");
    System.out.println("Last Column = " + lastColumn);

    for (int column = 1; column <= lastColumn; column++) {

        // INITIAL METHOS CALLING
        TestContext ctx = CallInitialMethods("OP Modules", "Outpatient Registration","opRegistrationData",column);

        boolean isColumnEmpty = ctx.data.values().stream().allMatch(value -> value == null || value.trim().isEmpty());
        if (isColumnEmpty) {
            System.out.println("Column " + column + " is empty. Stopping execution.");
            break;
        }
        System.out.println("Executing Person Data in Column: " + column);

        ctx.dropdownReader.captureAllDropdownOptions(); 

        validateAndSelectDropdown(ctx.reusableAction,"Pay/Free",ctx.data.get("PayFree"));

        // REFRESH PATIENT CATEGORY BASED ON PAY/FREE/CAMP
        ctx.dropdownReader.refreshDropdown("Patient Category:");
        System.out.println(JsonUtil.getOptions("Patient Category:"));  
       
        // PATIENT TYPE
        String PayFreeSelection = ctx.data.get("PayFree");
        if ((!"CAMP".equalsIgnoreCase(PayFreeSelection))){
            validateAndSelectDropdown(ctx.reusableAction,"Patient Type",ctx.data.get("PatientType"));
        }

        ctx.reusableAction.inputFieldByLabel("First Name", ctx.data.get("FirstName"));

        // NOT MANDATORY FIELD
        String LastName = ctx.data.get("LastName");
        if (ctx.reusableAction.hasValue(LastName))
        ctx.reusableAction.inputFieldByLabel("Last Name", LastName);

        ctx.reusableAction.inputFieldByLabel("DOB", ctx.data.get("Date of Birth"));
        ctx.reusableAction.selectRadioByLabel("Gender", ctx.data.get("Gender"));
        // ihmsPage.waitForTimeout(1000);

        // NEXT OF KIN
        ctx.dropdownReader.refreshDropdown("Next of Kin");
        validateAndSelectDropdown(ctx.reusableAction, "Next of Kin", ctx.data.get("NextOfKinType"));
        ctx.reusableAction.inputFieldByLabel("Next of Kin", ctx.data.get("NextOfKinName"));

        // NORMAL / REFERRAL TYPE
        ctx.reusableAction.selectRadioByLabel("Normal / Referral",ctx.data.get("ReferralType"));
        if ("Referral".equalsIgnoreCase(ctx.data.get("ReferralType"))) {
            ctx.dropdownReader.captureDropdowns("Referral Name","Clinic Referred to","Doctor Referred to");
            verifyReferralForm(ctx.reusableAction, ctx.data,ctx.dropdownReader,ctx.opPage);
        }
         
        
        // reusableAction.selectRadioByLabel("Nationality",data.get("Nationality"));
        // reusableAction.inputFieldByLabel("Door / Street", data.get("DoorStreet"));
        // reusableAction.inputFieldByLabel("Locality", data.get("locality"));
        // reusableAction.inputFieldByLabel("City", data.get("City"));
        // reusableAction.buttonClick("Area");
        // dropdownReader.refreshDropdown("City /");
        // validateAndSelectForceDropdown(opPage,"City /", data.get("Area"));
        // reusableAction.inputFieldByLabel("PinCode", data.get("PinCode"));
        // validateAndSelectForceDropdown(opPage,"Taluk", data.get("Taluk"));
        // reusableAction.inputFieldByLabel("Mobile No", data.get("MobileNo"));
        // reusableAction.inputFieldByLabel("Email", data.get("Email"));

        // FOR NON MANDATORY FIELD VALIDATION PURPOSE
        String doorStreet = ctx.data.get("DoorStreet");
        String locality = ctx.data.get("locality");
        String city = ctx.data.get("City");
        String area = ctx.data.get("Area");
        String pinCode = ctx.data.get("PinCode");
        String taluk = ctx.data.get("Taluk");
        String mobileNo = ctx.data.get("MobileNo");
        String email = ctx.data.get("Email");

        String nationality = ctx.data.get("Nationality");
        ctx.reusableAction.selectRadioByLabel("Nationality",nationality);
        if (("Foreigner".equalsIgnoreCase(nationality))){
            verifyForeignerNationality(ctx.reusableAction,ctx.data,ctx.dropdownReader);
        }

        if ((!"Foreigner".equalsIgnoreCase(nationality))){

            if (ctx.reusableAction.hasValue(doorStreet))
            ctx.reusableAction.inputFieldByLabel("Door / Street", doorStreet);

            if (ctx.reusableAction.hasValue(locality))
                ctx.reusableAction.inputFieldByLabel("Locality", locality);

            if (ctx.reusableAction.hasValue(city))
                ctx.reusableAction.inputFieldByLabel("City", city);

            if (ctx.reusableAction.hasValue(area)) {
                ctx.reusableAction.buttonClick("Area");
                ctx.dropdownReader.refreshDropdown("City /");
                validateAndSelectForceDropdown(ctx.opPage, "City /", area);
            }

            if (ctx.reusableAction.hasValue(pinCode))     
                ctx.reusableAction.inputFieldByLabel("PinCode", pinCode);

            // TALUK -----> MANDATORY FIELD
            validateAndSelectForceDropdown(ctx.opPage, "Taluk", taluk);

        }

        if (ctx.reusableAction.hasValue(mobileNo))
            ctx.reusableAction.inputFieldByLabel("Mobile No", mobileNo);
             
        if (ctx.reusableAction.hasValue(email))
            ctx.reusableAction.inputFieldByLabel("Email", email); 
        

        ctx.dropdownReader.refreshDropdown("Purpose Of Visit");
        validateAndSelectDropdown(ctx.reusableAction,"Purpose Of Visit",ctx.data.get("PurposeVisit"));


        // CONSENT FORM AND ASSIGN DOCTOR
        if (("PAY".equalsIgnoreCase(PayFreeSelection))){
            validateAndSelectDropdown(ctx.reusableAction,"Mobile App Consent Form",ctx.data.get("ConsentForm"));
            String mobileConsentForm = ctx.data.get("ConsentForm");
            if (("Yes".equalsIgnoreCase(mobileConsentForm))){
                ctx.reusableAction.inputFieldByLabel("Mobile App Consent Form", ctx.data.get("MobileNo"));
            }
            validateAndSelectDropdown(ctx.reusableAction,"Assign Doctor:",ctx.data.get("Assign doctor"));
        }


        // CHECK PATIENT DETAILS POPUP VISIBILITY (FOR ALREADY REGISTERED PATIENT)
        CheckPatientDetailsPopupVisibility(ctx.reusableAction);


        // PATIENT CATEGORY
        String RefferralType = ctx.data.get("ReferralType");
        validateAndSelectDropdown(ctx.reusableAction,"Patient Category:",ctx.data.get("Patient category"));
        if ("CORPORATE".equalsIgnoreCase(ctx.data.get("Patient category"))) {
            ctx.dropdownReader.captureDropdowns("Corporate Name","Employee Grade");
            verifyCorporateCategory(ctx.reusableAction, ctx.data,ctx.dropdownReader);
        }  
        else if (!"Referral".equalsIgnoreCase(RefferralType)){
            if ("COMMUNITY CENTER".equalsIgnoreCase(ctx.data.get("Patient category")) || ("VISION CENTER".equalsIgnoreCase(ctx.data.get("Patient category")))) {
                ctx.dropdownReader.captureDropdowns("Referral Name","Clinic Referred to","Doctor Referred to");
                verifyReferralForm(ctx.reusableAction, ctx.data,ctx.dropdownReader,ctx.opPage);
            }
        }

        // PATIENT SUB CATEGORY
        String patientCategory = ctx.data.get("Patient category");
        if ((!"CORPORATE".equalsIgnoreCase(patientCategory)) && (!"FREE".equalsIgnoreCase(PayFreeSelection)) && (!"CAMP".equalsIgnoreCase(PayFreeSelection))){

            String patientSubCategory = ctx.data.get("PatientSubCategory");
            if (patientSubCategory != null && !patientSubCategory.trim().isEmpty()) {
                validateAndSelectDropdown(ctx.reusableAction, "Patient Sub Category:", patientSubCategory);

                if ("Subsidy".equalsIgnoreCase(patientSubCategory)) {
                    ctx.dropdownReader.captureDropdowns("Subsidy Approved By", "Reason");
                    verifySubsidySubCategory(ctx.reusableAction, ctx.data);
                } else if ("Concession".equalsIgnoreCase(patientSubCategory)) {
                    ctx.dropdownReader.captureDropdowns("Concession Approved By", "Reason");
                    verifyConcessionSubCategory(ctx.reusableAction, ctx.data);
                }
            }
        }
    

        //     // PATIENT SUB CATEGORY 
        //     validateAndSelectDropdown(reusableAction,"Patient Sub Category:",data.get("PatientSubCategory"));
        //     if ("Subsidy".equalsIgnoreCase(data.get("PatientSubCategory"))) {
        //         dropdownReader.captureDropdowns("Subsidy Approved By","Reason");
        //         verifySubsidySubCategory(reusableAction, data);           
        //     }
        //     else if ("Concession".equalsIgnoreCase(data.get("PatientSubCategory"))) {
        //         dropdownReader.captureDropdowns("Concession Approved By","Reason");
        //         verifyConcessionSubCategory(reusableAction, data);                                 
        //     }             
        // }


        // PAYMENT TYPE
        if ((!"CORPORATE".equalsIgnoreCase(patientCategory)) && (!"FREE".equalsIgnoreCase(PayFreeSelection)) && (!"CAMP".equalsIgnoreCase(PayFreeSelection))){
            // dropdownReader.refreshDropdown("Payment Type:");
            validateAndSelectDropdown(ctx.reusableAction,"Payment Type:",ctx.data.get("PaymentType"));
            String PaymentType = ctx.data.get("PaymentType");
            if (("OTHERS".equalsIgnoreCase(PaymentType))){
                ctx.dropdownReader.captureDropdownWithoutLabel("Select Counter");
                validateAndSelectCounter(ctx.reusableAction,"Select Counter",ctx.data.get("selectCounter"));
                ctx.reusableAction.buttonClick("Select");                  
                ctx.reusableAction.buttonClick("Yes");  
                ctx.reusableAction.testClick("Credit Card");
                // reusableAction.closeIcon("Select Counter");    
            }
            else{    
                ctx.reusableAction.linkIcon("+"); 
            }
        }
        
        
        ctx.reusableAction.buttonClick("Submit");

        // AFTER SUBMIT STORE THE UIN,MRN,LOCATION OF PATIENT IN EXCEL
        if (ctx.reusableAction.isPopupVisible("Saved Successfully")) {

            String mrn = ctx.reusableAction.getPopupValue("MRN");
            String uin = ctx.reusableAction.getPopupValue("UIN");
            String location = ctx.reusableAction.getPopupValue("Location");

            Excel.updateCell("opRegistrationData", ctx.column, "MRN", mrn);
            Excel.updateCell("opRegistrationData", ctx.column, "UIN", uin);
            Excel.updateCell("opRegistrationData", ctx.column, "Location", location);

            ctx.reusableAction.buttonClick("×");
        }

        // HOME ICON TEST
        ctx.reusableAction.homeIcon();

        // NEXT PATIENT DATA INTEGRATION'S CONFIRMATION (VIA COLUMN COUNT)
        System.out.println("Completed Column: " + ctx.column);
    }
    
    }



    public void CheckPatientDetailsPopupVisibility(ReusableCode reusableAction) {
        if (reusableAction.isPopupVisible("Patient Details")) {
                reusableAction.closeIcon("Patient Details");
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

    // NATIONALITY
    public void verifyForeignerNationality(ReusableCode reusableAction,Map<String, String> data, DropdownReader dropdownReader) throws IOException {
        dropdownReader.refreshDropdown("Country");
        validateAndSelectDropdown(reusableAction,"Country",data.get("Foreign country"));
        reusableAction.inputFieldByLabel("Zip Code",data.get("zip code"));
    }


    public void validateAndSelectDropdown(ReusableCode reusableAction,String label,String value) {
        if (!JsonUtil.containsOption(label, value)) {
            throw new RuntimeException("Option '" + value +"' not found under '" + label + "' in JSON.");
        }
     reusableAction.selectDropdownByLabel(label, value);
    }   
             
    public void validateAndSelectSearchableDropdown(ReusableCode reusableAction,String label,String value) {
        if (!JsonUtil.containsOption(label, value)) {
            throw new RuntimeException("Option '" + value +"' not found under '" + label + "' in JSON.");
        } 
        reusableAction.selectSearchableDropdownByLabel(label, value);
    }

    public void validateAndSelectForceDropdown(OPRegistrationPage opPage,String label,String value) {
        if (!JsonUtil.containsOption(label, value)) {
            throw new RuntimeException("Option '" + value +"' not found under '" + label + "' in JSON.");
        }
        opPage.selectSomeForceDropdownByLabel(label, value);
    }

    public void newValidateAndSelectForceDropdown(OPRegistrationPage opPage,String label,String value) {
        if (!JsonUtil.containsOption(label, value)) {
            throw new RuntimeException("Option '" + value +"' not found under '" + label + "' in JSON.");
        }
        opPage.newChangeDropdown(label, value);
    }

    public void validateAndSelectCounter(ReusableCode reusableAction,String heading,String value) {
        if (!JsonUtil.containsOption(heading, value)) {
            throw new RuntimeException("Option '" + value + "' not found under 'Select' in JSON.");
        }
        reusableAction.selectDropdownWithoutLabel(heading,value);
    } 
    
    public void newValidateAndSelectPopUpDropdown(ReusableCode reusableAction,String label,String value) {
            if (!JsonUtil.containsOption(label, value)) {
                throw new RuntimeException("Option '" + value +"' not found under '" + label + "' in JSON.");
            }
            reusableAction.selectDropdownByLabelInsidePopup(label, value);
        }


//  @Test
//     public void verifySmallModuleInsideOpRegistrationnnn() throws IOException {
//         CallInitialMethods();
//         // verifyReprint(reusableAction,dropdownReader,data);
//         verifyRecall(reusableAction,opPage,data);
//         verifyRouteCard(reusableAction,opPage,data);
//         verifyViewCollection(reusableAction,opPage,data,column);
//         verifyUnitLoad(reusableAction,opPage,data,column);   
//     }
// }
    
//  public void CallInitialMethodsss(){
//         int lastColumn = Excel.getLastDataColumn("opRegistrationData");
    // System.out.println("LAST COLUMN COUNT: " + lastColumn);
    // for (int column = 1; column <= lastColumn; column++) {  
    //     // JsonUtil.clearJson();
    //     // JsonUtil.reloadJson();
    //     // System.out.println("Executing Person Data in Column: " + column); 
    //     Map<String, String> data = Excel.getTestData("opRegistrationData", column);
    //     // Map<String, String> data = Excel.getTestData("opRegistrationData");
    //     // IF THE WHOLE COLUMN IS EMPTY STOP TEST RUN
    //     boolean isColumnEmpty = data.values().stream().allMatch(value -> value == null || value.trim().isEmpty());
    //     if (isColumnEmpty) {
    //         System.out.println("Column " + column + " is empty. Stopping execution.");
    //         break;
    //     }
    //     System.out.println("Executing Person Data in Column: " + column); 
    //     DashboardPage dashboard = new DashboardPage(page);
    //     // PROFILE ICON TEST
    //     dashboard.profileIcon();
    //     Page ihmsPage = page.waitForPopup(() -> {
    //         dashboard.clickDashboardOption("IHMS");
    //     });
    //     ReusableCode reusableAction = new ReusableCode(ihmsPage);
    //     reusableAction.clickMenuAndSelectSubMenu("OP Modules","Outpatient Registration");                         
    //     ihmsPage.locator("//select").first().waitFor();
    //     // CAPTURE ALL DROPDOWN AND STORE IT IN JASON
    //     DropdownReader dropdownReader = new DropdownReader(ihmsPage);
    //     // dropdownReader.captureAllDropdowns();
    //     // dropdownReader.captureAllCustomDropdowns();
    //     dropdownReader.captureAllDropdownOptions();     
    //     OPRegistrationPage opPage = new OPRegistrationPage(ihmsPage);
//     }
// }


    // // INITIAL METHOD ---> COMMON FOR ALL
    // public TestContext CallInitialMethods(String mainMenu,String subMenu,String sheetName,int column) {

    //     Map<String, String> data = Excel.getTestData(sheetName, column);

    //     DashboardPage dashboard = new DashboardPage(page);

    //     Page ihmsPage = page.waitForPopup(() -> {
    //         dashboard.clickDashboardOption("IHMS");
    //     });

    //     ReusableCode reusableAction = new ReusableCode(ihmsPage);

    //     // reusableAction.clickMenuAndSelectSubMenu("OP Modules", "Outpatient Registration");
    //     reusableAction.clickMenuAndSelectSubMenu(mainMenu, subMenu);

    //     ihmsPage.locator("//select").first().waitFor();

    //     DropdownReader dropdownReader = new DropdownReader(ihmsPage);

    //     OPRegistrationPage opPage = new OPRegistrationPage(ihmsPage);

    //     BillingPage billPage = new BillingPage(ihmsPage);

    //     return new TestContext(reusableAction, dropdownReader, opPage, billPage, data, column);
    // }




// SMALL MODULES ----> REPRINT,RECALL,UNIT LOAD,ROUTE CARD,VIEW COLLECTION FROM REGISTRATION PAGE
@Test(priority = 2)
public void verifySmallModuleInsideOpRegistration() throws IOException {

    int lastColumn = Excel.getLastDataColumn("Extra collection");
    System.out.println("Last Column = " + lastColumn);

    for (int column = 1; column <= lastColumn; column++) {

        TestContext ctx = CallInitialMethods("OP Modules", "Outpatient Registration","Extra collection",column);

        // verifyReprint(ctx.reusableAction,ctx.opPage,ctx.dropdownReader,ctx.data);

        // verifyRecall(ctx.reusableAction, ctx.opPage, ctx.data); 

        verifyRouteCard(ctx.reusableAction, ctx.data);

        verifyViewCollection(ctx.reusableAction, ctx.opPage, ctx.data, ctx.column);

        verifyUnitLoad(ctx.reusableAction, ctx.opPage, ctx.data, ctx.column);   
    }

}

// REWORK THIS REPRINT 
    //  REPRINT ---> NOT COMPLETED (REPRINT IS NOT WORK "NO USER FOUND" ERROR IS APPEARS IN SITE)
    public void verifyReprint(ReusableCode reusableAction,OPRegistrationPage opPage,DropdownReader dropdownReader,Map<String, String> data) throws IOException {
        reusableAction.buttonClick("Reprint");
        dropdownReader.captureAllDropdownOptions();

        newValidateAndSelectPopUpDropdown(reusableAction,"Patient Type",data.get("PatientTypeReprint"));

        String PatientTypeReprint=data.get("PatientTypeReprint");
        if (("Review".equalsIgnoreCase(PatientTypeReprint))){
            reusableAction.inputFieldByLabelPopup("Date",data.get("dateReprint"));
            dropdownReader.refreshDropdown("Reprint");
            validateAndSelectDropdown(reusableAction,"Reprint",data.get("ReprintChoice"));
        }
        reusableAction.inputFieldByLabelPopup("UIN",data.get("uinReprint"));
        reusableAction.pressKey("Enter");
        dropdownReader.refreshDropdown("Receipt");
        newValidateAndSelectPopUpDropdown(reusableAction,"Receipt",data.get("ReceiptReprint"));
        reusableAction.buttonClickPopup("Submit");
        reusableAction.closeNewWindow();
    }

    // RECALL  ---> COMPLETED
    public void verifyRecall(ReusableCode reusableAction,OPRegistrationPage opPage,Map<String, String> data) throws IOException {
        reusableAction.buttonClick("Recall");
        reusableAction.inputFieldByLabelPopup("UIN",data.get("uinReprint"));
        reusableAction.buttonClickPopup("Submit");
    }

    // ROUTE CARD  ---> COMPLETED
    public void verifyRouteCard(ReusableCode reusableAction,Map<String, String> data) throws IOException {
        reusableAction.buttonClick("Route Card");
        reusableAction.inputFieldByLabelPopup("UIN",data.get("uinRouteCard"));
        reusableAction.buttonClickPopup("Submit");                       
        // reusableAction.closeNewWindow();
    }

    // VIEW COLLECTION  ---> COMPLETED ---> VALUE STORED IN "view collection" SHEET
    public void verifyViewCollection(ReusableCode reusableAction,OPRegistrationPage opPage,Map<String, String> data, int column) throws IOException {
        reusableAction.buttonClick("View Collection");
        if (opPage.isPopupVisible("View Collection")) {
            String collection = opPage.getView("View Collection");
            Excel.updateCell("Extra collection", column, "Total Collection Registration", collection);
        }
        reusableAction.buttonClickPopup("OK");
    }

    // UNIT LOAD  ---> COMPLETED ---> VALUE STORED IN "view collection" SHEET
   public void verifyUnitLoad(ReusableCode reusableAction,OPRegistrationPage opPage,Map<String, String> data,int column) throws IOException {
        reusableAction.buttonClick("Unit Load");
        if (opPage.isPopupVisible("Unit Load")) {
            String unitLoad = opPage.getUnitLoadTable("Unit Load");
            Excel.updateCell("Extra collection", column,"Unit Load Registration", unitLoad);
        }
        reusableAction.closeIcon("Unit Load");
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