package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the user data transferred by the API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @JsonProperty("id")
    private int id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("avatar")
    private String avatar;

    /**
     * Returns id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns avatar.
     *
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }
}
