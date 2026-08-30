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
        String actualPrice = overviewPage.getProductPrice(productName);
        String actualItemTotal = overviewPage.getItemTotal();
        String actualTax = overviewPage.getTax();
        String actualTotal = overviewPage.getTotal();

        assertSoftly("Verify order overview amounts", softly -> {
            softly.assertEquals(actualPrice, expectedPrice, assertionMessage("Unexpected product price", expectedPrice, actualPrice));
            softly.assertEquals(actualItemTotal, expectedItemTotal, assertionMessage("Unexpected item total", expectedItemTotal, actualItemTotal));
            softly.assertEquals(actualTax, expectedTax, assertionMessage("Unexpected tax", expectedTax, actualTax));
            softly.assertEquals(actualTotal, expectedTotal, assertionMessage("Unexpected order total", expectedTotal, actualTotal));
        });
    }

    public static void verifyOrderCompleted(CheckoutCompletePage completePage) {
        String actualHeader = completePage.getConfirmationHeader();
        String actualText = completePage.getConfirmationText();

        assertSoftly("Verify order confirmation", softly -> {
            softly.assertEquals(actualHeader, CONFIRMATION_HEADER, assertionMessage("Unexpected confirmation header", CONFIRMATION_HEADER, actualHeader));
            softly.assertEquals(actualText, CONFIRMATION_TEXT, assertionMessage("Unexpected confirmation text", CONFIRMATION_TEXT, actualText));
        });
    }
}
