package assertions;

import dto.*;

public class UsersAssertions extends BaseAssertions {

    public static void verifyPage(UsersResponseDto response, int expectedPage, int expectedUsersPerPage) {
        int actualPage = response.getPage();
        int actualUsersPerPage = response.getPerPage();

        assertEquals(actualPage, expectedPage, "Page does not match", "Verify page = " + expectedPage);
        assertEquals(
                actualUsersPerPage,
                expectedUsersPerPage,
                "Users per page does not match",
                "Verify users per page = " + expectedUsersPerPage);
    }

    public static void verifyUsersList(UsersResponseDto response, int expectedUsers) {
        assertNotNull(response.getData(), "Users list is null", "Verify users list not null");
        assertFalse(response.getData().isEmpty(), "Users list is empty", "Verify users list not empty");
        assertEquals(
                response.getData().size(),
                expectedUsers,
                "Users count does not match",
                "Verify users count = " + expectedUsers);
    }

    public static void verifyUserId(UserResponseDto response, int expectedUserId) {
        int actualUserId = response.getData().getId();

        assertEquals(
                actualUserId,
                expectedUserId,
                "User ID does not match",
                "Verify user ID = " + expectedUserId
        );
    }

    public static void verifyEmptyResponseBody(UserResponseDto response) {
        assertNull(response.getData(), "Expected empty response body", "Verify response body is null");
    }

    public static void verifyCreatedUser(
            CreateUserResponseDto response,
            CreateUserRequestDto request
    ) {
        assertEquals(response.getName(), request.getName(), "User name does not match", String.format("Verify created user name = %s", request.getName()));
        assertEquals(response.getJob(), request.getJob(), "User job does not match", String.format("Verify created user job = %s", request.getJob()));
        assertTrue(response.getId() != null && !response.getId().isBlank(), "User ID is blank", "Verify created user ID is not blank");
        assertTrue(
                response.getCreatedAt() != null && !response.getCreatedAt().isBlank(),
                "Creation timestamp is blank",
                "Verify user creation timestamp is not blank"
        );
    }

    public static void verifyUpdatedUser(
            UpdateUserResponseDto response,
            UpdateUserRequestDto request
    ) {
        if (request.getName() != null) {
            assertEquals(response.getName(), request.getName(), "User name does not match", String.format("Verify updated user name = %s", request.getName()));
        }
        if (request.getJob() != null) {
            assertEquals(response.getJob(), request.getJob(), "User job does not match", String.format("Verify updated user job = %s", request.getJob()));
        }
        assertTrue(
                response.getUpdatedAt() != null && !response.getUpdatedAt().isBlank(),
                "Update timestamp is blank",
                "Verify user update timestamp is not blank"
        );
    }
}
