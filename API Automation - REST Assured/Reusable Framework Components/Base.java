package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Base {

    int petId = 101;

    // Reusable Request and Response Specifications
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Request Specification
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();

        // Response Specification
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }

    // Generic Methods
    public Response postRequest(String endpoint, String body) {
        return given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response getRequest(String endpoint) {
        return given()
                .spec(requestSpec)
                .when()
                .get(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    public Response putRequest(String endpoint, String body) {
        return given()
                .spec(requestSpec)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    private Response deleteRequest(String endpoint) {
        return given()
                .spec(requestSpec)
                .when()
                .delete(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    // JSON Utilities
    private String createPetPayload(int id, String name, String status) {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";
    }
    @Test(priority = 1)
    public void createPet() {
        Response response = postRequest("/pet", createPetPayload(petId, "JOHNY", "available"));
        Assert.assertEquals(response.jsonPath().getString("name"), "JOHNY");
    }

    @Test(priority = 2)
    public void getPet() {
        Response response = getRequest("/pet/" + petId);
        Assert.assertEquals(response.jsonPath().getInt("id"), petId);
        Assert.assertEquals(response.jsonPath().getString("status"), "available");
    }

    @Test(priority = 3)
    public void updatePet() {
        Response response = putRequest("/pet", createPetPayload(petId, "PLUTO", "sold"));
        Assert.assertEquals(response.jsonPath().getString("name"), "PLUTO");
        Assert.assertEquals(response.jsonPath().getString("status"), "sold");
    }

    @Test(priority = 4)
    public void deletePet() {
        deleteRequest("/pet/" + petId);
    }
}








