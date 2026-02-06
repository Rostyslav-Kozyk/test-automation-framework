package clients;

import filters.BlankLineFilter;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import validations.SwaggerValidator;

import java.util.ArrayList;
import java.util.List;

public class ClientFactory {

    private static List<Filter> baseFilters() {
        return List.of(
                new BlankLineFilter(),
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
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
