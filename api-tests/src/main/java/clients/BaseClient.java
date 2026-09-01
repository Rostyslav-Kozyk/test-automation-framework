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

/**
 * Provides base client functionality for API requests.
 */
public abstract class BaseClient {

    private final List<Filter> filters;

    /**
     * Creates a new {@code BaseClient} instance.
     *
     * @param filters the filters value
     */
    public BaseClient(List<Filter> filters) {
        this.filters = filters == null ? List.of() : List.copyOf(filters);
    }

    /**
     * Creates the base request.
     *
     * @return the request specification
     */
    private RequestSpecification baseRequest() {
        RequestSpecification spec = RequestSpecFactory.baseSpec();

        if (!filters.isEmpty()) {
            spec.filters(filters);
        }

        return RestAssured.given(spec);
    }

    /**
     * Executes the client step operation.
     *
     * @param name the name value
     * @param call the call value
     * @return the operation result
     */
    private <T> T clientStep(String name, Supplier<T> call) {
        return StepExecutor.step(name, call);
    }

    /**
     * Sends an HTTP GET request.
     *
     * @param url    the url value
     * @param action the action value
     * @return the HTTP GET response
     */
    protected Response get(String url, String action) {
        return clientStep(action, () -> baseRequest()
                .when()
                .get(url));
    }

    /**
     * Sends an HTTP GET request with query parameters.
     *
     * @param url         the url value
     * @param queryParams the query params value
     * @param action      the action value
     * @return the HTTP GET response
     */
    protected Response get(String url, Map<String, ?> queryParams, String action) {
        return clientStep(action, () -> baseRequest()
                .queryParams(queryParams)
                .when()
                .get(url));
    }

    /**
     * Sends an HTTP GET request with URL parameters.
     *
     * @param url    the url value
     * @param action the action value
     * @param params the params value
     * @return the HTTP GET response
     */
    protected Response get(String url, String action, Object... params) {
        return clientStep(action, () -> baseRequest()
                .when()
                .get(String.format(url, params)));
    }

    /**
     * Sends an HTTP POST request.
     *
     * @param url    the url value
     * @param body   the body value
     * @param action the action value
     * @return the HTTP POST response
     */
    protected Response post(String url, Object body, String action) {
        return clientStep(action, () -> baseRequest()
                .body(body)
                .when()
                .post(url));
    }

    /**
     * Sends an HTTP PUT request.
     *
     * @param url    the url value
     * @param body   the body value
     * @param action the action value
     * @param params the params value
     * @return the HTTP PUT response
     */
    protected Response put(String url, Object body, String action, Object... params) {
        return clientStep(action, () -> baseRequest()
                .body(body)
                .when()
                .put(String.format(url, params)));
    }

    /**
     * Sends an HTTP PATCH request.
     *
     * @param url    the url value
     * @param body   the body value
     * @param action the action value
     * @param params the params value
     * @return the HTTP PATCH response
     */
    protected Response patch(String url, Object body, String action, Object... params) {
        return clientStep(action, () -> baseRequest()
                .body(body)
                .when()
                .patch(String.format(url, params)));
    }

    /**
     * Sends an HTTP DELETE request.
     *
     * @param url    the url value
     * @param action the action value
     * @param params the params value
     * @return the HTTP DELETE response
     */
    protected Response delete(String url, String action, Object... params) {
        return clientStep(action, () -> baseRequest()
                .when()
                .delete(String.format(url, params)));
    }
}
