package com.platform.web.webactions;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseWaitActions extends BaseTests {

    public WebDriver driver;


    public BaseWaitActions(WebDriver driver) {
        this.driver = driver;
    }

    protected By getLocator(String elementLocator) {

        String[] dynamicElementUnit = elementLocator.split(",");
        String elementProperty = ELEMENT_PROPERTIES.getProperty(dynamicElementUnit[0]);
        if (elementProperty == null) elementProperty = dynamicElementUnit[0];

        for (int i = 0; i < dynamicElementUnit.length - 1; i++) {
            elementProperty =
                    elementProperty.replaceAll("\\$\\{index_" + i + "\\}", dynamicElementUnit[i + 1]);
        }

        String locatorType;
        String locatorValue;

        try {
            String[] locator = elementProperty.split("_", 2);
            locatorType = locator[0];
            locatorValue = locator[1];
        } catch (Exception e) {
            throw new org.openqa.selenium.NoSuchElementException("Locator invalid : " + elementLocator);
        }

        By element;

        switch (locatorType) {
            case "xpath":
                element = By.xpath(locatorValue);
                break;
            case "id":
                element = By.id(locatorValue);
                break;
            case "css":
                element = By.cssSelector(locatorValue);
                break;
            case "class":
                element = By.className(locatorValue);
                break;
            case "containsText":
                element = By.xpath("//*[contains(text(), '" + locatorValue + "')]");
                break;
            case "text":
                element = By.xpath("//*[@text='" + locatorValue + "']");
                break;
            default:
                element = null;
        }
        return element;
    }
}
