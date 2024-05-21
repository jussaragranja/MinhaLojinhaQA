package requestspecification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import util.Property;

import static util.Constants.AUTHORIZATION_TOKEN;
import static util.Constants.BEARER_TOKEN;

/**
 * @author jussaragranja
 */

public class BaseTest {

    @BeforeAll
    public static void configRestassured(){

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setConfig(RestAssuredConfig.config()
                        .logConfig(LogConfig.logConfig()
                                .enablePrettyPrinting(true)
                                .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)
                        )
                )
                .setBaseUri(Property.API_URL)
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification requestSpecification(String token) {
        return getRequestBuilder()
                .setBaseUri(Property.API_URL)
                .addHeader(AUTHORIZATION_TOKEN, token)
                .build();
    }

    private static RequestSpecBuilder getRequestBuilder() {
        return new RequestSpecBuilder()
                .setConfig(
                        RestAssuredConfig.config()
                                .logConfig(LogConfig
                                        .logConfig()
                                        .enablePrettyPrinting(true)
                                        .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)
                                )
                )
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL);
    }

}
