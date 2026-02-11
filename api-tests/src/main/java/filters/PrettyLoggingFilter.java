package filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class PrettyLoggingFilter implements Filter {

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
