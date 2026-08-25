package dto;

public class CreateUserRequestDto {

    private String name;
    private String job;

    private CreateUserRequestDto() {
    }

    public String getName() {
        return name;
    }

    public CreateUserRequestDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getJob() {
        return job;
    }

    public CreateUserRequestDto setJob(String job) {
        this.job = job;
        return this;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String job;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder job(String job) {
            this.job = job;
            return this;
        }

        public CreateUserRequestDto build() {
            var createUserRequestDto = new CreateUserRequestDto();
            createUserRequestDto.setName(name);
            createUserRequestDto.setJob(job);
            return createUserRequestDto;
        }
    }
}
