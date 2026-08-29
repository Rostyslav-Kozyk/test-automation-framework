package listeners;

import config.RequestSpecFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Parameter;
import org.testng.IExecutionListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;

public class AllureTestListener implements ITestListener, IExecutionListener {

    @Override
    public void onExecutionStart() {
        Properties environment = new Properties();
        environment.setProperty("API environment", System.getProperty("env", "PROD").toUpperCase());
        environment.setProperty("API base URL", RequestSpecFactory.getBaseUrl());
        environment.setProperty("Java version", System.getProperty("java.version"));
        environment.setProperty("Operating system", System.getProperty("os.name"));

        mergeEnvironmentProperties(environment);
    }

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

    private void mergeEnvironmentProperties(Properties environment) {
        Path resultsDirectory = Path.of(System.getProperty(
                "allure.results.directory",
                "target/allure-results"
        ));
        Path environmentFile = resultsDirectory.resolve("environment.properties");

        try {
            Files.createDirectories(resultsDirectory);
            Properties mergedEnvironment = new Properties();

            if (Files.exists(environmentFile)) {
                try (InputStream input = Files.newInputStream(environmentFile)) {
                    mergedEnvironment.load(input);
                }
            }

            mergedEnvironment.putAll(environment);
            try (OutputStream output = Files.newOutputStream(environmentFile)) {
                mergedEnvironment.store(output, "Allure environment");
            }
        } catch (IOException exception) {
            System.err.println("Could not write Allure environment properties: " + exception.getMessage());
        }
    }
}
