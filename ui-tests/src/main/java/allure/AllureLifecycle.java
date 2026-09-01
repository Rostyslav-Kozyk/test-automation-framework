package allure;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Parameter;

import java.util.List;

/**
 * Provides allure lifecycle functionality.
 */
public class AllureLifecycle {

    /**
     * Updates test case name in Allure report.
     *
     * @param testCaseName       the test case name value
     * @param testCaseParameters the test case parameters value
     */
    public static void updateTestCase(String testCaseName, List<Parameter> testCaseParameters) {
        Allure.getLifecycle().updateTestCase(tc -> {
            tc.setName(testCaseName);
            tc.setParameters(testCaseParameters);
        });
    }
}
