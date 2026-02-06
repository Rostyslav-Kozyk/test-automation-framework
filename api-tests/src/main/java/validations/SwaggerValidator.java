package validations;

import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;

public class SwaggerValidator {

    private static final String SPEC_PATH = "src/test/resources/openapi/reqres-api.yaml";

    public static OpenApiValidationFilter filter() {
        return new OpenApiValidationFilter(SPEC_PATH);
    }
}
