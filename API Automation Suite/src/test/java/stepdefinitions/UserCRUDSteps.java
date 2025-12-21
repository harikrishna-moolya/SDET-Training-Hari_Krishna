package stepdefinitions;

import constants.Endpoints;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import specs.RequestSpecUtil;
import specs.ResponseSpecUtil;
import payloads.UserPayload;
import org.testng.Assert;

import static io.restassured.RestAssured.*;

public class UserCRUDSteps {

    Response response;

    @Given("API is configured")
    public void setup() {
        given().spec(RequestSpecUtil.getRequestSpec());
    }

    @When("user creates a new user")
    public void createUser() {
        response = given()
                .spec(RequestSpecUtil.getRequestSpec())
                .body(UserPayload.createUserPayload())
                .when()
                .post(Endpoints.CREATE_USER);
    }

    @When("user retrieves the user")
    public void getUser() {
        response = given()
                .spec(RequestSpecUtil.getRequestSpec())
                .pathParam("username", "testuser")
                .when()
                .get(Endpoints.GET_USER);
    }

    @When("user updates the user")
    public void updateUser() {
        response = given()
                .spec(RequestSpecUtil.getRequestSpec())
                .pathParam("username", "testuser")
                .body(UserPayload.createUserPayload())
                .when()
                .put(Endpoints.UPDATE_USER);
    }

    @When("user deletes the user")
    public void deleteUser() {
        response = given()
                .spec(RequestSpecUtil.getRequestSpec())
                .pathParam("username", "testuser")
                .when()
                .delete(Endpoints.DELETE_USER);
    }

    @When("user fetches invalid user")
    public void invalidUser() {
        response = given()
                .spec(RequestSpecUtil.getRequestSpec())
                .pathParam("username", "invaliduser")
                .when()
                .get(Endpoints.GET_USER);
    }

    @Then("response status code should be {int}")
    public void validateStatusCode(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }
}
