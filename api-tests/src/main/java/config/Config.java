package config;

/**
 * Defines the supported config values.
 */
public enum Config {

    API_KEY(require("API_KEY"));

    private final String value;

    /**
     * Creates a new {@code Config} instance.
     *
     * @param value the value
     */
    Config(String value) {
        this.value = value;
    }

    /**
     * Verifies the presence of requested config.
     *
     * @param name the name value
     * @return the config
     */
    private static String require(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Environment variable " + name + " is not set"
            );
        }
        return value;
    }

    /**
     * Returns config.
     *
     * @return the config
     */
    public String getConfig() {
        return value;
    }
}
