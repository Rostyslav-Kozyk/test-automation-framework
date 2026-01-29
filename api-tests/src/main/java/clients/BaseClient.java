package clients;

import config.RequestSpecFactory;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseClient {

    protected RequestSpecification baseRequest() {
        return RestAssured.given(RequestSpecFactory.baseSpec());
    }
}
