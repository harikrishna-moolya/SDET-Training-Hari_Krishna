package request_and_response;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Request_And_Response_Validator {

    int petId = 101;
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }
    // POST - Create Pet

    @Test(priority = 1)
    public void createPet() {

        String requestBody = "{\n" +
                "  \"id\": " + petId + ",\n" +
                "  \"name\": \"JOHNY\",\n" +
                "  \"status\": \"available\"\n" +
                "}";

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(requestBody)
                        .when()
                        .post("/pet")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .header("Content-Type", "application/json")
                        .time(org.hamcrest.Matchers.lessThan(5000L))
                        .body(matchesJsonSchemaInClasspath("petSchema.json"))
                        .extract().response();

        // JSONPath extraction
        int id = response.jsonPath().getInt("id");
        String name = response.jsonPath().getString("name");
        String status = response.jsonPath().getString("status");

        // Assertions
        Assert.assertEquals(id, petId);
        Assert.assertEquals(name, "JOHNY");
        Assert.assertEquals(status, "available");
    }


    // GET - Read Pet

    @Test(priority = 2)
    public void getPet() {

        Response response =
                given()
                        .log().all()
                        .when()
                        .get("/pet/" + petId)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .header("Content-Type", "application/json")
                        .time(org.hamcrest.Matchers.lessThan(5000L))
                        .extract().response();

        int id = response.jsonPath().getInt("id");
        String name = response.jsonPath().getString("name");
        String status = response.jsonPath().getString("status");

        Assert.assertEquals(id, petId);
        Assert.assertEquals(name, "JOHNY");
        Assert.assertEquals(status, "available");
    }


    // PUT - Update Pet

    @Test(priority = 3)
    public void updatePet() {

        String updateBody = "{\n" +
                "  \"id\": " + petId + ",\n" +
                "  \"name\": \"PLUTO\",\n" +
                "  \"status\": \"sold\"\n" +
                "}";

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(updateBody)
                        .when()
                        .put("/pet")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .time(org.hamcrest.Matchers.lessThan(5000L))
                        .extract().response();

        String updatedName = response.jsonPath().getString("name");
        String updatedStatus = response.jsonPath().getString("status");

        Assert.assertEquals(updatedName, "PLUTO");
        Assert.assertEquals(updatedStatus, "sold");
    }


    // DELETE - Delete Pet

    @Test(priority = 4)
    public void deletePet() {

        given()
                .log().all()
                .when()
                .delete("/pet/" + petId)
                .then()
                .log().all()
                .statusCode(200)
                .time(org.hamcrest.Matchers.lessThan(5000L));
    }
}
