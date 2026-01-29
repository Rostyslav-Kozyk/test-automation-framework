package config;

public enum Config {

    API_KEY(require("API_KEY"));

    private final String value;

    Config(String value) {
        this.value = value;
    }

    private static String require(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Environment variable " + name + " is not set"
            );
        }
        return value;
    }

    public String getConfig() {
        return value;
    }
}
