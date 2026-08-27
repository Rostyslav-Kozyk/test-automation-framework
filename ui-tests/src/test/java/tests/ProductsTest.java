package tests;

import assertions.ProductsAssertions;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsTest extends BaseTest {

    private static final String VALID_PRODUCT_NAME = "Sauce Labs Backpack";

    private ProductsPage productsPage;

    @BeforeClass(alwaysRun = true)
    public void setUpProductsPage() {
        productsPage = new LoginPage(driver)
                .open()
                .login(VALID_USERNAME, VALID_PASSWORD);
    }

    @Test(
            description = "Verify adding product to cart"
    )
    @Description("Verify adding product to cart")
    public void addProductToCartTest() {
        productsPage.addProductToCart(VALID_PRODUCT_NAME);
        var expectedNumberOfCartItems = 1;

        ProductsAssertions.verifyCartItemsCount(productsPage, expectedNumberOfCartItems);
    }
}
