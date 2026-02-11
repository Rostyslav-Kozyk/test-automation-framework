package assertions;

import org.openqa.selenium.WebDriver;

public class LoginAssertions extends BaseAssertions {

    private static final String WRONG_USERNAME_AND_PASSWORD_ERROR_MESSAGE =
            "Epic sadface: Username and password do not match any user in this service";

    public static void verifyLoginSuccessful(WebDriver driver) {
        String currentUrl = driver.getCurrentUrl();

        assertTrue(
                currentUrl.contains("inventory"),
                "Expected to be on inventory page after successful login, but was: " + currentUrl,
                "Verify login is successful"
        );
    }

    public static void verifyLoginFailed(String actualErrorMessage) {
        assertEquals(
                actualErrorMessage,
                WRONG_USERNAME_AND_PASSWORD_ERROR_MESSAGE,
                "Expected login error message, but got: " + actualErrorMessage,
                "Verify login error message"
        );
    }
}
