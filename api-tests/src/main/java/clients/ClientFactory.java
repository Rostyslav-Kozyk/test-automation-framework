package clients;

import filters.AllureLoggingFilter;
import filters.PrettyLoggingFilter;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import validations.SwaggerValidator;

import java.util.ArrayList;
import java.util.List;

public class ClientFactory {

    private static List<Filter> baseFilters() {
        return List.of(
                new AllureLoggingFilter(),
                new RequestLoggingFilter(),
                new PrettyLoggingFilter()
        );
    }

    public static UsersClient getUsersClient() {
        return new UsersClient(baseFilters());
    }

    public static UsersClient getUsersClientWithSwagger() {
        List<Filter> filters = new ArrayList<>(baseFilters());
        filters.add(SwaggerValidator.filter());

        return new UsersClient(filters);
    }
}
