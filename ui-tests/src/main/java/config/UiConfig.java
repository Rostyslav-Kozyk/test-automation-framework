package config;

import java.time.Duration;
import java.util.Locale;
import java.util.Optional;

/**
 * Provides UI config functionality.
 */
public final class UiConfig {

    private static final String DEFAULT_BROWSER = "chrome";
    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    /**
     * Prevents instantiation of this utility class.
     */
    private UiConfig() {
    }

    /**
     * Returns environment or custom base URL for UI tests.
     *
     * @return the base url
     */
    public static String getBaseUrl() {
        String customBaseUrl = System.getProperty("ui.base.url");
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
                    "Unsupported UI environment '" + environmentName +
                            "'. Supported values: DEV, QA, PROD",
                    exception
            );
        }
    }

    /**
     * Returns browser.
     *
     * @return the browser
     */
    public static String getBrowser() {
        return System.getProperty("browser", DEFAULT_BROWSER).trim();
    }

    /**
     * Checks whether browser is in headless mode.
     *
     * @return the boolean value
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    /**
     * Returns remote url.
     *
     * @return the remote url
     */
    public static Optional<String> getRemoteUrl() {
        String remoteUrl = System.getProperty("remote.url");
        if (remoteUrl == null || remoteUrl.isBlank()) {
            return Optional.empty();
        }

        return Optional.of(remoteUrl.trim().replaceAll("/+$", ""));
    }

    /**
     * Returns timeout value for UI tests.
     *
     * @return the timeout value
     */
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
