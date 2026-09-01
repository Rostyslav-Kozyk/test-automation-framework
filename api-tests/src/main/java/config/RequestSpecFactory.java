package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * Creates configured API request specification instances.
 */
public final class RequestSpecFactory {

    /**
     * Prevents instantiation of this utility class.
     */
    private RequestSpecFactory() {
    }

    /**
     * Creates the base API specification.
     *
     * @return the request specification
     */
    public static RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ApiConfig.getBaseUrl())
                .setContentType("application/json")
                .addHeader("x-api-key", Config.API_KEY.getConfig())
                .build();
    }

}
