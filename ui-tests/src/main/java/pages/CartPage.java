package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Models the cart page and its available interactions.
 */
public class CartPage extends BasePage {

    private static final String PRODUCT = "//div[@data-test='inventory-item' and .//div[@data-test='inventory-item-name' and text()='%s']]";

    private final By cartList = By.cssSelector("div[data-test='cart-list']");
    private final By cartProducts = By.cssSelector("div[data-test='inventory-item']");
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");

    /**
     * Creates a new {@code CartPage} instance.
     *
     * @param driver the driver value
     */
    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Checks whether page opened.
     *
     * @return the operation result
     */
    public boolean isPageOpened() {
        return isDisplayed(cartList, "Verify cart page is opened");
    }

    /**
     * Checks whether the page contains product.
     *
     * @param productName the product name value
     * @return the operation result
     */
    public boolean containsProduct(String productName) {
        By product = By.xpath(String.format(PRODUCT, productName));
        return isDisplayed(product, String.format("Verify cart contains \"%s\"", productName));
    }

    /**
     * Returns product price.
     *
     * @param productName the product name value
     * @return the operation result
     */
    public String getProductPrice(String productName) {
        By productPrice = By.xpath(String.format(PRODUCT, productName) + "//div[@data-test='inventory-item-price']");
        return getText(productPrice, String.format("Retrieve price for \"%s\"", productName));
    }

    /**
     * Removes product.
     *
     * @param productName the product name value
     * @return the operation result
     */
    public CartPage removeProduct(String productName) {
        By removeButton = By.xpath(String.format(PRODUCT, productName) + "//button[text()='Remove']");
        clickElementWithJavaScript(removeButton, String.format("Remove %s from cart", productName));
        return this;
    }

    /**
     * Checks whether empty.
     *
     * @return the operation result
     */
    public boolean isEmpty() {
        return isAbsent(cartProducts, "Verify cart has no products");
    }

    /**
     * Executes the checkout operation.
     *
     * @return the operation result
     */
    public CheckoutInformationPage checkout() {
        clickElementWithJavaScript(checkoutButton, "Start checkout");
        waitForUrlContaining("checkout-step-one.html", "Wait for checkout information page");
        return new CheckoutInformationPage(driver);
    }
}
