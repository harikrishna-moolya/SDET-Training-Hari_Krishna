package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginSteps {

    WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        Hooks.driver.get(
                "https://tutorialsninja.com/demo/index.php?route=account/login"
        );
    }

    // ---------- BASIC VALID LOGIN ----------
    @When("the user enters valid username and password")
    public void the_user_enters_valid_username_and_password() {
        Hooks.driver.findElement(By.id("input-email"))
                .sendKeys("hk01@gmail.com");
        Hooks.driver.findElement(By.id("input-password"))
                .sendKeys("1432");
    }

    // ---------- SCENARIO OUTLINE (DATA-DRIVEN) ----------
    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {

        WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
        email.clear();
        email.sendKeys(username);

        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("input-password")));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @When("clicks on the login button")
    public void clicks_on_the_login_button() {
        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Login']")));
        loginBtn.click();
    }

    // ---------- SUCCESS VALIDATION ----------
    @Then("the user should be redirected to the dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() {
        WebElement logout = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
        Assert.assertTrue(logout.isDisplayed(), "Dashboard not displayed");
    }

    // ---------- GENERIC RESULT VALIDATION ----------
    @Then("{string} should be displayed")
    public void login_result_should_be_displayed(String loginResult) {

        if (loginResult.equalsIgnoreCase("success")) {
            WebElement logout = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
            Assert.assertTrue(logout.isDisplayed(), "Login should be successful");
        } else {
            WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(".alert-danger")));
            Assert.assertTrue(error.isDisplayed(), "Error message should be displayed");
        }
    }
}
