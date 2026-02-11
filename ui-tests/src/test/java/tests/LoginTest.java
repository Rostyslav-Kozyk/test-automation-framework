package tests;

import assertions.LoginAssertions;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void setUpLoginPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(
            description = "Verify login with valid credentials"
    )
    @Description("Verify login with valid credentials")
    public void loginWithValidCredentialsTest() {
        loginPage
                .open()
                .login(VALID_USERNAME, VALID_PASSWORD);

        LoginAssertions.verifyLoginSuccessful(driver);
    }

    @Test(
            dataProvider = "invalidLoginCredentials",
            description = "Verify login with invalid credentials"
    )
    @Description("Verify login with invalid credentials")
    public void loginWithInvalidCredentialsTest(String invalidUsername, String invalidPassword) {
        loginPage
                .open()
                .login(invalidUsername, invalidPassword);

        LoginAssertions.verifyLoginFailed(loginPage.getErrorMessage());
    }

    @DataProvider(name = "invalidLoginCredentials")
    public Object[][] invalidLoginCredentials() {
        return new Object[][]{
                {"invalid_user", "invalid_password"},
                {"invalid_user!@#$%^&*", "invalid_password!@#$%^&*"}
        };
    }
}
