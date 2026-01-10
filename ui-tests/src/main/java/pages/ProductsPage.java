package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    private final WebDriver driver;

    private final By inventoryContainer = By.id("inventory_container");
    private final By shoppingCart = By.cssSelector("a[data-test='shopping-cart-link']");
    private final By shoppingCartBadge = By.cssSelector("span[data-test='shopping-cart-badge']");

    private static final String ADD_TO_CART_BUTTON = "//div[@data-test='inventory-item' and .//div[text()='%s']]//button";

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageOpened() {
        return driver.findElement(inventoryContainer).isDisplayed();
    }

    public ProductsPage addProductToCart(String productName) {
        String addToCartButton = String.format(ADD_TO_CART_BUTTON, productName);
        driver.findElement(By.xpath(addToCartButton)).click();
        return this;
    }

    public int getCartItemsCount() {
        if (driver.findElements(shoppingCartBadge).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(
                driver.findElement(shoppingCartBadge).getText()
        );
    }

    public void openCart() {
        driver.findElement(shoppingCart).click();
    }
}
