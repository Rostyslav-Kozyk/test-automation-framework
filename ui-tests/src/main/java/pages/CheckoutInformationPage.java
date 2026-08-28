package pages;

import models.CheckoutInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage extends BasePage {

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutInformationPage enterFirstName(String firstName) {
        fillInField(firstNameInput, firstName, String.format("Enter first name: %s", firstName));
        return this;
    }

    public CheckoutInformationPage enterLastName(String lastName) {
        fillInField(lastNameInput, lastName, String.format("Enter last name: %s", lastName));
        return this;
    }

    public CheckoutInformationPage enterPostalCode(String postalCode) {
        fillInField(postalCodeInput, postalCode, String.format("Enter postal code: %s", postalCode));
        return this;
    }

    public CheckoutInformationPage fillInformation(String firstName, String lastName, String postalCode) {
        return enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostalCode(postalCode);
    }

    public CheckoutInformationPage fillInformation(CheckoutInformation information) {
        return fillInformation(
                information.getFirstName(),
                information.getLastName(),
                information.getPostalCode()
        );
    }

    public CheckoutInformationPage submit() {
        submitForm(continueButton, "Continue checkout");
        return this;
    }

    public CheckoutOverviewPage continueCheckout() {
        submit();
        waitForUrlContaining("checkout-step-two.html", "Wait for checkout overview page");
        return new CheckoutOverviewPage(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage, "Retrieve checkout error message");
    }
}
