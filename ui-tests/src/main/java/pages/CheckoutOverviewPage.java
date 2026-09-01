package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Models the Checkout overview page and its available interactions.
 */
public class CheckoutOverviewPage extends BasePage {

    private static final String PRODUCT = "//div[@data-test='inventory-item' and .//div[@data-test='inventory-item-name' and text()='%s']]";

    private final By itemTotal = By.cssSelector("div[data-test='subtotal-label']");
    private final By tax = By.cssSelector("div[data-test='tax-label']");
    private final By total = By.cssSelector("div[data-test='total-label']");
    private final By finishButton = By.cssSelector("button[data-test='finish']");

    /**
     * Creates a new {@code CheckoutOverviewPage} instance.
     *
     * @param driver the driver value
     */
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Checks whether the page contains specified product.
     *
     * @param productName the product name value
     * @return the boolean value
     */
    public boolean containsProduct(String productName) {
        By product = By.xpath(String.format(PRODUCT, productName));
        return isDisplayed(product, String.format("Verify overview contains \"%s\"", productName));
    }

    /**
     * Returns product price.
     *
     * @param productName the product name value
     * @return the product price
     */
    public String getProductPrice(String productName) {
        By productPrice = By.xpath(String.format(PRODUCT, productName) + "//div[@data-test='inventory-item-price']");
        return getText(productPrice, String.format("Retrieve overview price for \"%s\"", productName));
    }

    /**
     * Returns item total.
     *
     * @return the item total
     */
    public String getItemTotal() {
        return getText(itemTotal, "Retrieve item total");
    }

    /**
     * Returns tax.
     *
     * @return the tax
     */
    public String getTax() {
        return getText(tax, "Retrieve tax");
    }

    /**
     * Returns total.
     *
     * @return the total
     */
    public String getTotal() {
        return getText(total, "Retrieve order total");
    }

    /**
     * Finishes the checkout overview.
     *
     * @return the Checkout complete page class
     */
    public CheckoutCompletePage finish() {
        clickElementWithJavaScript(finishButton, "Finish checkout");
        waitForUrlContaining("checkout-complete.html", "Wait for checkout confirmation page");
        return new CheckoutCompletePage(driver);
    }
}
