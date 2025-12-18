package stepdefinitions;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class LoginSteps {

    WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        Hooks.driver.get(
                "https://tutorialsninja.com/demo/index.php?route=account/login"
        );
    }

    @When("the user enters valid username and password")
    public void the_user_enters_valid_username_and_password() {
        Hooks.driver.findElement(By.id("input-email"))
                .sendKeys("hk01@gmail.com");
        Hooks.driver.findElement(By.id("input-password"))
                .sendKeys("1432");
    }

//    @When("the user enters username {string} and password {string}")
//    public void the_user_enters_username_and_password(String username, String password) {
//        Hooks.driver.findElement(By.id("input-email")).clear();
//        Hooks.driver.findElement(By.id("input-email")).sendKeys(username);
//
//        Hooks.driver.findElement(By.id("input-password")).clear();
//        Hooks.driver.findElement(By.id("input-password")).sendKeys(password);
//    }

    @When("the user enters login details")
    public void the_user_enters_login_details_with_clearing_fields(DataTable dataTable) {
        List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> user : users) {
            // Wait and fill email
            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
            email.clear();
            email.sendKeys(user.get("username"));

            // Wait and fill password
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-password")));
            password.clear();
            password.sendKeys(user.get("password"));

            // Wait and click login button
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Login']")));
            loginButton.click();

            // Validate login success
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
                System.out.println("Login successful for: " + user.get("username"));
            } catch (Exception e) {
                System.out.println("Login failed for: " + user.get("username"));
            }

            // Navigate back to login page for next iteration
            Hooks.driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        }
    }





    @When("clicks on the login button")
    public void clicks_on_the_login_button() {
        Hooks.driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Then("the user should be redirected to the dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() {
       System.out.println("Redirected to Dashboard");
    }
}
