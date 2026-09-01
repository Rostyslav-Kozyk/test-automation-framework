package dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents the update user request data transferred by the API.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserRequestDto {

    private String name;
    private String job;

    /**
     * Creates an empty {@code UpdateUserRequestDto} instance for controlled construction.
     */
    private UpdateUserRequestDto() {
    }

    /**
     * Returns name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name value
     * @return the class
     */
    public UpdateUserRequestDto setName(String name) {
        this.name = name;
        return this;
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
     * Sets job.
     *
     * @param job the job value
     * @return the class
     */
    public UpdateUserRequestDto setJob(String job) {
        this.job = job;
        return this;
    }

    /**
     * Creates a new {@code UpdateUserRequestDto} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Provides builder functionality.
     */
    public static class Builder {

        private String name;
        private String job;

        /**
         * Creates an empty {@code UpdateUserRequestDto} instance for controlled construction.
         */
        private Builder() {
        }

        /**
         * Sets the name.
         *
         * @param name the name value
         * @return the builder
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the job.
         *
         * @param job the job value
         * @return the builder
         */
        public Builder job(String job) {
            this.job = job;
            return this;
        }

        /**
         * Executes the build operation.
         *
         * @return the update user request DTO
         */
        public UpdateUserRequestDto build() {
            var updateUserRequestDto = new UpdateUserRequestDto();
            updateUserRequestDto.setName(name);
            updateUserRequestDto.setJob(job);
            return updateUserRequestDto;
        }
    }
}
