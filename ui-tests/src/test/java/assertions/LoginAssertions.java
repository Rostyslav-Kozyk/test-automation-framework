package assertions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginAssertions {

    private static final String WRONG_USERNAME_AND_PASSWORD_ERROR_MESSAGE =
            "Epic sadface: Username and password do not match any user in this service";

    public static void assertLoginSuccessful(WebDriver driver) {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("inventory"),
                "Expected to be on inventory page after successful login, but was: " + currentUrl
        );
    }

    public static void assertLoginFailed(String actualErrorMessage) {
        Assert.assertEquals(
                actualErrorMessage, WRONG_USERNAME_AND_PASSWORD_ERROR_MESSAGE,
                "Expected login error message, but got: " + actualErrorMessage);
    }
}
