package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseRequest implements Request {

    private final String baseUrl;
    private final String endpoint;
    private final RequestSpecification requestSpecification;

    public BaseRequest(String baseUrl, String endpoint) {
        this.baseUrl = baseUrl;
        this.endpoint = endpoint;

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON).build();
    }

    public BaseRequest(String endpoint){

        this(Config.URL.getValue(), endpoint);
    }

    public RequestSpecification requestConfig() {
        return RestAssured.given()
                .spec(requestSpecification)
                .baseUri(baseUrl)
                .basePath(endpoint);
    }
}
