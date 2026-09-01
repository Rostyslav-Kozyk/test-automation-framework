package testdata;

import models.CheckoutInformation;

/**
 * Represents checkout validation case used by UI scenarios.
 */
public record CheckoutValidationCase(
        CheckoutInformation information,
        String expectedError
) {
}
