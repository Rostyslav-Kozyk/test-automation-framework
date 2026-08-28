package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

    public static RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(resolveBaseUrl())
                .setContentType("application/json")
                .addHeader("x-api-key", Config.API_KEY.getConfig())
                .build();
    }

    private static String resolveBaseUrl() {
        String customBaseUrl = System.getProperty("api.base.url");
        if (customBaseUrl != null && !customBaseUrl.isBlank()) {
            return customBaseUrl.trim().replaceAll("/+$", "");
        }

        String environmentName = System.getProperty("env", "PROD").trim().toUpperCase();
        try {
            return Environment.valueOf(environmentName).getBaseUrl();
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Unsupported API environment '" + environmentName +
                            "'. Supported values: DEV, QA, PROD",
                    exception
            );
        }
    }
}
