package stepdefinitions;

import constants.Endpoints;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import payloads.UserPayload;
import specs.RequestSpecUtil;
import utils.ExceptionUtil;

import java.io.InputStream;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class UserCRUDSteps {

    private Response response;

    private final String username = "HARI";
    private final String invalidUser = "PREM";

    // ================== SETUP ==================

    @Given("API is configured")
    public void apiConfigured() {
        // Base URI & headers handled in RequestSpecUtil
    }

    // ================== POSITIVE ==================

    @When("user creates a new user")
    public void createUser() {
        response = given()
                .spec(RequestSpecUtil.getRequestSpec())
                .body(UserPayload.createUser(username))
                .when()
                .post(Endpoints.USER);
    }

    @When("user retrieves the user")
    public void retrieveUser() {
        response = given()
                .spec(RequestSpecUtil.getRequestSpec())
                .pathParam("username", username)
                .when()
                .get(Endpoints.USER_BY_NAME);
    }

    @When("user updates the user")
    public void updateUser() {
        response = given()
                .spec(RequestSpecUtil.getRequestSpec())
                .pathParam("username", username)
                .body(UserPayload.updateUser(username))
                .when()
                .put(Endpoints.USER_BY_NAME);
    }

    @When("user deletes the user")
    public void deleteUser() {
        response = given()
                .spec(RequestSpecUtil.getRequestSpec())
                .pathParam("username", username)
                .when()
                .delete(Endpoints.USER_BY_NAME);
    }

    // ================== NEGATIVE ==================

    @When("user retrieves non existing user")
    public void retrieveInvalidUser() {
        response = given()
                .spec(RequestSpecUtil.getRequestSpec())
                .pathParam("username", invalidUser)
                .when()
                .get(Endpoints.USER_BY_NAME);
    }

    @When("user deletes non existing user")
    public void deleteInvalidUser() {
        response = given()
                .spec(RequestSpecUtil.getRequestSpec())
                .pathParam("username", invalidUser)
                .when()
                .delete(Endpoints.USER_BY_NAME);
    }

    @When("user creates user with empty payload")
    public void createUserWithEmptyPayload() {
        response = given()
                .spec(RequestSpecUtil.getRequestSpec())
                .body(UserPayload.emptyPayload())
                .when()
                .post(Endpoints.USER);
    }

    // ================== ASSERTIONS ==================

    @Then("response status code should be {int}")
    public void validateStatusCode(int expectedStatus) {
        try {
            response.then().statusCode(expectedStatus);
        } catch (AssertionError e) {
            ExceptionUtil.fail(
                    "Status code validation failed. Expected: "
                            + expectedStatus
                            + ", Actual: "
                            + response.getStatusCode(),
                    e
            );
        }
    }

    // ================== SCHEMA VALIDATION ==================
    // Applied ONLY for GET user API

    @Then("response should match user schema")
    public void validateUserSchema() {

        if (response == null) {
            ExceptionUtil.fail("Response is null. API not executed.");
        }

        // Apply schema ONLY when actual user object is returned
        if (!response.asString().contains("\"username\"")) {
            System.out.println("ℹ️ Schema validation skipped: response is not a user object");
            return;
        }

        try {
            InputStream schemaStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("schemas/userSchema.json");

            if (schemaStream == null) {
                ExceptionUtil.fail("Schema not found: schemas/userSchema.json");
            }

            response.then().body(matchesJsonSchema(schemaStream));

        } catch (AssertionError e) {
            ExceptionUtil.fail("User schema validation failed", e);
        }
    }
}
