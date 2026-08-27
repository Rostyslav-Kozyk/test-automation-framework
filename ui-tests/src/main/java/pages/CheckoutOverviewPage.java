package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private static final String PRODUCT = "//div[@data-test='inventory-item' and .//div[@data-test='inventory-item-name' and text()='%s']]";

    private final By itemTotal = By.cssSelector("div[data-test='subtotal-label']");
    private final By tax = By.cssSelector("div[data-test='tax-label']");
    private final By total = By.cssSelector("div[data-test='total-label']");
    private final By finishButton = By.cssSelector("button[data-test='finish']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean containsProduct(String productName) {
        By product = By.xpath(String.format(PRODUCT, productName));
        return isDisplayed(product, String.format("Verify overview contains \"%s\"", productName));
    }

    public String getProductPrice(String productName) {
        By productPrice = By.xpath(String.format(PRODUCT, productName) + "//div[@data-test='inventory-item-price']");
        return getText(productPrice, String.format("Retrieve overview price for \"%s\"", productName));
    }

    public String getItemTotal() {
        return getText(itemTotal, "Retrieve item total");
    }

    public String getTax() {
        return getText(tax, "Retrieve tax");
    }

    public String getTotal() {
        return getText(total, "Retrieve order total");
    }

    public CheckoutCompletePage finish() {
        clickElementWithJavaScript(finishButton, "Finish checkout");
        waitForUrlContaining("checkout-complete.html", "Wait for checkout confirmation page");
        return new CheckoutCompletePage(driver);
    }
}
