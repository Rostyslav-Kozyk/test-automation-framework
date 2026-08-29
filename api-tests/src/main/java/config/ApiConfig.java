package config;

import java.util.Locale;

public final class ApiConfig {

    private ApiConfig() {
    }

    public static String getBaseUrl() {
        String customBaseUrl = System.getProperty("api.base.url");
        if (customBaseUrl != null && !customBaseUrl.isBlank()) {
            return customBaseUrl.trim().replaceAll("/+$", "");
        }

        return getEnvironment().getBaseUrl();
    }

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
