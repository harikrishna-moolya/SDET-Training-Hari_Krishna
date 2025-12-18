package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import Pages.Login;

import java.util.List;
import java.util.Map;

public class LoginSteps {

    Login loginPage = new Login(Hooks.driver);

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @When("the user enters login details")
    public void enterLoginDetails(DataTable dataTable) {
        List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> user : users) {
            loginPage.enterEmail(user.get("username"));
            loginPage.enterPassword(user.get("password"));
            loginPage.clickLogin();

            if (loginPage.isLoginSuccessful()) {
                System.out.println("Login successful for: " + user.get("username"));
            } else {
                System.out.println("Login failed for: " + user.get("username"));
            }

            // Clear fields by navigating back to login page
            loginPage.navigateToLoginPage();
        }
    }

    @Then("the user should be redirected to the dashboard")
    public void verifyDashboard() {
        System.out.println("Redirected to Dashboard");
    }
}
