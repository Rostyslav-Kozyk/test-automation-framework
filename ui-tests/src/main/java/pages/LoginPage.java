package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        openUrl(BASE_URL, "Open Login page");
        return this;
    }

    public LoginPage enterUsername(String username) {
        fillInField(usernameInput, username, String.format("Enter username: %s", username));
        return this;
    }

    public LoginPage enterPassword(String password) {
        fillInField(passwordInput, password, String.format("Enter password: %s", password));
        return this;
    }

    public ProductsPage clickLogin() {
        clickElement(loginButton, "Click Login button");
        return new ProductsPage(this.driver);
    }

    public ProductsPage login(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    public String getErrorMessage() {
        return getText(errorMessage, "Retrieve error message");
    }
}
