package com.platform.web.stepdef;

import com.platform.web.page.BasePage;
import io.cucumber.java8.En;

public class LoginStepDef extends BasePage implements En {

    public LoginStepDef() {

        Given(
                "^user is on sauce demo homepage$",
                () -> {
                    LoginPage().goToLoginPage();
                });

        When(
                "^user login with valid username \"([^\"]*)\"$",
                (String username) -> {
                    LoginPage().loginWithCredentials(username);
                    LoginPage().clickLoginButton();
                });
        Then(
                "^login should be success$",() -> {
                    LoginPage().validateLoginSuccess();
                });
        Then(
                "^user should be locked$",() -> {
                    LoginPage().validateUserLocked();
                });
    }
}

