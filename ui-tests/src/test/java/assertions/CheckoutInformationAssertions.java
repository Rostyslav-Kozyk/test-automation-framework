package assertions;

import org.openqa.selenium.WebDriver;

/**
 * Provides assertions for Checkout information page.
 */
public class CheckoutInformationAssertions extends BaseAssertions {

    /**
     * Verifies provided checkout information.
     *
     * @param driver the driver value
     */
    public static void verifyProvidingCheckoutInformation(WebDriver driver) {
        String currentUrl = driver.getCurrentUrl();

        assertTrue(
                currentUrl != null && currentUrl.contains("checkout-step-two"),
                "Expected to be on Checkout Overview page after providing checkout information, but was: " + currentUrl,
                "Verify checkout information provided successfully"
        );
    }

    /**
     * Verifies checkout information error.
     *
     * @param actualError   the actual error value
     * @param expectedError the expected error value
     */
    public static void verifyCheckoutInformationError(String actualError, String expectedError) {
        assertEquals(actualError, expectedError, "Unexpected checkout information error", "Verify checkout information error");
    }
}
