package assertions;

import io.restassured.response.Response;
import org.testng.Assert;

public class BaseAssertions {

    public static void verifyStatusCode(Response response, int expectedStatus) {
        Assert.assertEquals(
                response.getStatusCode(),
                expectedStatus,
                "Unexpected HTTP status code"
        );
    }
}
