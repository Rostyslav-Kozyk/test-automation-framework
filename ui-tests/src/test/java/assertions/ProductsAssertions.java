package assertions;

import org.testng.Assert;
import pages.ProductsPage;

public class ProductsAssertions {

    public static void verifyCartItemsCount(ProductsPage productsPage, int expectedCount) {
        var actualCount = productsPage.getCartItemsCount();
        Assert.assertEquals(
                actualCount,
                expectedCount,
                "Unexpected number of items in cart"
        );
    }
}
