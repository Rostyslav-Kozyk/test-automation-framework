package config;

import java.util.Locale;

/**
 * Provides API config functionality.
 */
public final class ApiConfig {

    /**
     * Prevents instantiation of this utility class.
     */
    private ApiConfig() {
    }

    /**
     * Returns environment or custom base URL for API tests.
     *
     * @return the base url
     */
    public static String getBaseUrl() {
        String customBaseUrl = System.getProperty("api.base.url");
        if (customBaseUrl != null && !customBaseUrl.isBlank()) {
            return customBaseUrl.trim().replaceAll("/+$", "");
        }

        return getEnvironment().getBaseUrl();
    }

    /**
     * Returns environment.
     *
     * @return the environment variable
     */
    public static Environment getEnvironment() {
        String environmentName = System.getProperty("env", "PROD")
                .trim()
                .toUpperCase(Locale.ROOT);

        try {
            return Environment.valueOf(environmentName);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Unsupported API environment '" + environmentName +
                            "'. Supported values: DEV, QA, PROD",
                    exception
            );
        }
    }
}
