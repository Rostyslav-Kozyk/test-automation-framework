package clients;

import config.RequestSpecFactory;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import steps.StepExecutor;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public abstract class BaseClient {

    private final List<Filter> filters;

    public BaseClient(List<Filter> filters) {
        this.filters = filters;
    }

    private RequestSpecification baseRequest() {
        RequestSpecification spec = RequestSpecFactory.baseSpec();

        if (filters != null && !filters.isEmpty()) {
            spec.filters(filters);
        }

        return RestAssured.given(spec);
    }

    private <T> T clientStep(String name, Supplier<T> call) {
        return StepExecutor.step(name, call);
    }

    protected Response get(String url, String action) {
        return clientStep(action, () -> baseRequest()
                .when()
                .get(url));
    }

    protected Response get(String url, Map<String, ?> queryParams, String action) {
        return clientStep(action, () -> baseRequest()
                .queryParams(queryParams)
                .when()
                .get(url));
    }

    protected Response get(String url, String action, Object... params) {
        return clientStep(action, () -> baseRequest()
                .when()
                .get(String.format(url, params)));
    }
}
