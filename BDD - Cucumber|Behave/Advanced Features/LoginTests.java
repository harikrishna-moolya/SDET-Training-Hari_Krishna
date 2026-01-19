package Tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/",
        glue = "stepdefinitions",
        tags = "@login",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        }
)
public class LoginTests extends AbstractTestNGCucumberTests {
}
