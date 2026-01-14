package End_to_End_Flow;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestEndToEndTest {

    String token = "5c2a11962ee8c840fe0e512f909f8a11550cd5ffd1bb8345db8a241cfe6a92ce";
    int userId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
    }

    // CREATE USER
    @Test(priority = 1)
    public void createUser() {

        String body = """
        {
          "name": "Hari Krishna",
          "gender": "male",
          "email": "hari_%d@gmail.com",
          "status": "active"
        }
        """.formatted(System.currentTimeMillis()); 

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + token)
                        .body(body)
                        .when()
                        .post("/users")
                        .then()
                        .statusCode(201)
                        .header("Content-Type", containsString("application/json"))
                        .body("id", notNullValue())
                        .body("name", equalTo("Hari Krishna"))
                        .body("gender", equalTo("male"))
                        .body("status", equalTo("active"))
                        .extract().response();

        userId = response.path("id");
    }

    // GET USER
    @Test(priority = 2, dependsOnMethods = "createUser") 
    public void getUser() {

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("id", equalTo(userId))
                .body("name", equalTo("Hari Krishna"))
                .body("gender", equalTo("male"))
                .body("status", equalTo("active"));
    }

    // UPDATE USER
    @Test(priority = 3, dependsOnMethods = "getUser") 
    public void updateUser() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body("{\"name\":\"Hari Krishna Akki\"}")
                .when()
                .patch("/users/" + userId)
                .then()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("name", equalTo("Hari Krishna Akki"));
    }

    // DELETE USER
    @Test(priority = 4, dependsOnMethods = "updateUser")
    public void deleteUser() {

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(204);
    }

    // VERIFY DELETED USER
    @Test(priority = 5, dependsOnMethods = "deleteUser") 
    public void verifyDeletedUser() {

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(404)
                .body("message", equalTo("Resource not found"));
    }
}
