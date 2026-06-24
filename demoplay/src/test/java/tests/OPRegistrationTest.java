package tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import basetest.BaseTest;
import pages.DashboardPage;
import pages.OPRegistrationPage;
import utils.ReusableCode;

public class OPRegistrationTest extends BaseTest {

    @Test
    public void verifyOPRegistration() {

        DashboardPage dashboard = new DashboardPage(page);

        // OPEN IHMS IN A NEW TAB 
        Page ihmsPage = page.waitForPopup(() -> {
            dashboard.clickDashboardOption("IHMS");
        });

        ReusableCode reuseableAction = new ReusableCode(ihmsPage);

        reuseableAction.clickMenuAndSelectSubMenu("OP Modules","Outpatient Registration");

        OPRegistrationPage opPage = new OPRegistrationPage(ihmsPage);

        reuseableAction.selectDropdownByLabel("Pay/Free", "FREE");

        reuseableAction.selectDropdownByLabel("Patient Type", "DIRECT");

        reuseableAction.inputFieldByLabel("First Name","AAA");

        reuseableAction.inputFieldByLabel("Last Name","ZZZ");

        reuseableAction.inputFieldByLabel("Age","25");

        reuseableAction.selectRadioByLabel("Gender","Male");

        reuseableAction.selectDropdownByLabel("Next of Kin","S/O");

        reuseableAction.inputFieldByLabel("Next of Kin","sss");

        // reuseableAction.selectRadioByLabel("Normal / Referral", "Referral");

        // // REFERRAL FORM
        // reuseableAction.inputFieldByLabel("Reference No","566546");
        // // reuseableAction.checkboxByLabel("CRS");
        // // opPage.selectSomeForceDropdownByLabel("District","MADURAI");
        // reuseableAction.inputFieldByLabel("Reference Date","2026-06-20");
        // reuseableAction.selectDropdownByLabel("Referral Name","AARA EYE HOSPITAL");
        // reuseableAction.selectDropdownByLabel("Clinic Referred to","RETINA FREE");
        // reuseableAction.selectDropdownByLabel("Doctor Referred to","Anjana");
        // reuseableAction.buttonClick("Save");

        // // NATIONALITY ---> FOREIGNER
        // reuseableAction.selectRadioByLabel("Nationality", "Foreigner");
        // reuseableAction.selectDropdownByLabel("Country","CANADA");
        // reuseableAction.inputFieldByLabel("Zip Code","45126");

        // NATIONALITY ---> INDIAN
        reuseableAction.selectRadioByLabel("Nationality", "Indian");
        reuseableAction.inputFieldByLabel("Door / Street","98 rrr street");
        reuseableAction.inputFieldByLabel("Locality","inside madurai");
        reuseableAction.inputFieldByLabel("City","madurai");
        reuseableAction.buttonClick("Area");
        opPage.selectSomeForceDropdownByLabel("City","BEE BEE KULAM");
        reuseableAction.inputFieldByLabel("PinCode","645126");
        opPage.selectSomeForceDropdownByLabel("Taluk","MADURAI - GENL");
        reuseableAction.inputFieldByLabel("Mobile No","9548645126");
        reuseableAction.inputFieldByLabel("Email","emailihms2@gmail.com");    
        reuseableAction.selectDropdownByLabel("Purpose Of Visit","OCT");    
    }
}   