package testdata;

import dto.CreateUserRequestDto;
import dto.UpdateUserRequestDto;

public final class ApiTestDataFactory {

    private ApiTestDataFactory() {
    }

    public static CreateUserRequestDto createUserRequest() {
        return CreateUserRequestDto.builder()
                .name("Rostyslav Kozyk")
                .job("Test Automation Engineer")
                .build();
    }

    public static UpdateUserRequestDto replaceUserRequest() {
        return UpdateUserRequestDto.builder()
                .name("Rostyslav Kozyk")
                .job("Senior Test Automation Engineer")
                .build();
    }

    public static UpdateUserRequestDto updateUserRequest() {
        return UpdateUserRequestDto.builder()
                .job("Lead Test Automation Engineer")
                .build();
    }

    public static Object[][] userPages() {
        return new Object[][]{{1}, {2}};
    }

    public static Object[][] invalidUserIds() {
        return new Object[][]{{9999}, {-1}};
    }
}
