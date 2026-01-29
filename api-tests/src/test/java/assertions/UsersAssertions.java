package assertions;

import dto.UserResponseDto;
import dto.UsersResponseDto;
import io.restassured.response.Response;
import org.testng.Assert;

public class UsersAssertions {

    public static void verifyStatusCode(Response response, int expectedStatus) {
        Assert.assertEquals(
                response.getStatusCode(),
                expectedStatus,
                "Unexpected HTTP status code"
        );
    }

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
}
