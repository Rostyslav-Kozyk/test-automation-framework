package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the user response data transferred by the API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDto {

    @JsonProperty("data")
    private UserDto data;

    /**
     * Returns data.
     *
     * @return the data
     */
    public UserDto getData() {
        return data;
    }
}
