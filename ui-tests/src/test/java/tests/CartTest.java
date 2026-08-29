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
import testdata.Credentials;
import testdata.ProductData;
import testdata.UiTestDataFactory;

public class CartTest extends BaseTest {

    private final ProductData product = UiTestDataFactory.backpack();
    private ProductsPage productsPage;
    private CartPage cartPage;

    @BeforeClass(alwaysRun = true)
    public void setUpProductsPage() {
        Credentials credentials = UiTestDataFactory.validCredentials();
        productsPage = new LoginPage(getDriver())
                .open()
                .login(credentials.username(), credentials.password());
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpCartPage() {
        productsPage
                .open()
                .addProductToCart(product.name());
    }

    @Test(groups = {"ui", "regression"}, description = "Verify cart product")
    @Description("Verify cart product")
    public void cartProductTest() {
        cartPage = productsPage.openCart();

        CartAssertions.verifyCartProduct(cartPage, product.name(), product.price());
    }

    @Test(groups = {"ui", "regression"}, description = "Verify product removal")
    @Description("Verify product removal")
    public void cartProductRemovalTest() {
        cartPage = productsPage.openCart();
        cartPage.removeProduct(product.name());

        CartAssertions.verifyEmptyCart(cartPage);
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUpCartPage() {
        productsPage.open();
        if (productsPage.getCartItemsCount() > 0) {
            productsPage.openCart().removeProduct(product.name());
        }
    }
}
