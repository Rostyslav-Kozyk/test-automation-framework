package tests;

import assertions.LoginAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    private static final String VALID_USERNAME = "standard_user";
    private static final String VALID_PASSWORD = "secret_sauce";
    private static final String INVALID_USERNAME = "invalid_user";
    private static final String INVALID_PASSWORD = "invalid_password";

    @BeforeClass
    public void setUpLogin() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void shouldLoginSuccessfullyWithValidCredentials() {
        loginPage
                .open()
                .login(VALID_USERNAME, VALID_PASSWORD);

        LoginAssertions.assertLoginSuccessful(driver);
    }

    @Test
    public void shouldShowErrorMessageWithInvalidCredentials() {
        loginPage
                .open()
                .login(INVALID_USERNAME, INVALID_PASSWORD);

        LoginAssertions.assertLoginFailed(loginPage.getErrorMessage());
    }
}
