package assertions;

import io.restassured.response.Response;
import org.testng.Assert;
import steps.StepExecutor;

public abstract class BaseAssertions {

    private static void assertStep(String name, Runnable assertion) {
        StepExecutor.step(name, assertion);
    }

    protected static void assertEquals(Object actual, Object expected, String message, String action) {
        assertStep(action, () -> Assert.assertEquals(actual, expected, message));
    }

    protected static void assertNull(Object object, String message, String action) {
        assertStep(action, () -> Assert.assertNull(object, message));
    }

    protected static void assertNotNull(Object object, String message, String action) {
        assertStep(action, () -> Assert.assertNotNull(object, message));
    }

    protected static void assertFalse(boolean condition, String message, String action) {
        assertStep(action, () -> Assert.assertFalse(condition, message));
    }

    public static void verifyStatusCode(Response response, int expectedStatus) {
        assertEquals(
                response.getStatusCode(),
                expectedStatus,
                "Unexpected HTTP status code",
                "Verify status code = " + expectedStatus
        );
    }
}
