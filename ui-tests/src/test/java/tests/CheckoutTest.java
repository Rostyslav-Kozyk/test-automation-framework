package tests;

import assertions.CheckoutAssertions;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CheckoutCompletePage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;
import pages.ProductsPage;
import testdata.Credentials;
import testdata.ProductData;
import testdata.UiTestDataFactory;

public class CheckoutTest extends BaseTest {

    private final ProductData product = UiTestDataFactory.backpack();
    private ProductsPage productsPage;

    @BeforeClass(alwaysRun = true)
    public void setUpProductsPage() {
        Credentials credentials = UiTestDataFactory.validCredentials();
        productsPage = new LoginPage(getDriver())
                .open()
                .login(credentials.username(), credentials.password());
    }

    @Test(groups = {"ui", "regression", "smoke"}, description = "Verify successful checkout")
    @Description("Verify successful end-to-end checkout")
    public void successfulCheckoutTest() {
        CheckoutOverviewPage overviewPage = productsPage
                .addProductToCart(product.name())
                .openCart()
                .checkout()
                .fillInformation(UiTestDataFactory.validCheckoutInformation())
                .continueCheckout();

        CheckoutAssertions.verifyOrderOverview(
                overviewPage,
                product.name(),
                product.price(),
                product.itemTotal(),
                product.tax(),
                product.orderTotal()
        );

        CheckoutCompletePage completePage = overviewPage.finish();
        CheckoutAssertions.verifyOrderCompleted(completePage);
    }
}
