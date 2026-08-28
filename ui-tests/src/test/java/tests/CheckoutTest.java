package tests;

import assertions.CheckoutAssertions;
import io.qameta.allure.Description;
import models.CheckoutInformation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CheckoutCompletePage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CheckoutTest extends BaseTest {

    private static final String PRODUCT_NAME = "Sauce Labs Backpack";
    private static final String PRODUCT_PRICE = "$29.99";
    private static final String ITEM_TOTAL = "Item total: $29.99";
    private static final String TAX = "Tax: $2.40";
    private static final String ORDER_TOTAL = "Total: $32.39";

    private ProductsPage productsPage;

    @BeforeClass(alwaysRun = true)
    public void setUpProductsPage() {
        productsPage = new LoginPage(driver)
                .open()
                .login(VALID_USERNAME, VALID_PASSWORD);
    }

    @Test(groups = {"ui", "regression", "smoke"}, description = "Verify successful checkout")
    @Description("Verify successful end-to-end checkout")
    public void successfulCheckoutTest() {
        CheckoutInformation checkoutInformation = CheckoutInformation.builder()
                .firstName("Rostyslav")
                .lastName("Kozyk")
                .postalCode("10115")
                .build();

        CheckoutOverviewPage overviewPage = productsPage
                .addProductToCart(PRODUCT_NAME)
                .openCart()
                .checkout()
                .fillInformation(checkoutInformation)
                .continueCheckout();

        CheckoutAssertions.verifyOrderOverview(
                overviewPage,
                PRODUCT_NAME,
                PRODUCT_PRICE,
                ITEM_TOTAL,
                TAX,
                ORDER_TOTAL
        );

        CheckoutCompletePage completePage = overviewPage.finish();
        CheckoutAssertions.verifyOrderCompleted(completePage);
    }
}
