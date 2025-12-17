package pojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.Category;
import pojo.Pet;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.*;

public class AdvancedPetstoreTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    // 1️⃣ Send Complex JSON using MAP
    @Test
    public void createPetUsingMap() {

        Map<String, Object> category = new HashMap<>();
        category.put("id", 1);
        category.put("name", "Dogs");

        Map<String, Object> pet = new HashMap<>();
        pet.put("id", 101);
        pet.put("name", "Tommy");
        pet.put("status", "available");
        pet.put("category", category);
        pet.put("photoUrls", Arrays.asList("http://image.com/dog.png"));

        given()
                .contentType("application/json")
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .log().all();
    }

    // 2️⃣ Send JSON using POJO (Serialization)
    @Test
    public void createPetUsingPojo() {

        Category category = new Category();
        category.id = 2;
        category.name = "Dogs";

        Pet pet = new Pet();
        pet.id = 102;
        pet.name = "Bruno";
        pet.status = "available";
        pet.category = category;
        pet.photoUrls = Arrays.asList("http://image.com/bruno.png");

        given()
                .contentType("application/json")
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .log().all();
    }

    // 3️⃣ Send JSON using FILE
    @Test
    public void createPetUsingJsonFile() {

        File jsonFile = new File("src/main/resources/pet.json");

        given()
                .contentType("application/json")
                .body(jsonFile)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .log().all();
    }

    // 4️⃣ Upload File (multipart/form-data)
    @Test
    public void uploadPetImage() {

        File image = new File("src/main/resources/dog.jpeg");

        given()
                .multiPart("file", image)
                .when()
                .post("/pet/101/uploadImage")
                .then()
                .statusCode(200)
                .log().all();
    }

    // 5️⃣ Cookies - Send & Capture
    @Test
    public void manageCookies() {

        Response response =
                given()
                        .cookie("sessionId", "abc123")
                        .when()
                        .get("/pet/101");

        System.out.println("Response Cookies: " + response.getCookies());
    }

    // 6️⃣ Enable Request & Response Logging
    @Test
    public void loggingExample() {

        given()
                .log().all()
                .when()
                .get("/pet/101")
                .then()
                .log().all()
                .statusCode(200);
    }

    // 7️⃣ Deserialize JSON → POJO
    @Test
    public void deserializeResponseToPojo() {

        Pet pet =
                given()
                        .when()
                        .get("/pet/101")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Pet.class);

        System.out.println("Pet Name: " + pet.name);
        System.out.println("Pet Status: " + pet.status);
    }
}
