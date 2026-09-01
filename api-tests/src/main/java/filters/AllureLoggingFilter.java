package filters;

import allure.AllureAttachments;
import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

/**
 * Provides Allure logging filter functionality. Used for detailed information in Allure report about API requests and
 * responses received during test steps.
 */
public class AllureLoggingFilter implements Filter {

    /**
     * Attaches ReqRes information to step in Allure report.
     *
     * @param request      the request value
     * @param responseSpec the response spec value
     * @param ctx          the context value
     * @return the Allure step with detailed ReqRes information
     */
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

    /**
     * Attaches request.
     *
     * @param request the request value
     */
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

        String requestInfo = ("%s %s%n%nHeaders:%n%s%n%nBody:%n%s%n").formatted(
                request.getMethod(),
                request.getURI(),
                headers,
                body
        );

        AllureAttachments.attachText("HTTP Request", requestInfo);
    }

    /**
     * Attaches response.
     *
     * @param response the response value
     */
    private void attachResponse(Response response) {
        String responseBody;
        try {
            responseBody = response.getBody() == null
                    ? "<empty>"
                    : response.getBody().asPrettyString();
        } catch (Exception e) {
            responseBody = "<unreadable body>";
        }

        String responseInfo = ("Status:%n%s%n%nHeaders:%n%s%n%nBody:%n%s%n").formatted(
                response.getStatusLine(),
                response.getHeaders(),
                responseBody
        );

        AllureAttachments.attachJson("HTTP Response", responseInfo);
    }
}
