package assertions;

import dto.CreateUserRequestDto;
import dto.CreateUserResponseDto;
import dto.UpdateUserRequestDto;
import dto.UpdateUserResponseDto;
import dto.UserResponseDto;
import dto.UsersResponseDto;
import io.restassured.response.Response;

/**
 * Provides assertions for users behavior.
 */
public class UsersAssertions extends BaseAssertions {

    /**
     * Verifies page in response.
     *
     * @param response             the response value
     * @param expectedPage         the expected page value
     * @param expectedUsersPerPage the expected users per page value
     */
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

    /**
     * Verifies users list in response.
     *
     * @param response      the response value
     * @param expectedUsers the expected users value
     */
    public static void verifyUsersList(UsersResponseDto response, int expectedUsers) {
        assertNotNull(response.getData(), "Users list is null", "Verify users list not null");
        assertFalse(response.getData().isEmpty(), "Users list is empty", "Verify users list not empty");
        assertEquals(
                response.getData().size(),
                expectedUsers,
                "Users count does not match",
                "Verify users count = " + expectedUsers);
    }

    /**
     * Verifies user id in response.
     *
     * @param response       the response value
     * @param expectedUserId the expected user id value
     */
    public static void verifyUserId(UserResponseDto response, int expectedUserId) {
        int actualUserId = response.getData().getId();

        assertEquals(
                actualUserId,
                expectedUserId,
                "User ID does not match",
                "Verify user ID = " + expectedUserId
        );
    }

    /**
     * Verifies empty response body.
     *
     * @param response the response value
     */
    public static void verifyEmptyResponseBody(UserResponseDto response) {
        assertNull(response.getData(), "Expected empty response body", "Verify response body is null");
    }

    /**
     * Verifies created user in response.
     *
     * @param response the response value
     * @param request  the request value
     */
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

    /**
     * Verifies updated user in response.
     *
     * @param response the response value
     * @param request  the request value
     */
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

    /**
     * Verifies response body is empty.
     *
     * @param response the response value
     */
    public static void verifyResponseBodyIsEmpty(Response response) {
        assertTrue(
                response.getBody().asString().isEmpty(),
                "Response body is not empty",
                "Verify response body is empty"
        );
    }
}
