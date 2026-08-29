package tests;

import assertions.ProductsAssertions;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import testdata.Credentials;
import testdata.ProductData;
import testdata.UiTestDataFactory;

public class ProductsTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeClass(alwaysRun = true)
    public void setUpProductsPage() {
        Credentials credentials = UiTestDataFactory.validCredentials();
        productsPage = new LoginPage(driver)
                .open()
                .login(credentials.username(), credentials.password());
    }

    @Test(
            groups = {"ui", "regression"},
            description = "Verify adding product to cart"
    )
    @Description("Verify adding product to cart")
    public void addProductToCartTest() {
        ProductData product = UiTestDataFactory.backpack();
        productsPage.addProductToCart(product.name());
        var expectedNumberOfCartItems = 1;

        ProductsAssertions.verifyCartItemsCount(productsPage, expectedNumberOfCartItems);
    }
}
