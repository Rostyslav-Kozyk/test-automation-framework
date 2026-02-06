package tests;

import assertions.UsersAssertions;
import clients.ApiResponse;
import config.HttpStatusCode;
import dto.UserResponseDto;
import dto.UsersResponseDto;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UsersTest extends BaseTest {

    @Test
    @Description("Verify users retrieval")
    public void getUsersListTest() {
        var userPage = 1;
        ApiResponse<UsersResponseDto> response = usersClientWithSwagger.getUsers(userPage);

        UsersAssertions.verifyStatusCode(response.getResponse(), HttpStatusCode.OK.getCode());
        UsersAssertions.verifyUsersListNotEmpty(response.getBody());
    }

    @Test
    @Description("Verify retrieval of single user")
    public void getSingleUserByIdTest() {
        var userId = 2;
        ApiResponse<UserResponseDto> response = usersClientWithSwagger.getUserById(userId);

        UsersAssertions.verifyStatusCode(response.getResponse(), HttpStatusCode.OK.getCode());
        UsersAssertions.verifyUserId(response.getBody(), userId);
    }

    @Test(dataProvider = "invalidUserIds")
    @Description("Verify error with invalid user id")
    public void getSingleUserByInvalidIdTest(int invalidUserId) {
        ApiResponse<UserResponseDto> response = usersClient.getUserById(invalidUserId);

        UsersAssertions.verifyStatusCode(response.getResponse(), HttpStatusCode.NOT_FOUND.getCode());
        UsersAssertions.verifyEmptyResponseBody(response.getBody());
    }

    @DataProvider(name = "invalidUserIds")
    public Object[][] invalidUserIds() {
        return new Object[][]{
                {9999},
                {-1}
        };
    }
}
