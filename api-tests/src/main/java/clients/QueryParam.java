package clients;

public enum QueryParam {

    PAGE("page");

    private final String queryParam;

    QueryParam(String queryParam) {
        this.queryParam = queryParam;
    }

    public String getQueryParam() {
        return queryParam;
    }
}
