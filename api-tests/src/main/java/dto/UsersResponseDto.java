package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents the users response data transferred by the API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersResponseDto {

    @JsonProperty("page")
    private int page;
    @JsonProperty("per_page")
    private int perPage;
    @JsonProperty("total")
    private int total;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("data")
    private List<UserDto> data;

    /**
     * Returns page.
     *
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * Returns per page.
     *
     * @return the per page
     */
    public int getPerPage() {
        return perPage;
    }

    /**
     * Returns total.
     *
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Returns total pages.
     *
     * @return the total pages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Returns user data.
     *
     * @return the user data
     */
    public List<UserDto> getData() {
        return data == null ? null : List.copyOf(data);
    }
}
