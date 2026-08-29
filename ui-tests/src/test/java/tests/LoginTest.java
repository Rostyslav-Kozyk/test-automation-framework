package tests;

import assertions.LoginAssertions;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import testdata.Credentials;
import testdata.UiTestDataFactory;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void setUpLogin() {
        loginPage = new LoginPage(driver);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpLoginPage() {
        loginPage.open();
    }

    @Test(
            groups = {"ui", "regression", "smoke"},
            description = "Verify login with valid credentials"
    )
    @Description("Verify login with valid credentials")
    public void loginWithValidCredentialsTest() {
        Credentials credentials = UiTestDataFactory.validCredentials();
        loginPage.login(credentials.username(), credentials.password());

        LoginAssertions.verifyLoginSuccessful(driver);
    }

    @Test(
            groups = {"ui", "regression"},
            dataProvider = "invalidLoginCredentials",
            description = "Verify login with invalid credentials"
    )
    @Description("Verify login with invalid credentials")
    public void loginWithInvalidCredentialsTest(String invalidUsername, String invalidPassword) {
        loginPage.login(invalidUsername, invalidPassword);

        LoginAssertions.verifyLoginFailed(loginPage.getErrorMessage());
    }

    @DataProvider(name = "invalidLoginCredentials")
    public Object[][] invalidLoginCredentials() {
        return UiTestDataFactory.invalidCredentials().stream()
                .map(credentials -> new Object[]{credentials.username(), credentials.password()})
                .toArray(Object[][]::new);
    }
}
