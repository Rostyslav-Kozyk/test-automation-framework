package clients;

import dto.UserResponseDto;
import dto.UsersResponseDto;
import io.restassured.response.Response;

public class UsersClient extends BaseClient {

    private static final String USERS_URL = "/users";
    private static final String USER_URL = USERS_URL + "/%s";

    public ApiResponse<UsersResponseDto> getUsers(int page) {
        Response response = baseRequest()
                .queryParam("page", page)
                .when()
                .get(USERS_URL);

        return new ApiResponse<>(response, UsersResponseDto.class);
    }

    public ApiResponse<UserResponseDto> getUserById(int userId) {
        Response response = baseRequest()
                .when()
                .get(String.format(USER_URL, userId));

        return new ApiResponse<>(response, UserResponseDto.class);
    }
}
