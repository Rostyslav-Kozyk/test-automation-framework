package assertions;

import pages.CheckoutCompletePage;
import pages.CheckoutOverviewPage;

public class CheckoutAssertions extends BaseAssertions {

    private static final String CONFIRMATION_HEADER = "Thank you for your order!";
    private static final String CONFIRMATION_TEXT = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

    public static void verifyOrderOverview(
            CheckoutOverviewPage overviewPage,
            String productName,
            String expectedPrice,
            String expectedItemTotal,
            String expectedTax,
            String expectedTotal
    ) {
        assertTrue(
                overviewPage.containsProduct(productName),
                "Expected product is missing from checkout overview",
                String.format("Verify overview contains product = %s", productName)
        );
        assertEquals(overviewPage.getProductPrice(productName), expectedPrice, "Unexpected product price", "Verify overview product price");
        assertEquals(overviewPage.getItemTotal(), expectedItemTotal, "Unexpected item total", "Verify item total");
        assertEquals(overviewPage.getTax(), expectedTax, "Unexpected tax", "Verify tax");
        assertEquals(overviewPage.getTotal(), expectedTotal, "Unexpected order total", "Verify order total");
    }

    public static void verifyOrderCompleted(CheckoutCompletePage completePage) {
        assertEquals(completePage.getConfirmationHeader(), CONFIRMATION_HEADER, "Unexpected confirmation header", "Verify confirmation header");
        assertEquals(completePage.getConfirmationText(), CONFIRMATION_TEXT, "Unexpected confirmation text", "Verify confirmation text");
    }
}
