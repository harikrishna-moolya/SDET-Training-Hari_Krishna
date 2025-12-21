package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecUtil {

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }
}
