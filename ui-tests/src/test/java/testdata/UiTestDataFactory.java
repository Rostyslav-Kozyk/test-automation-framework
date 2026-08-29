package testdata;

import models.CheckoutInformation;

import java.util.List;

public final class UiTestDataFactory {

    private UiTestDataFactory() {
    }

    public static Credentials validCredentials() {
        return new Credentials("standard_user", "secret_sauce");
    }

    public static List<Credentials> invalidCredentials() {
        return List.of(
                new Credentials("invalid_user", "invalid_password"),
                new Credentials("invalid_user!@#$%^&*", "invalid_password!@#$%^&*")
        );
    }

    public static ProductData backpack() {
        return new ProductData(
                "Sauce Labs Backpack",
                "$29.99",
                "Item total: $29.99",
                "Tax: $2.40",
                "Total: $32.39"
        );
    }

    public static CheckoutInformation validCheckoutInformation() {
        return CheckoutInformation.builder()
                .firstName("Rostyslav")
                .lastName("Kozyk")
                .postalCode("10115")
                .build();
    }

    public static List<CheckoutValidationCase> invalidCheckoutInformation() {
        CheckoutInformation validInformation = validCheckoutInformation();

        return List.of(
                new CheckoutValidationCase(
                        validInformation.toBuilder().firstName("").build(),
                        "Error: First Name is required"
                ),
                new CheckoutValidationCase(
                        validInformation.toBuilder().lastName("").build(),
                        "Error: Last Name is required"
                ),
                new CheckoutValidationCase(
                        validInformation.toBuilder().postalCode("").build(),
                        "Error: Postal Code is required"
                )
        );
    }
}
