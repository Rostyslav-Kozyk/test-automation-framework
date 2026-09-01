package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the update user response data transferred by the API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUserResponseDto {

    @JsonProperty("name")
    private String name;
    @JsonProperty("job")
    private String job;
    @JsonProperty("updatedAt")
    private String updatedAt;

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
     * Returns updated at.
     *
     * @return the updated at
     */
    public String getUpdatedAt() {
        return updatedAt;
    }
}
