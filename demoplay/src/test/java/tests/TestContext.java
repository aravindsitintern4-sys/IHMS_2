package tests;

import java.util.Map;

import pages.BillingPage;
import pages.OPRegistrationPage;
import utils.DropdownReader;
import utils.ReusableCode;

// USE CLASS AND OBJECT MODEL METHOD
public class TestContext {

    public ReusableCode reusableAction;
    public DropdownReader dropdownReader;
    public OPRegistrationPage opPage;
    public BillingPage billPage;
    public Map<String, String> data;
    public int column;
    

    // CREATE CONSTRUCTOR
    public TestContext(ReusableCode reusableAction,
                       DropdownReader dropdownReader,
                       OPRegistrationPage opPage,
                       BillingPage billPage,
                       Map<String, String> data,
                       int column) {

        this.reusableAction = reusableAction;
        this.dropdownReader = dropdownReader;
        this.opPage = opPage;
        this.billPage = billPage;
        this.data = data;
        this.column = column;
    }
}
    
