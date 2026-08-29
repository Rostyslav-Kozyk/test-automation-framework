package testdata;

import models.CheckoutInformation;

public record CheckoutValidationCase(
        CheckoutInformation information,
        String expectedError
) {
}
