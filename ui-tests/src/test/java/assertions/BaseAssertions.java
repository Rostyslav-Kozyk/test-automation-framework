package assertions;

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
}
