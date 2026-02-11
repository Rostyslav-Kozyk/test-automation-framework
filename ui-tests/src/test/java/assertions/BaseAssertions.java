package assertions;

import org.testng.Assert;
import steps.StepExecutor;

public abstract class BaseAssertions {

    private static void assertStep(String name, Runnable assertion) {
        StepExecutor.step(name, assertion);
    }

    protected static void assertEquals(Object actual, Object expected, String message, String action) {
        assertStep(action, () -> Assert.assertEquals(actual, expected, message));
    }

    protected static void assertTrue(boolean condition, String message, String action) {
        assertStep(action, () -> Assert.assertTrue(condition, message));
    }
}
