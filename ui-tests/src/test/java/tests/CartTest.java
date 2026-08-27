package tests;

import assertions.CartAssertions;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CartTest extends BaseTest {

    private static final String PRODUCT_NAME = "Sauce Labs Backpack";
    private static final String PRODUCT_PRICE = "$29.99";

    private ProductsPage productsPage;
    private CartPage cartPage;

    @BeforeClass(alwaysRun = true)
    public void setUpProductsPage() {
        productsPage = new LoginPage(driver)
                .open()
                .login(VALID_USERNAME, VALID_PASSWORD);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpCartPage() {
        productsPage
                .open()
                .addProductToCart(PRODUCT_NAME);
    }

    @Test(description = "Verify cart product")
    @Description("Verify cart product")
    public void cartProductTest() {
        cartPage = productsPage.openCart();

        CartAssertions.verifyCartProduct(cartPage, PRODUCT_NAME, PRODUCT_PRICE);
    }

    @Test(description = "Verify product removal")
    @Description("Verify product removal")
    public void cartProductRemovalTest() {
        cartPage = productsPage.openCart();
        cartPage.removeProduct(PRODUCT_NAME);

        CartAssertions.verifyEmptyCart(cartPage);
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUpCartPage() {
        productsPage.open();
        if (productsPage.getCartItemsCount() > 0) {
            productsPage.openCart().removeProduct(PRODUCT_NAME);
        }
    }
}
