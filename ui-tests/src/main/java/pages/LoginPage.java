package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Models the Login page and its available interactions.
 */
public class LoginPage extends BasePage {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    /**
     * Creates a new {@code LoginPage} instance.
     *
     * @param driver the driver value
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens the Login page.
     *
     * @return the class
     */
    public LoginPage open() {
        openUrl(BASE_URL, "Open Login page");
        return this;
    }

    /**
     * Enters username.
     *
     * @param username the username value
     * @return the class
     */
    public LoginPage enterUsername(String username) {
        fillInField(usernameInput, username, String.format("Enter username: %s", username));
        return this;
    }

    /**
     * Enters password.
     *
     * @param password the password value
     * @return the class
     */
    public LoginPage enterPassword(String password) {
        fillInField(passwordInput, password, String.format("Enter password: %s", password));
        return this;
    }

    /**
     * Clicks login button.
     *
     * @return the Products page class
     */
    public ProductsPage clickLogin() {
        clickElement(loginButton, "Click Login button");
        return new ProductsPage(this.driver);
    }

    /**
     * Executes the login operation.
     *
     * @param username the username value
     * @param password the password value
     * @return the Products page class
     */
    public ProductsPage login(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    /**
     * Returns error message on Login page.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return getText(errorMessage, "Retrieve error message");
    }
}
