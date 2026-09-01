package testdata;

import dto.CreateUserRequestDto;
import dto.UpdateUserRequestDto;

/**
 * Creates configured API test data instances.
 */
public final class ApiTestDataFactory {

    /**
     * Prevents instantiation of this utility class.
     */
    private ApiTestDataFactory() {
    }

    /**
     * Provides valid create user request body.
     *
     * @return the create user request body
     */
    public static CreateUserRequestDto createUserRequest() {
        return CreateUserRequestDto.builder()
                .name("Rostyslav Kozyk")
                .job("Test Automation Engineer")
                .build();
    }

    /**
     * Provides valid replace user request body.
     *
     * @return the replace user request body
     */
    public static UpdateUserRequestDto replaceUserRequest() {
        return UpdateUserRequestDto.builder()
                .name("Rostyslav Kozyk")
                .job("Senior Test Automation Engineer")
                .build();
    }

    /**
     * Provides valid update user request body.
     *
     * @return the update user request body
     */
    public static UpdateUserRequestDto updateUserRequest() {
        return UpdateUserRequestDto.builder()
                .job("Lead Test Automation Engineer")
                .build();
    }

    /**
     * Provides valid user pages parameter.
     *
     * @return the user pages parameter
     */
    public static Object[][] userPages() {
        return new Object[][]{{1}, {2}};
    }

    /**
     * Provides invalid user ids parameter.
     *
     * @return the user ids parameter
     */
    public static Object[][] invalidUserIds() {
        return new Object[][]{{9999}, {-1}};
    }
}
