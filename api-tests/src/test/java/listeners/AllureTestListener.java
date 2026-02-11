package listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Parameter;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class AllureTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        Method method = result.getMethod().getConstructorOrMethod().getMethod();

        String description = result.getMethod().getDescription();
        if (description == null || description.isBlank()) {
            description = method.getName();
        }

        Object[] paramsValues = result.getParameters();
        java.lang.reflect.Parameter[] parameters = method.getParameters();

        List<Parameter> allureParams = new ArrayList<>();
        String paramsSuffix = "";

        if (paramsValues != null && paramsValues.length > 0) {
            StringJoiner joiner = new StringJoiner(", ", " [", "]");

            for (int i = 0; i < paramsValues.length; i++) {
                String name = parameters[i].getName();
                String value = String.valueOf(paramsValues[i]);

                joiner.add(name + "=" + value);

                allureParams.add(new Parameter().setName(name).setValue(value));
            }

            paramsSuffix = joiner.toString();
        }

        final String finalName = description + paramsSuffix;
        final List<Parameter> finalParams = allureParams;

        Allure.getLifecycle().updateTestCase(tc -> {
            tc.setName(finalName);
            tc.setParameters(finalParams);
        });
    }
}
