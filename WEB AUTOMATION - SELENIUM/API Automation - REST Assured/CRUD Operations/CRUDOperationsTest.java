package CRUD;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class CRUDOperationsTest {

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
                        .body(requestBody)
                        .log().all()
                        .when()
                        .post("/pet")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().response();

        String name = response.jsonPath().getString("name");
        Assert.assertEquals(name, "JOHNY");
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
                        .extract().response();

        int id = response.jsonPath().getInt("id");
        String status = response.jsonPath().getString("status");

        Assert.assertEquals(id, petId);
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
                        .body(updateBody)
                        .log().all()
                        .when()
                        .put("/pet")
                        .then()
                        .log().all()
                        .statusCode(200)
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
                .statusCode(200);
    }
}
