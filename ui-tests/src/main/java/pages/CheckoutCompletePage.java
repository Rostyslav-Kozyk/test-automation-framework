package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Models the Checkout complete page and its available interactions.
 */
public class CheckoutCompletePage extends BasePage {

    private final By completeHeader = By.cssSelector("h2[data-test='complete-header']");
    private final By completeText = By.cssSelector("div[data-test='complete-text']");

    /**
     * Creates a new {@code CheckoutCompletePage} instance.
     *
     * @param driver the driver value
     */
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Returns order confirmation header.
     *
     * @return the order confirmation header
     */
    public String getConfirmationHeader() {
        return getText(completeHeader, "Retrieve order confirmation header");
    }

    /**
     * Returns order confirmation text.
     *
     * @return the order confirmation text
     */
    public String getConfirmationText() {
        return getText(completeText, "Retrieve order confirmation text");
    }
}
