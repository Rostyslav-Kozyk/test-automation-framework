package steps;

import io.qameta.allure.Allure;
import io.qameta.allure.model.StepResult;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * Provides step executor functionality.
 */
public class StepExecutor {

    /**
     * Prevents instantiation of this utility class.
     */
    private StepExecutor() {
    }

    /**
     * Executes the API step.
     *
     * @param name   the name value
     * @param action the action value
     * @return the executed step
     */
    public static <T> T step(String name, Supplier<T> action) {
        String uuid = UUID.randomUUID().toString();

        Allure.getLifecycle().startStep(
                uuid,
                new StepResult().setName(name)
        );

        try {
            T result = action.get();
            Allure.getLifecycle().updateStep(uuid, s -> s.setStatus(io.qameta.allure.model.Status.PASSED));
            return result;
        } catch (Throwable t) {
            Allure.getLifecycle().updateStep(uuid, s -> s.setStatus(io.qameta.allure.model.Status.FAILED));
            throw t;
        } finally {
            Allure.getLifecycle().stopStep(uuid);
        }
    }

    /**
     * Executes the API step.
     *
     * @param name   the name value
     * @param action the action value
     */
    public static void step(String name, Runnable action) {
        step(name, () -> {
            action.run();
            return null;
        });
    }
}
