package assertions;

import pages.CartPage;

public class CartAssertions extends BaseAssertions {

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

    public static void verifyEmptyCart(CartPage cartPage) {
        assertTrue(
                cartPage.isEmpty(),
                "Cart is not empty",
                "Verify cart is empty"
        );
    }
}
