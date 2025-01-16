package com.platform.web.page.saucedemo;

import com.platform.web.webactions.BaseActions;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseActions {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void goToLoginPage()  {
        goToURL("https://www.saucedemo.com/");
    }

    public void inputUsername (String username)  {
        inputTextWithValue("TXTBOX_USER_NAME",username,10);
    }

    public void inputPassword(String password)  {
        inputTextWithValue("TXTBOX_PASSWORD",password,10);
    }

    public void clickLoginButton()  {
        clickElement("BTN_LOGIN",60);
    }

    public void loginWithCredentials(String username)  {
        inputUsername(username);
        inputPassword("secret_sauce");
    }

    public void validateLoginSuccess()  {
        validateElementDisplayed("LISTPRICE_PRODUCT", 10);
    }

    public void validateUserLocked() {
        validateElementDisplayed("TXT_USER_LOCKED", 10);
        isElementContainText("TXT_USER_LOCKED","Epic sadface: Sorry, this user has been locked out", 10);

    }
}
