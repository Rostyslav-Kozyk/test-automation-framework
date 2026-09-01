package clients;

import filters.AllureLoggingFilter;
import filters.PrettyLoggingFilter;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import validations.SwaggerValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Creates configured client instances.
 */
public class ClientFactory {

    /**
     * Creates the base filters.
     *
     * @return the base filters
     */
    private static List<Filter> baseFilters() {
        return List.of(
                new AllureLoggingFilter(),
                new RequestLoggingFilter(
                        LogDetail.ALL,
                        true,
                        System.out,
                        false,
                        Set.of("x-api-key")
                ),
                new PrettyLoggingFilter()
        );
    }

    /**
     * Returns users client.
     *
     * @return the users client
     */
    public static UsersClient getUsersClient() {
        return new UsersClient(baseFilters());
    }

    /**
     * Returns users client with swagger.
     *
     * @return the users client with swagger
     */
    public static UsersClient getUsersClientWithSwagger() {
        List<Filter> filters = new ArrayList<>(baseFilters());
        filters.add(SwaggerValidator.filter());

        return new UsersClient(filters);
    }
}
