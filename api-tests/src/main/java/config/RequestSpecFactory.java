package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

    static Environment env = Environment.valueOf(
            System.getProperty("env", "PROD")
    );

    public static RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(env.getBaseUrl())
                .setContentType("application/json")
                .addHeader("x-api-key", Config.API_KEY.getConfig())
                .build();
    }
}
