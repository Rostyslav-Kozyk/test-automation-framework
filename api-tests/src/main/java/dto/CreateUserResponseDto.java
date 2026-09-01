package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the create user response data transferred by the API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserResponseDto {

    @JsonProperty("name")
    private String name;
    @JsonProperty("job")
    private String job;
    @JsonProperty("id")
    private String id;
    @JsonProperty("createdAt")
    private String createdAt;

    /**
     * Returns name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns job.
     *
     * @return the job
     */
    public String getJob() {
        return job;
    }

    /**
     * Returns id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns created at.
     *
     * @return the created at
     */
    public String getCreatedAt() {
        return createdAt;
    }
}
