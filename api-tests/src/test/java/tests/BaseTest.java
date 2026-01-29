package tests;

import filters.BlankLineFilter;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    void setUp() {
        RestAssured.filters(
                new BlankLineFilter(),
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );
    }
}
