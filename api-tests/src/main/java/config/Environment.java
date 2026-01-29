package config;

public enum Environment {

    DEV("https://dev.reqres.in/api"),
    QA("https://qa.reqres.in/api"),
    PROD("https://reqres.in/api");

    private final String baseUrl;

    Environment(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
