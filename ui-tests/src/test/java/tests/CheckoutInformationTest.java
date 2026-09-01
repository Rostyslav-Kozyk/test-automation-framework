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
import testdata.CheckoutValidationCase;
import testdata.Credentials;
import testdata.ProductData;
import testdata.UiTestDataFactory;

public class CheckoutInformationTest extends BaseTest {

    private final ProductData product = UiTestDataFactory.backpack();
    private ProductsPage productsPage;
    private CheckoutInformationPage informationPage;

    @BeforeClass(alwaysRun = true)
    public void setUpProduct() {
        Credentials credentials = UiTestDataFactory.validCredentials();
        productsPage = new LoginPage(getDriver())
                .open()
                .login(credentials.username(), credentials.password())
                .addProductToCart(product.name());
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpCheckout() {
        informationPage = productsPage
                .open()
                .openCart()
                .checkout();
    }

    @Test(groups = {"ui", "regression"}, description = "Verify checkout information")
    @Description("Verify checkout information")
    public void checkoutInformationTest() {
        informationPage
                .fillInformation(UiTestDataFactory.validCheckoutInformation())
                .continueCheckout();

        CheckoutInformationAssertions.verifyProvidingCheckoutInformation(getDriver());
    }

    @Test(
            groups = {"ui", "regression"},
            dataProvider = "invalidCheckoutInformation",
            description = "Verify checkout validation for missing customer information"
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
        return UiTestDataFactory.invalidCheckoutInformation().stream()
                .map(this::toDataProviderRow)
                .toArray(Object[][]::new);
    }

    private Object[] toDataProviderRow(CheckoutValidationCase validationCase) {
        return new Object[]{validationCase.information(), validationCase.expectedError()};
    }
}
