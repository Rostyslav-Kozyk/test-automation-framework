package dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserRequestDto {

    private String name;
    private String job;

    private UpdateUserRequestDto() {
    }

    public String getName() {
        return name;
    }

    public UpdateUserRequestDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getJob() {
        return job;
    }

    public UpdateUserRequestDto setJob(String job) {
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

        public UpdateUserRequestDto build() {
            var updateUserRequestDto = new UpdateUserRequestDto();
            updateUserRequestDto.setName(name);
            updateUserRequestDto.setJob(job);
            return updateUserRequestDto;
        }
    }
}
