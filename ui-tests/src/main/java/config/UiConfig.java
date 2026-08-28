package config;

import java.time.Duration;

public final class UiConfig {

    private static final String DEFAULT_BASE_URL = "https://www.saucedemo.com";
    private static final String DEFAULT_BROWSER = "chrome";
    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    private UiConfig() {
    }

    public static String getBaseUrl() {
        String baseUrl = System.getProperty("ui.base.url", DEFAULT_BASE_URL).trim();
        return baseUrl.replaceAll("/+$", "");
    }

    public static String getBrowser() {
        return System.getProperty("browser", DEFAULT_BROWSER).trim();
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    public static Duration getTimeout() {
        String configuredTimeout = System.getProperty(
                "ui.timeout.seconds",
                String.valueOf(DEFAULT_TIMEOUT_SECONDS)
        );

        try {
            int timeoutSeconds = Integer.parseInt(configuredTimeout);
            if (timeoutSeconds <= 0) {
                throw new IllegalArgumentException("UI timeout must be greater than zero");
            }
            return Duration.ofSeconds(timeoutSeconds);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "UI timeout must be a whole number of seconds: " + configuredTimeout,
                    exception
            );
        }
    }
}
