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

    @Test(priority = 1)
    public void createUser() {

        String body = """
        {
          "name": "Hari Krishna",
          "gender": "male",
          "email": "hari.k@gmail.com",
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
                        .extract().response();

        userId = response.path("id");
        System.out.println("User created with ID: " + userId);
    }

    @Test(priority = 2)
    public void getUser() {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("id", equalTo(userId));
    }

    @Test(priority = 3)
    public void updateUser() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body("{\"name\":\"Hari Krishna Akki\"}")
                .when()
                .patch("/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Hari Krishna Akki"));
    }

    @Test(priority = 4)
    public void deleteUser() {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(204);
    }

    @Test(priority = 5)
    public void verifyDeletedUser() {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(404);
    }
}

