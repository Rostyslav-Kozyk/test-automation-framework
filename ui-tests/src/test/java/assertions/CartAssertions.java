package assertions;

import pages.CartPage;

/**
 * Provides assertions for Cart page.
 */
public class CartAssertions extends BaseAssertions {

    /**
     * Verifies product on Cart page.
     *
     * @param cartPage      the cart page value
     * @param productName   the product name value
     * @param expectedPrice the expected price value
     */
    public static void verifyCartProduct(CartPage cartPage, String productName, String expectedPrice) {
        assertTrue(
                cartPage.containsProduct(productName),
                "Expected product is missing from cart",
                String.format("Verify cart contains product = %s", productName)
        );
        assertEquals(
                cartPage.getProductPrice(productName),
                expectedPrice,
                "Unexpected product price in cart",
                String.format("Verify cart product price = %s", expectedPrice)
        );
    }

    /**
     * Verifies Cart page is empty.
     *
     * @param cartPage the cart page value
     */
    public static void verifyEmptyCart(CartPage cartPage) {
        assertTrue(
                cartPage.isEmpty(),
                "Cart is not empty",
                "Verify cart is empty"
        );
    }
}
