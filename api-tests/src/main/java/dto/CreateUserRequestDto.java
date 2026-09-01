package dto;

/**
 * Represents the create user request data transferred by the API.
 */
public class CreateUserRequestDto {

    private String name;
    private String job;

    /**
     * Creates an empty {@code CreateUserRequestDto} instance for controlled construction.
     */
    private CreateUserRequestDto() {
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
    public CreateUserRequestDto setName(String name) {
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
    public CreateUserRequestDto setJob(String job) {
        this.job = job;
        return this;
    }

    /**
     * Creates a new {@code CreateUserRequestDto} instance.
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
         * Creates an empty {@code CreateUserRequestDto} instance for controlled construction.
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
         * @return the create user request DTO
         */
        public CreateUserRequestDto build() {
            var createUserRequestDto = new CreateUserRequestDto();
            createUserRequestDto.setName(name);
            createUserRequestDto.setJob(job);
            return createUserRequestDto;
        }
    }
}
