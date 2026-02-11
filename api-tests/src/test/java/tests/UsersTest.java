package tests;

import assertions.UsersAssertions;
import clients.ApiResponse;
import clients.HttpStatusCode;
import dto.UserResponseDto;
import dto.UsersResponseDto;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UsersTest extends BaseTest {

    private final int EXPECTED_USERS_PER_PAGE = 6;

    @Test(
            description = "Verify users retrieval"
    )
    @Description("Verify users retrieval")
    public void getUsersListTest() {
        var expectedUsersPage = 1;

        ApiResponse<UsersResponseDto> response = usersClientWithSwagger.getUsers();

        UsersAssertions.verifyStatusCode(response.getResponse(), HttpStatusCode.OK.getCode());
        UsersAssertions.verifyPage(response.getBody(), expectedUsersPage, EXPECTED_USERS_PER_PAGE);
        UsersAssertions.verifyUsersList(response.getBody(), EXPECTED_USERS_PER_PAGE);
    }

    @Test(
            dataProvider = "usersPage",
            description = "Verify retrieval of users by page"
    )
    @Description("Verify retrieval of users by page")
    public void getUsersListByPageTest(int usersPage) {
        ApiResponse<UsersResponseDto> response = usersClientWithSwagger.getUsers(usersPage);

        UsersAssertions.verifyStatusCode(response.getResponse(), HttpStatusCode.OK.getCode());
        UsersAssertions.verifyPage(response.getBody(), usersPage, EXPECTED_USERS_PER_PAGE);
        UsersAssertions.verifyUsersList(response.getBody(), EXPECTED_USERS_PER_PAGE);
    }

    @Test(
            description = "Verify retrieval of single user"
    )
    @Description("Verify retrieval of single user")
    public void getSingleUserByIdTest() {
        var userId = 2;

        ApiResponse<UserResponseDto> response = usersClientWithSwagger.getUserById(userId);

        UsersAssertions.verifyStatusCode(response.getResponse(), HttpStatusCode.OK.getCode());
        UsersAssertions.verifyUserId(response.getBody(), userId);
    }

    @Test(
            dataProvider = "invalidUserIds",
            description = "Verify retrieval of single user with invalid user id"
    )
    @Description("Verify retrieval of single user with invalid user id")
    public void getSingleUserByInvalidIdTest(int invalidUserId) {
        ApiResponse<UserResponseDto> response = usersClient.getUserById(invalidUserId);

        UsersAssertions.verifyStatusCode(response.getResponse(), HttpStatusCode.NOT_FOUND.getCode());
        UsersAssertions.verifyEmptyResponseBody(response.getBody());
    }

    @DataProvider(name = "usersPage")
    public Object[][] usersPage() {
        return new Object[][]{
                {1},
                {2}
        };
    }

    @DataProvider(name = "invalidUserIds")
    public Object[][] invalidUserIds() {
        return new Object[][]{
                {9999},
                {-1}
        };
    }
}
