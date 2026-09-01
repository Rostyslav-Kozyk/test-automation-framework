package validations;

import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;

/**
 * Provides swagger validator functionality.
 */
public class SwaggerValidator {

    private static final String SPEC_PATH = "src/test/resources/openapi/reqres-api.yaml";

    /**
     * Sets OpenAPI validation filter.
     *
     * @return the OpenAPI validation filter
     */
    public static OpenApiValidationFilter filter() {
        return new OpenApiValidationFilter(SPEC_PATH);
    }
}
