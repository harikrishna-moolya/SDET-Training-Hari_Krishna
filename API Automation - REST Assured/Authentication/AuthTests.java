package Authentication;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthTests {

    private String accessToken;
    private String refreshToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
    }

    // 1️⃣ Login and get access + refresh tokens
    @Test(priority = 1)
    public void loginAndGetTokens() {
        Response resp =
                given()
                        .header("Content-Type", "application/json")
                        .body("{ \"username\": \"emilys\", \"password\": \"emilyspass\", \"expiresInMins\": 30 }")
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract().response();

        accessToken = resp.jsonPath().getString("accessToken");  // ✅ correct key
        refreshToken = resp.jsonPath().getString("refreshToken");

        System.out.println("Access Token: " + accessToken);
        System.out.println("Refresh Token: " + refreshToken);
    }


    // 2️⃣ Access protected endpoint using Bearer token
    @Test(priority = 2, dependsOnMethods = "loginAndGetTokens")
    public void accessMeWithBearer() {
        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/auth/me")
                .then()
                .statusCode(200)
                .log().all();
    }


    // 3️⃣ Refresh the access token using refresh token

    @Test(priority = 3, dependsOnMethods = "loginAndGetTokens")
    public void refreshAccessToken() {
        Response resp =
                given()
                        .header("Content-Type", "application/json")
                        .body("{ \"refreshToken\": \"" + refreshToken + "\", \"expiresInMins\": 30 }")
                        .when()
                        .post("/auth/refresh")
                        .then()
                        .statusCode(200)
                        .extract().response();

        accessToken = resp.jsonPath().getString("accessToken"); // ✅ correct key
        System.out.println("Refreshed Access Token: " + accessToken);
    }


    // 4️⃣ Access protected endpoint with refreshed token
    @Test(priority = 4, dependsOnMethods = "refreshAccessToken")
    public void accessMeWithRefreshedToken() {
        given()
                .header("Authorization", "Bearer " + accessToken)
                .log().all()
                .when()
                .get("/auth/me")
                .then()
                .log().all()
                .statusCode(200);
    }
}
