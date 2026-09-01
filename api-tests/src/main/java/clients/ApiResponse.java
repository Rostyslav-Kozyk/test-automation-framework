package clients;

import io.restassured.response.Response;

/**
 * Provides api response functionality.
 */
public class ApiResponse<T> {

    private final Response response;
    private final T body;

    public ApiResponse(Response response, Class<T> clazz) {
        this.response = response;
        this.body = response.as(clazz);
    }

    /**
     * Returns response.
     *
     * @return the response value
     */
    public Response getResponse() {
        return response;
    }

    /**
     * Returns body.
     *
     * @return the body value
     */
    public T getBody() {
        return body;
    }
}
