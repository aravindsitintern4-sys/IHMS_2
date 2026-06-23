package pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    // LOCATORS
    private String username = "#username";
    private String password = "#password";
    private String loginBtn = "button[type='Submit']";

    // CONSTRUCTOR
    public LoginPage(Page page) {
        this.page = page;
    }

    // ACTIONS
    public void enterUsername(String user) {
        page.fill(username, user);
    }

    public void enterPassword(String pass) {
        page.fill(password, pass);
    }

    public void clickLogin() {
        page.click(loginBtn);
    }

    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

}