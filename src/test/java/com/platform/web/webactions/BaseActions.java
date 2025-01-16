package com.platform.web.webactions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BaseActions extends BaseWaitActions {

    public BaseActions(WebDriver driver) {
        super(driver);
    }


    private WebElement waitAndGetElement(String locator, int timeout) {
        validateElementDisplayed(locator, timeout);
        return driver.findElement(getLocator(locator));
    }

    public List<WebElement> waitAndGetListElements(String locator, int timeout) {
        validateElementDisplayed(locator, timeout);
        return driver.findElements(getLocator(locator));
    }


    public void clickElement(String locator, int timeout) {
        waitAndGetElement(locator, timeout).click();
    }

    public void clickElementWithIndex (String locator, int timeout, int index) {

        waitAndGetListElements(locator, timeout).get(index).click();
    }

    private void clickElementByCondition(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            clickElementViaJSExecutor(element);
        }
    }

    private void clickElementViaJSExecutor(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void inputTextWithValue(String locator, String value, int timeout) {
        WebElement element = waitAndGetElement(locator, timeout);
        element.clear();
        element.sendKeys(value);
    }


    public void clearAndInputTextWithValue(String locator, String value, int timeout) {
        WebElement element = waitAndGetElement(locator, timeout);
        element.sendKeys(Keys.HOME);
        element.sendKeys(Keys.chord(Keys.SHIFT, Keys.END));
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(value);
    }

    public void goToURL(String url) {

        driver.get(url);
    }


    public void validateElementDisplayed(String locator, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)));
    }

    public void validateElementWithText(String locator, String expected) {

        Assert.assertEquals(driver.findElement(getLocator(locator)).getText(), expected);

    }


    public Boolean isElementContainText(String locator, String partialText, int timeout) {
        String text = waitAndGetElement(locator, timeout).getText();
        if (text.contains(partialText)) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isElementVisible(String locator, int inSeconds) {
        try {
            validateElementDisplayed(locator, inSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean IsElementPresent(By by, int inSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(inSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void selectOnDropDownByText(String locator, String text, int timeout) {
        try {
            WebElement element = waitAndGetElement(locator, timeout);
            Select select = new Select(element);
            select.selectByVisibleText(text);
        } catch (UnexpectedTagNameException e) {
            List<WebElement> elements = waitAndGetListElements(locator, timeout);
            for (WebElement element : elements) {
                if (element.getText().trim().equals(text.trim())) {
                    clickElementByCondition(element);
                    break;
                }
            }
        }
    }
}
