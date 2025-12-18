package Tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/",
        glue = {"stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber.json"},
        tags = "@smoke or @regression"
)
public class LoginTests extends AbstractTestNGCucumberTests { }
