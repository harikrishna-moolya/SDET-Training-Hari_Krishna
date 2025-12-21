package specs;

import config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class RequestSpecUtil {

    public static RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("base.uri"))
                .setContentType("application/json")
                .setAuth(preemptive().basic(
                        ConfigReader.get("username"),
                        ConfigReader.get("password")))
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }
}
