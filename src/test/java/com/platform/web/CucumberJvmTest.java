package com.platform.web;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = "com.platform.web",
        plugin = {
                "pretty",
                "json:target/cucumber_report/report.json"
        }
        )

public class CucumberJvmTest extends AbstractTestNGCucumberTests {}
