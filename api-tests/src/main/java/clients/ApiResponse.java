package clients;

import io.restassured.response.Response;

public class ApiResponse<T> {

    private final Response response;
    private final T body;

    public ApiResponse(Response response, Class<T> clazz) {
        this.response = response;
        this.body = response.as(clazz);
    }

    public Response getResponse() {
        return response;
    }

    public T getBody() {
        return body;
    }
}
