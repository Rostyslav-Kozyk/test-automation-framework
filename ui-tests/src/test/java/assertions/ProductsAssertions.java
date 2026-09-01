package assertions;

import pages.ProductsPage;

/**
 * Provides assertions for products behavior.
 */
public class ProductsAssertions extends BaseAssertions {

    /**
     * Verifies cart items count.
     *
     * @param productsPage  the products page value
     * @param expectedCount the expected count value
     */
    public static void verifyCartItemsCount(ProductsPage productsPage, int expectedCount) {
        var actualCount = productsPage.getCartItemsCount();

        assertEquals(
                actualCount,
                expectedCount,
                "Unexpected number of items in cart",
                "Verify number of items in cart = " + expectedCount
        );
    }
}
