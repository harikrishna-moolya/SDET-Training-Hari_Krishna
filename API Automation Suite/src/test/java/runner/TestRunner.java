package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = "stepdefinitions",
        plugin = {
                "pretty",
                "html:reports/cucumber-report.html",
                "json:reports/cucumber-report.json",
                "junit:reports/cucumber-report.xml"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
