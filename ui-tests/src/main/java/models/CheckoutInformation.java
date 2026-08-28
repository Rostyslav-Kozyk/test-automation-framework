package models;

public final class CheckoutInformation {

    private String firstName;
    private String lastName;
    private String postalCode;

    private CheckoutInformation() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return builder()
                .firstName(firstName)
                .lastName(lastName)
                .postalCode(postalCode);
    }

    public String getFirstName() {
        return firstName;
    }

    public CheckoutInformation setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CheckoutInformation setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public CheckoutInformation setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    @Override
    public String toString() {
        return "CheckoutInformation{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    public static final class Builder {

        private String firstName = "";
        private String lastName = "";
        private String postalCode = "";

        private Builder() {
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public CheckoutInformation build() {
            var checkoutInformation = new CheckoutInformation();
            checkoutInformation.setFirstName(firstName);
            checkoutInformation.setLastName(lastName);
            checkoutInformation.setPostalCode(postalCode);
            return checkoutInformation;
        }
    }
}
