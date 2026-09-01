package testdata;

import models.CheckoutInformation;

import java.util.List;

/**
 * Creates configured UI test data instances.
 */
public final class UiTestDataFactory {

    /**
     * Prevents instantiation of this utility class.
     */
    private UiTestDataFactory() {
    }

    /**
     * Provides the valid login credentials.
     *
     * @return the login credentials
     */
    public static Credentials validCredentials() {
        return new Credentials("standard_user", "secret_sauce");
    }

    /**
     * Provides the invalid login credentials.
     *
     * @return the invalid login credentials
     */
    public static List<Credentials> invalidCredentials() {
        return List.of(
                new Credentials("invalid_user", "invalid_password"),
                new Credentials("invalid_user!@#$%^&*", "invalid_password!@#$%^&*")
        );
    }

    /**
     * Provides the backpack product values.
     *
     * @return the backpack product values
     */
    public static ProductData backpack() {
        return new ProductData(
                "Sauce Labs Backpack",
                "$29.99",
                "Item total: $29.99",
                "Tax: $2.40",
                "Total: $32.39"
        );
    }

    /**
     * Provides the valid checkout information.
     *
     * @return the valid checkout information
     */
    public static CheckoutInformation validCheckoutInformation() {
        return CheckoutInformation.builder()
                .firstName("Rostyslav")
                .lastName("Kozyk")
                .postalCode("10115")
                .build();
    }

    /**
     * Provides the invalid checkout information.
     *
     * @return the invalid checkout information
     */
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
