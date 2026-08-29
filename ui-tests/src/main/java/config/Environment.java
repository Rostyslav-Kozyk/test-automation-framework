package config;

public enum Environment {

    DEV("https://dev.saucedemo.com"),
    QA("https://qa.saucedemo.com"),
    PROD("https://www.saucedemo.com");

    private final String baseUrl;

    Environment(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
