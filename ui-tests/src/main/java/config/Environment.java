package config;

/**
 * Defines the supported environment base URLs.
 */
public enum Environment {

    DEV("https://dev.saucedemo.com"),
    QA("https://qa.saucedemo.com"),
    PROD("https://www.saucedemo.com");

    private final String baseUrl;

    /**
     * Creates a new {@code Environment} instance.
     *
     * @param baseUrl the base url value
     */
    Environment(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Returns base url.
     *
     * @return the base url
     */
    public String getBaseUrl() {
        return baseUrl;
    }
}
