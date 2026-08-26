package filters;

import allure.AllureAttachments;
import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AllureLoggingFilter implements Filter {

    @Override
    public Response filter(
            FilterableRequestSpecification request,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {

        String stepName = request.getMethod() + " " + request.getURI();

        return Allure.step(stepName, () -> {

            attachRequest(request);

            Response response = ctx.next(request, responseSpec);

            attachResponse(response);

            return response;
        });
    }

    private void attachRequest(FilterableRequestSpecification request) {
        Object requestBody = request.getBody();
        String body = requestBody == null
                ? "<empty>"
                : requestBody.toString();

        String headers = request.getHeaders().asList().stream()
                .map(header -> header.getName().equalsIgnoreCase("x-api-key")
                        ? header.getName() + ": <redacted>"
                        : header.getName() + ": " + header.getValue())
                .reduce((first, second) -> first + System.lineSeparator() + second)
                .orElse("<empty>");

        String requestInfo = """
                %s %s

                Headers:
                %s

                Body:
                %s
                """.formatted(
                request.getMethod(),
                request.getURI(),
                headers,
                body
        );

        AllureAttachments.attachText("HTTP Request", requestInfo);
    }

    private void attachResponse(Response response) {
        String responseBody;
        try {
            responseBody = response.getBody() == null
                    ? "<empty>"
                    : response.getBody().asPrettyString();
        } catch (Exception e) {
            responseBody = "<unreadable body>";
        }

        String responseInfo = """
                Status:
                %s

                Headers:
                %s

                Body:
                %s
                """.formatted(
                response.getStatusLine(),
                response.getHeaders(),
                responseBody
        );

        AllureAttachments.attachJson("HTTP Response", responseInfo);
    }
}
