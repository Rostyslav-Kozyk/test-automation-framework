package tests;

import assertions.CheckoutInformationAssertions;
import io.qameta.allure.Description;
import models.CheckoutInformation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckoutInformationPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CheckoutInformationTest extends BaseTest {

    private static final String PRODUCT_NAME = "Sauce Labs Backpack";

    private ProductsPage productsPage;
    private CheckoutInformationPage informationPage;

    @BeforeClass(alwaysRun = true)
    public void setUpProduct() {
        productsPage = new LoginPage(driver)
                .open()
                .login(VALID_USERNAME, VALID_PASSWORD)
                .addProductToCart(PRODUCT_NAME);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpCheckout() {
        informationPage = productsPage
                .open()
                .openCart()
                .checkout();
    }

    @Test(description = "Verify checkout information")
    @Description("Verify checkout information")
    public void checkoutInformationTest() {
        informationPage
                .fillInformation(validCheckoutInformation())
                .continueCheckout();

        CheckoutInformationAssertions.verifyProvidingCheckoutInformation(driver);
    }

    @Test(
            dataProvider = "invalidCheckoutInformation",
            description = "Verify required checkout information"
    )
    @Description("Verify checkout validation for missing customer information")
    public void requiredCheckoutInformationTest(
            CheckoutInformation checkoutInformation,
            String expectedError
    ) {
        informationPage
                .fillInformation(checkoutInformation)
                .submit();

        CheckoutInformationAssertions.verifyCheckoutInformationError(informationPage.getErrorMessage(), expectedError);
    }

    @DataProvider(name = "invalidCheckoutInformation")
    public Object[][] invalidCheckoutInformation() {
        CheckoutInformation validInformation = validCheckoutInformation();

        return new Object[][]{
                {
                        validInformation.toBuilder().firstName("").build(),
                        "Error: First Name is required"
                },
                {
                        validInformation.toBuilder().lastName("").build(),
                        "Error: Last Name is required"
                },
                {
                        validInformation.toBuilder().postalCode("").build(),
                        "Error: Postal Code is required"
                }
        };
    }

    private CheckoutInformation validCheckoutInformation() {
        return CheckoutInformation.builder()
                .firstName("Rostyslav")
                .lastName("Kozyk")
                .postalCode("10115")
                .build();
    }
}
