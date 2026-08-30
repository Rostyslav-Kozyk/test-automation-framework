package assertions;

import org.openqa.selenium.WebDriver;

public class CheckoutInformationAssertions extends BaseAssertions {

    public static void verifyProvidingCheckoutInformation(WebDriver driver) {
        String currentUrl = driver.getCurrentUrl();

        assertTrue(
                currentUrl != null && currentUrl.contains("checkout-step-two"),
                "Expected to be on Checkout Overview page after providing checkout information, but was: " + currentUrl,
                "Verify checkout information provided successfully"
        );
    }

    public static void verifyCheckoutInformationError(String actualError, String expectedError) {
        assertEquals(actualError, expectedError, "Unexpected checkout information error", "Verify checkout information error");
    }
}
