package clients;

import dto.CreateUserRequestDto;
import dto.CreateUserResponseDto;
import dto.UpdateUserRequestDto;
import dto.UpdateUserResponseDto;
import dto.UserResponseDto;
import dto.UsersResponseDto;
import io.restassured.filter.Filter;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Provides users client functionality.
 */
public class UsersClient extends BaseClient {

    private static final String USERS_URL = "/users";
    private static final String USER_URL = USERS_URL + "/%s";

    /**
     * Creates a new {@code UsersClient} instance.
     *
     * @param filters the filters value
     */
    public UsersClient(List<Filter> filters) {
        super(filters);
    }

    /**
     * Returns users.
     *
     * @return the users
     */
    public ApiResponse<UsersResponseDto> getUsers() {
        Response response = get(USERS_URL, "Get users");

        return new ApiResponse<>(response, UsersResponseDto.class);
    }

    /**
     * Returns users by page.
     *
     * @param page the page value
     * @return the users
     */
    public ApiResponse<UsersResponseDto> getUsers(int page) {
        Map<String, Integer> queryParams = Collections.singletonMap(QueryParam.PAGE.getQueryParam(), page);
        Response response = get(USERS_URL, queryParams, "Get users by page");

        return new ApiResponse<>(response, UsersResponseDto.class);
    }

    /**
     * Returns user by id.
     *
     * @param userId the user id value
     * @return the user
     */
    public ApiResponse<UserResponseDto> getUserById(int userId) {
        Response response = get(USER_URL, "Get user by id", userId);

        return new ApiResponse<>(response, UserResponseDto.class);
    }

    /**
     * Creates user.
     *
     * @param request the request value
     * @return the create user response
     */
    public ApiResponse<CreateUserResponseDto> createUser(CreateUserRequestDto request) {
        Response response = post(USERS_URL, request, "Create user");

        return new ApiResponse<>(response, CreateUserResponseDto.class);
    }

    /**
     * Replaces user.
     *
     * @param userId  the user id value
     * @param request the request value
     * @return the replace user response
     */
    public ApiResponse<UpdateUserResponseDto> replaceUser(int userId, UpdateUserRequestDto request) {
        Response response = put(USER_URL, request, "Replace user", userId);

        return new ApiResponse<>(response, UpdateUserResponseDto.class);
    }

    /**
     * Updates user.
     *
     * @param userId  the user id value
     * @param request the request value
     * @return the update user response
     */
    public ApiResponse<UpdateUserResponseDto> updateUser(int userId, UpdateUserRequestDto request) {
        Response response = patch(USER_URL, request, "Update user", userId);

        return new ApiResponse<>(response, UpdateUserResponseDto.class);
    }

    /**
     * Deletes user.
     *
     * @param userId the user id value
     * @return the delete user response
     */
    public Response deleteUser(int userId) {
        return delete(USER_URL, "Delete user", userId);
    }
}
