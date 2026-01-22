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

    // -------------------------------------------------
    // BASIC AUTHENTICATION (FIX ADDED)
    // -------------------------------------------------
    @Test(priority = 0)
    public void basicAuthenticationTest() {

        given()
                .auth().preemptive().basic("emilys", "emilyspass")
                .when()
                .get("/auth/me")
                .then()
                .statusCode(200)
                .log().all();
    }

    // -------------------------------------------------
    // LOGIN & GET ACCESS + REFRESH TOKENS
    // -------------------------------------------------
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

        accessToken = resp.jsonPath().getString("accessToken");
        refreshToken = resp.jsonPath().getString("refreshToken");

        System.out.println("Access Token: " + accessToken);
        System.out.println("Refresh Token: " + refreshToken);
    }

    // -------------------------------------------------
    // ACCESS PROTECTED ENDPOINT USING BEARER TOKEN
    // -------------------------------------------------
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

    // -------------------------------------------------
    // REFRESH ACCESS TOKEN
    // -------------------------------------------------
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

        accessToken = resp.jsonPath().getString("accessToken");
        System.out.println("Refreshed Access Token: " + accessToken);
    }

    // -------------------------------------------------
    // ACCESS WITH REFRESHED TOKEN
    // -------------------------------------------------
    @Test(priority = 4, dependsOnMethods = "refreshAccessToken")
    public void accessMeWithRefreshedToken() {

        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/auth/me")
                .then()
                .statusCode(200)
                .log().all();
    }
}
