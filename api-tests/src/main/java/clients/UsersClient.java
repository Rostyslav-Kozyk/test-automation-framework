package clients;

import dto.CreateUserRequestDto;
import dto.CreateUserResponseDto;
import dto.UserResponseDto;
import dto.UsersResponseDto;
import io.restassured.filter.Filter;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UsersClient extends BaseClient {

    private static final String USERS_URL = "/users";
    private static final String USER_URL = USERS_URL + "/%s";

    public UsersClient(List<Filter> filters) {
        super(filters);
    }

    public ApiResponse<UsersResponseDto> getUsers() {
        Response response = get(USERS_URL, "Get users");

        return new ApiResponse<>(response, UsersResponseDto.class);
    }

    public ApiResponse<UsersResponseDto> getUsers(int page) {
        Map<String, Integer> queryParams = Collections.singletonMap(QueryParam.PAGE.getQueryParam(), page);
        Response response = get(USERS_URL, queryParams, "Get users by page");

        return new ApiResponse<>(response, UsersResponseDto.class);
    }

    public ApiResponse<UserResponseDto> getUserById(int userId) {
        Response response = get(USER_URL, "Get user by id", userId);

        return new ApiResponse<>(response, UserResponseDto.class);
    }

    public ApiResponse<CreateUserResponseDto> createUser(CreateUserRequestDto request) {
        Response response = post(USERS_URL, request, "Create user");

        return new ApiResponse<>(response, CreateUserResponseDto.class);
    }
}
