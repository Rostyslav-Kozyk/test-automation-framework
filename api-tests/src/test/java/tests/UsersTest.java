package tests;

import assertions.UsersAssertions;
import clients.ApiResponse;
import config.HttpStatusCode;
import dto.UserResponseDto;
import dto.UsersResponseDto;
import org.testng.annotations.Test;

public class UsersTest extends BaseTest {

    @Test
    public void getUsersListTest() {
        var userPage = 1;
        ApiResponse<UsersResponseDto> response = usersClientWithSwagger.getUsers(userPage);

        UsersAssertions.verifyStatusCode(response.getResponse(), HttpStatusCode.OK.getCode());
        UsersAssertions.verifyUsersListNotEmpty(response.getBody());
    }

    @Test
    public void getSingleUserByIdTest() {
        var userId = 2;
        ApiResponse<UserResponseDto> response = usersClientWithSwagger.getUserById(userId);

        UsersAssertions.verifyStatusCode(response.getResponse(), HttpStatusCode.OK.getCode());
        UsersAssertions.verifyUserId(response.getBody(), userId);
    }
}
