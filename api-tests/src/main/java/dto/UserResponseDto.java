package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDto {

    @JsonProperty("data")
    private UserDto data;

    public UserDto getData() {
        return data;
    }
}
