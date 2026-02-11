package assertions;

import pages.ProductsPage;

public class ProductsAssertions extends BaseAssertions {

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
