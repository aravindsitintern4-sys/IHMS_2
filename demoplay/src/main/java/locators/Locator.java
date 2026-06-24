package locators;

public class Locator {

    // public static final String buttonClick = "//button[normalize-space()='%s']";

    public static final String toastMsg = "#toast-container";

    public static final String DashboardOption = "//h3[normalize-space()='%s']";

    // public static final String dropdownXpath = "//label[contains(normalize-space(),'%s')]/following-sibling::select";

    public static final String dropdownXpath =  "//label[contains(normalize-space(),'%s')]/parent::*//select";

    public static final String inputField =  "//label[contains(normalize-space(),'%s')]/following::input[1]";

    public static final String radioBtn ="//label[contains(normalize-space(),'%s')]/following-sibling::div//label[contains(normalize-space(),'%s')]/input";

    public static final String checkbox = "//label[contains(normalize-space(),'%s')]/preceding-sibling::input[@type='checkbox']";

    public static final String DROPDOWN_BY_LABEL = "//label[contains(normalize-space(),'%s')]/following::select[1]";
    
} 



