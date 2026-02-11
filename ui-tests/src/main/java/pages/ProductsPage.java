package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By inventoryContainer = By.id("inventory_container");
    private final By shoppingCart = By.cssSelector("a[data-test='shopping-cart-link']");
    private final By shoppingCartBadge = By.cssSelector("span[data-test='shopping-cart-badge']");

    private static final String ADD_TO_CART_BUTTON = "//div[@data-test='inventory-item' and .//div[text()='%s']]//button";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return isDisplayed(inventoryContainer, "Verify Product page is opened");
    }

    public ProductsPage addProductToCart(String productName) {
        String addToCartButton = String.format(ADD_TO_CART_BUTTON, productName);
        clickElement(By.xpath(addToCartButton), String.format("Add \"%s\" product to cart", productName));
        return this;
    }

    public int getCartItemsCount() {
        if (driver.findElements(shoppingCartBadge).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(
                getText(shoppingCartBadge, "Retrieve cart items count")
        );
    }

    public void openCart() {
        clickElement(shoppingCart, "Open shopping cart");
    }
}
