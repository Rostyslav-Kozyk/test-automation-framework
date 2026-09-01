package clients;

/**
 * Defines the supported query param values.
 */
public enum QueryParam {

    PAGE("page");

    private final String queryParam;

    /**
     * Creates a new {@code QueryParam} instance.
     *
     * @param queryParam the query param value
     */
    QueryParam(String queryParam) {
        this.queryParam = queryParam;
    }

    /**
     * Returns query param.
     *
     * @return the query param
     */
    public String getQueryParam() {
        return queryParam;
    }
}
