package tests;

import assertions.ProductsAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsTest extends BaseTest {

    private ProductsPage productsPage;

    private static final String VALID_PRODUCT_NAME = "Sauce Labs Backpack";

    @BeforeClass
    public void setUpProductsPage() {
        LoginPage loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);

        loginPage
                .open()
                .login(VALID_USERNAME, VALID_PASSWORD);
    }

    @Test
    public void addProductToCartTest() {
        productsPage.addProductToCart(VALID_PRODUCT_NAME);
        var expectedNumberOfCartItems = 1;

        ProductsAssertions.verifyCartItemsCount(productsPage, expectedNumberOfCartItems);
    }
}
