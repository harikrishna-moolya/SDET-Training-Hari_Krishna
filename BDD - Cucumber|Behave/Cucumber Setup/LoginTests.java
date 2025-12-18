package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/Login.feature",
        glue = "stepdefinitions",
        plugin = {"pretty"}
)
public class LoginTests extends AbstractTestNGCucumberTests {
}
