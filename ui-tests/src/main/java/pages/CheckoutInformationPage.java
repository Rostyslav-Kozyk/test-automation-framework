package pages;

import models.CheckoutInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Models the Checkout information page and its available interactions.
 */
public class CheckoutInformationPage extends BasePage {

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    /**
     * Creates a new {@code CheckoutInformationPage} instance.
     *
     * @param driver the driver value
     */
    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters first name.
     *
     * @param firstName the first name value
     * @return the class
     */
    public CheckoutInformationPage enterFirstName(String firstName) {
        fillInField(firstNameInput, firstName, String.format("Enter first name: %s", firstName));
        return this;
    }

    /**
     * Enters last name.
     *
     * @param lastName the last name value
     * @return the class
     */
    public CheckoutInformationPage enterLastName(String lastName) {
        fillInField(lastNameInput, lastName, String.format("Enter last name: %s", lastName));
        return this;
    }

    /**
     * Enters postal code.
     *
     * @param postalCode the postal code value
     * @return the class
     */
    public CheckoutInformationPage enterPostalCode(String postalCode) {
        fillInField(postalCodeInput, postalCode, String.format("Enter postal code: %s", postalCode));
        return this;
    }

    /**
     * Fills information.
     *
     * @param firstName  the first name value
     * @param lastName   the last name value
     * @param postalCode the postal code value
     * @return the class
     */
    public CheckoutInformationPage fillInformation(String firstName, String lastName, String postalCode) {
        return enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostalCode(postalCode);
    }

    /**
     * Fills information.
     *
     * @param information the information value
     * @return the class
     */
    public CheckoutInformationPage fillInformation(CheckoutInformation information) {
        return fillInformation(
                information.getFirstName(),
                information.getLastName(),
                information.getPostalCode()
        );
    }

    /**
     * Submits the provided information on page.
     *
     * @return the class
     */
    public CheckoutInformationPage submit() {
        submitForm(continueButton, "Continue checkout");
        return this;
    }

    /**
     * Proceeds checkout to next step.
     *
     * @return the Checkout overview page class
     */
    public CheckoutOverviewPage continueCheckout() {
        submit();
        waitForUrlContaining("checkout-step-two.html", "Wait for checkout overview page");
        return new CheckoutOverviewPage(driver);
    }

    /**
     * Returns error message on page.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return getText(errorMessage, "Retrieve checkout error message");
    }
}
