package assertions;

import dto.UserResponseDto;
import dto.UsersResponseDto;
import org.testng.Assert;

public class UsersAssertions extends BaseAssertions {

    public static void verifyUsersListNotEmpty(UsersResponseDto response) {
        Assert.assertNotNull(response.getData(), "Users list is null");
        Assert.assertFalse(response.getData().isEmpty(), "Users list is empty");
    }

    public static void verifyUserId(UserResponseDto response, int expectedUserId) {
        int actualUserId = response.getData().getId();
        Assert.assertEquals(
                actualUserId,
                expectedUserId,
                "User ID does not match"
        );
    }

    public static void verifyEmptyResponseBody(UserResponseDto response) {
        Assert.assertNull(
                response.getData(),
                "Expected empty response body"
        );
    }
}
