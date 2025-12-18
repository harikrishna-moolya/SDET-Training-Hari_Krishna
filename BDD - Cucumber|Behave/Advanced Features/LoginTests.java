package Tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/",
        glue = "stepdefinitions",
        tags = "@smoke or @regression",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "rerun:target/failed_scenarios.txt"
        },
        monochrome = true
)
public class LoginTests extends AbstractTestNGCucumberTests {
}
