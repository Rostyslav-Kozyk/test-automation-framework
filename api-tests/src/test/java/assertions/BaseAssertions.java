package assertions;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import steps.StepExecutor;

import java.util.function.Consumer;

/**
 * Provides assertions for base behavior.
 */
public abstract class BaseAssertions {

    /**
     * Executes the assert step operation.
     *
     * @param name      the name value
     * @param assertion the assertion value
     */
    private static void assertStep(String name, Runnable assertion) {
        StepExecutor.step(name, assertion);
    }

    /**
     * Executes the assert equals operation.
     *
     * @param actual   the actual value
     * @param expected the expected value
     * @param message  the message value
     * @param action   the action value
     */
    protected static void assertEquals(Object actual, Object expected, String message, String action) {
        assertStep(action, () -> Assert.assertEquals(actual, expected, assertionMessage(message, expected, actual)));
    }

    /**
     * Executes the assert null operation.
     *
     * @param object  the object value
     * @param message the message value
     * @param action  the action value
     */
    protected static void assertNull(Object object, String message, String action) {
        assertStep(action, () -> Assert.assertNull(object, assertionMessage(message, null, object)));
    }

    /**
     * Executes the assert not null operation.
     *
     * @param object  the object value
     * @param message the message value
     * @param action  the action value
     */
    protected static void assertNotNull(Object object, String message, String action) {
        assertStep(action, () -> Assert.assertNotNull(object, assertionMessage(message, "not null", object)));
    }

    /**
     * Executes the assert true operation.
     *
     * @param condition the condition value
     * @param message   the message value
     * @param action    the action value
     */
    protected static void assertTrue(boolean condition, String message, String action) {
        assertStep(action, () -> Assert.assertTrue(condition, assertionMessage(message, true, condition)));
    }

    /**
     * Executes the assert false operation.
     *
     * @param condition the condition value
     * @param message   the message value
     * @param action    the action value
     */
    protected static void assertFalse(boolean condition, String message, String action) {
        assertStep(action, () -> Assert.assertFalse(condition, assertionMessage(message, false, condition)));
    }

    /**
     * Executes the assert softly operation.
     *
     * @param action     the action value
     * @param assertions the assertions value
     */
    protected static void assertSoftly(String action, Consumer<SoftAssert> assertions) {
        assertStep(action, () -> {
            SoftAssert softAssert = new SoftAssert();
            assertions.accept(softAssert);
            softAssert.assertAll();
        });
    }

    /**
     * Provides the assertion message.
     *
     * @param message  the message value
     * @param expected the expected value
     * @param actual   the actual value
     * @return the assertion message
     */
    protected static String assertionMessage(String message, Object expected, Object actual) {
        return String.format("%s | expected: <%s>, actual: <%s>", message, expected, actual);
    }

    /**
     * Verifies status code.
     *
     * @param response       the response value
     * @param expectedStatus the expected status value
     */
    public static void verifyStatusCode(Response response, int expectedStatus) {
        assertEquals(
                response.getStatusCode(),
                expectedStatus,
                "Unexpected HTTP status code",
                "Verify status code = " + expectedStatus
        );
    }
}
