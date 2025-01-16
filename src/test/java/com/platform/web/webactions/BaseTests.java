package com.platform.web.webactions;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Pattern;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;

public class BaseTests {

    public static Properties ELEMENT_PROPERTIES;
    protected static WebDriver driver;


    @BeforeSuite
    public WebDriver getDriver() {

            this.collectLocator();

            ChromeOptions options = new ChromeOptions();
            options.addArguments(
                    "--no-sandbox",
                    // "--disable-extensions",
                    "--disable-gpu",
                    "--ignore-certificate-errors",
                    "--whitelisted-ips=''",
                    "--disable-dev-shm-usage");
            System.setProperty("webdriver.chrome.whitelistedIps", "");
            WebDriverManager.chromedriver().setup();


            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

        return driver;
    }

    private void collectLocator() {
        String directory = "src/test/resources/elements/";
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();
        ELEMENT_PROPERTIES = new Properties();

        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; ++i) {
            if (listOfFiles[i].isFile() && listOfFiles[i].toString().endsWith(".properties")) {
                try {
                    // TODO: handle redundant variable name
                    ELEMENT_PROPERTIES.load(new FileInputStream(directory + listOfFiles[i].getName()));
                } catch (Exception e) {
                    throw new IllegalStateException("Failed to load properties : " + listOfFiles[i].getName());

                }
            }
        }
    }
}

