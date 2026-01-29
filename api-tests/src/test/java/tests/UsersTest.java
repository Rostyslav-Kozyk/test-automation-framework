package tests;

import assertions.UsersAssertions;
import clients.ApiResponse;
import clients.UsersClient;
import config.HttpStatusCode;
import dto.UserResponseDto;
import dto.UsersResponseDto;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UsersTest extends BaseTest {

    private UsersClient usersClient;

    @BeforeClass
    public void setUpUsersEndpoint() {
        usersClient = new UsersClient();
    }

    @Test
    public void getUsersListTest() {
        var userPage = 1;
        ApiResponse<UsersResponseDto> response = usersClient.getUsers(userPage);

        UsersAssertions.verifyStatusCode(response.getResponse(), HttpStatusCode.OK.getCode());
        UsersAssertions.verifyUsersListNotEmpty(response.getBody());
    }

    @Test
    public void getSingleUserByIdTest() {
        var userId = 2;
        ApiResponse<UserResponseDto> response = usersClient.getUserById(userId);

        UsersAssertions.verifyStatusCode(response.getResponse(), HttpStatusCode.OK.getCode());
        UsersAssertions.verifyUserId(response.getBody(), userId);
    }
}
