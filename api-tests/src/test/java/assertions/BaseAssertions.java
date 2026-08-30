package assertions;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import steps.StepExecutor;

import java.util.function.Consumer;

public abstract class BaseAssertions {

    private static void assertStep(String name, Runnable assertion) {
        StepExecutor.step(name, assertion);
    }

    protected static void assertEquals(Object actual, Object expected, String message, String action) {
        assertStep(action, () -> Assert.assertEquals(actual, expected, assertionMessage(message, expected, actual)));
    }

    protected static void assertNull(Object object, String message, String action) {
        assertStep(action, () -> Assert.assertNull(object, assertionMessage(message, null, object)));
    }

    protected static void assertNotNull(Object object, String message, String action) {
        assertStep(action, () -> Assert.assertNotNull(object, assertionMessage(message, "not null", object)));
    }

    protected static void assertTrue(boolean condition, String message, String action) {
        assertStep(action, () -> Assert.assertTrue(condition, assertionMessage(message, true, condition)));
    }

    protected static void assertFalse(boolean condition, String message, String action) {
        assertStep(action, () -> Assert.assertFalse(condition, assertionMessage(message, false, condition)));
    }

    protected static void assertSoftly(String action, Consumer<SoftAssert> assertions) {
        assertStep(action, () -> {
            SoftAssert softAssert = new SoftAssert();
            assertions.accept(softAssert);
            softAssert.assertAll();
        });
    }

    protected static String assertionMessage(String message, Object expected, Object actual) {
        return String.format("%s | expected: <%s>, actual: <%s>", message, expected, actual);
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
