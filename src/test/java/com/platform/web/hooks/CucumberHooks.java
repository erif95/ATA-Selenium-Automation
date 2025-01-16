package com.platform.web.hooks;

import com.platform.web.webactions.BaseTests;
import io.cucumber.java8.En;
import org.openqa.selenium.WebDriver;

public class CucumberHooks extends BaseTests implements En {

    public WebDriver driver;

    public CucumberHooks() {
        Before(
                "@web",
                () -> {
                    driver = getDriver();
                });
        After(
                "@web",
                () -> {
                    driver.quit();
                });
    }
}
