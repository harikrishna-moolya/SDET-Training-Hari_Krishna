package stepdefinitions;

import io.cucumber.java.en.*;

public class LoginSteps {

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        System.out.println("User is on the login page");
    }

    @When("the user enters incorrect username and password")
    public void the_user_enters_incorrect_username_and_password() {
        System.out.println("User enters username and password");
    }

    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        System.out.println("Error message is displayed");
    }
}
