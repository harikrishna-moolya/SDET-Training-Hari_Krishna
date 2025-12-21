package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features",
        glue = "stepdefinitions",
        monochrome = true,
        plugin = {
                "pretty",
                "html:reports/cucumber-report.html",
                "json:reports/cucumber-report.json",
                "junit:reports/cucumber-report.xml"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
