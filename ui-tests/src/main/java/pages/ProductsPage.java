package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Models the products page and its available interactions.
 */
public class ProductsPage extends BasePage {

    private static final String PRODUCT_PAGE_URL = BASE_URL + "/inventory.html";

    private final By inventoryContainer = By.id("inventory_container");
    private final By shoppingCart = By.cssSelector("a[data-test='shopping-cart-link']");
    private final By shoppingCartBadge = By.cssSelector("span[data-test='shopping-cart-badge']");

    private static final String ADD_TO_CART_BUTTON = "//div[@data-test='inventory-item' and .//div[text()='%s']]//button[text()='Add to cart']";

    /**
     * Creates a new {@code ProductsPage} instance.
     *
     * @param driver the driver value
     */
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens the Product page.
     *
     * @return the class
     */
    public ProductsPage open() {
        openUrl(PRODUCT_PAGE_URL, "Open Product page");
        return this;
    }

    /**
     * Checks whether page opened.
     *
     * @return the boolean value
     */
    public boolean isPageOpened() {
        return isDisplayed(inventoryContainer, "Verify Product page is opened");
    }

    /**
     * Executes the add product to cart operation.
     *
     * @param productName the product name value
     * @return the class
     */
    public ProductsPage addProductToCart(String productName) {
        String addToCartButton = String.format(ADD_TO_CART_BUTTON, productName);
        clickElementWithJavaScript(By.xpath(addToCartButton), String.format("Add \"%s\" product to cart", productName));
        return this;
    }

    /**
     * Returns cart items count.
     *
     * @return the cart items count
     */
    public int getCartItemsCount() {
        if (driver.findElements(shoppingCartBadge).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(
                getText(shoppingCartBadge, "Retrieve cart items count")
        );
    }

    /**
     * Opens cart.
     *
     * @return the Cart page class
     */
    public CartPage openCart() {
        clickElementWithJavaScript(shoppingCart, "Open shopping cart");
        waitForUrlContaining("cart.html", "Wait for shopping cart page");
        CartPage cartPage = new CartPage(driver);
        cartPage.isPageOpened();
        return cartPage;
    }
}
