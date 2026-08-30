package assertions;

import dto.CreateUserRequestDto;
import dto.CreateUserResponseDto;
import dto.UpdateUserRequestDto;
import dto.UpdateUserResponseDto;
import dto.UserResponseDto;
import dto.UsersResponseDto;
import io.restassured.response.Response;

public class UsersAssertions extends BaseAssertions {

    public static void verifyPage(UsersResponseDto response, int expectedPage, int expectedUsersPerPage) {
        int actualPage = response.getPage();
        int actualUsersPerPage = response.getPerPage();

        assertSoftly("Verify users page metadata", softly -> {
            softly.assertEquals(
                    actualPage,
                    expectedPage,
                    assertionMessage("Page does not match", expectedPage, actualPage)
            );
            softly.assertEquals(
                    actualUsersPerPage,
                    expectedUsersPerPage,
                    assertionMessage("Users per page does not match", expectedUsersPerPage, actualUsersPerPage)
            );
        });
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
        assertSoftly("Verify created user details", softly -> {
            softly.assertEquals(
                    response.getName(),
                    request.getName(),
                    assertionMessage("User name does not match", request.getName(), response.getName())
            );
            softly.assertEquals(
                    response.getJob(),
                    request.getJob(),
                    assertionMessage("User job does not match", request.getJob(), response.getJob())
            );
            softly.assertTrue(
                    response.getId() != null && !response.getId().isBlank(),
                    assertionMessage("User ID is blank", "non-blank value", response.getId())
            );
            softly.assertTrue(
                    response.getCreatedAt() != null && !response.getCreatedAt().isBlank(),
                    assertionMessage("Creation timestamp is blank", "non-blank value", response.getCreatedAt())
            );
        });
    }

    public static void verifyUpdatedUser(
            UpdateUserResponseDto response,
            UpdateUserRequestDto request
    ) {
        assertSoftly("Verify updated user details", softly -> {
            if (request.getName() != null) {
                softly.assertEquals(
                        response.getName(),
                        request.getName(),
                        assertionMessage("User name does not match", request.getName(), response.getName())
                );
            }
            if (request.getJob() != null) {
                softly.assertEquals(
                        response.getJob(),
                        request.getJob(),
                        assertionMessage("User job does not match", request.getJob(), response.getJob())
                );
            }
            softly.assertTrue(
                    response.getUpdatedAt() != null && !response.getUpdatedAt().isBlank(),
                    assertionMessage("Update timestamp is blank", "non-blank value", response.getUpdatedAt())
            );
        });
    }

    public static void verifyResponseBodyIsEmpty(Response response) {
        assertTrue(
                response.getBody().asString().isEmpty(),
                "Response body is not empty",
                "Verify response body is empty"
        );
    }
}
