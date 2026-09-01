package filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

/**
 * Provides pretty logging filter functionality.
 */
public class PrettyLoggingFilter implements Filter {

    /**
     * Filters the API responses and prints them in logs.
     *
     * @param requestSpec  the request spec value
     * @param responseSpec the response spec value
     * @param ctx          the context value
     * @return the logged response
     */
    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {

        Response response = ctx.next(requestSpec, responseSpec);

        System.out.println("\nResponse status: " + response.getStatusLine());

        System.out.println("\nResponse Headers:");
        response.headers().forEach(h -> System.out.println(h.getName() + ": " + h.getValue())
        );

        System.out.println("\nResponse Body:");
        System.out.println(
                response.getBody().asPrettyString().isEmpty()
                        ? "<none>"
                        : response.getBody().asPrettyString()
        );

        System.out.println();

        return response;
    }
}
