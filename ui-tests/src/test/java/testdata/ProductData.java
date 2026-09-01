package testdata;

/**
 * Represents product test data.
 */
public record ProductData(
        String name,
        String price,
        String itemTotal,
        String tax,
        String orderTotal
) {
}
