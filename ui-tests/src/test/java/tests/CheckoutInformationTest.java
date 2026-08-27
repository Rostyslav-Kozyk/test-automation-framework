package tests;

import assertions.CheckoutInformationAssertions;
import io.qameta.allure.Description;
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
                .fillInformation("Rostyslav", "Kozyk", "10115")
                .continueCheckout();

        CheckoutInformationAssertions.verifyProvidingCheckoutInformation(driver);
    }

    @Test(
            dataProvider = "invalidCheckoutInformation",
            description = "Verify required checkout information"
    )
    @Description("Verify checkout validation for missing customer information")
    public void requiredCheckoutInformationTest(
            String firstName,
            String lastName,
            String postalCode,
            String expectedError
    ) {
        informationPage
                .fillInformation(firstName, lastName, postalCode)
                .submit();

        CheckoutInformationAssertions.verifyCheckoutInformationError(informationPage.getErrorMessage(), expectedError);
    }

    @DataProvider(name = "invalidCheckoutInformation")
    public Object[][] invalidCheckoutInformation() {
        return new Object[][]{
                {"", "Kozyk", "10115", "Error: First Name is required"},
                {"Rostyslav", "", "10115", "Error: Last Name is required"},
                {"Rostyslav", "Kozyk", "", "Error: Postal Code is required"}
        };
    }
}
