package models;

/**
 * Represents checkout information used by UI scenarios.
 */
public final class CheckoutInformation {

    private String firstName;
    private String lastName;
    private String postalCode;

    /**
     * Creates an empty {@code CheckoutInformation} instance for controlled construction.
     */
    private CheckoutInformation() {
    }

    /**
     * Creates a new {@code CheckoutInformation} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Converts to builder.
     *
     * @return the checkout information builder
     */
    public Builder toBuilder() {
        return builder()
                .firstName(firstName)
                .lastName(lastName)
                .postalCode(postalCode);
    }

    /**
     * Returns first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name value
     * @return the class
     */
    public CheckoutInformation setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Returns last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name value
     * @return the class
     */
    public CheckoutInformation setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Returns postal code.
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets postal code.
     *
     * @param postalCode the postal code value
     * @return the class
     */
    public CheckoutInformation setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * Converts checkout information to string.
     *
     * @return the checkout information
     */
    @Override
    public String toString() {
        return "CheckoutInformation{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    /**
     * Provides builder functionality.
     */
    public static final class Builder {

        private String firstName = "";
        private String lastName = "";
        private String postalCode = "";

        /**
         * Creates an empty {@code CheckoutInformation} instance for controlled construction.
         */
        private Builder() {
        }

        /**
         * Sets the first name.
         *
         * @param firstName the first name value
         * @return the builder
         */
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Sets the last name.
         *
         * @param lastName the last name value
         * @return the builder
         */
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Sets the postal code.
         *
         * @param postalCode the postal code value
         * @return the builder
         */
        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        /**
         * Executes the build operation.
         *
         * @return the checkout information class
         */
        public CheckoutInformation build() {
            var checkoutInformation = new CheckoutInformation();
            checkoutInformation.setFirstName(firstName);
            checkoutInformation.setLastName(lastName);
            checkoutInformation.setPostalCode(postalCode);
            return checkoutInformation;
        }
    }
}
