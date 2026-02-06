package clients;

import config.RequestSpecFactory;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class BaseClient {

    protected RequestSpecification baseRequest(List<Filter> filters) {
        RequestSpecification spec = RequestSpecFactory.baseSpec();

        if (filters != null && !filters.isEmpty()) {
            spec.filters(filters);
        }

        return RestAssured.given(spec);
    }
}
