package assertions;

import dto.UserResponseDto;
import dto.UsersResponseDto;

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
}
