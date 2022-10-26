package seedu.address.testutil;

import seedu.address.model.product.Product;

/**
 * A utility class to help with building Product objects.
 */
public class ProductBuilder {
    public static final String DEFAULT_NAME = "MyInsurance";
    private String productName;

    /**
     * Creates a {@code ProductBuilder} with the default details.
     */
    public ProductBuilder() {
        productName = DEFAULT_NAME;
    }

    /**
     * Initializes the ProductBuilder with the data of {@code productToCopy}.
     */
    public ProductBuilder(Product productToCopy) {
        productName = productToCopy.getProductName();
    }

    /**
     * Sets the {@code productName} of the {@code Product} that we are building.
     */
    public ProductBuilder withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Product build() {
        return new Product(productName);
    }
}
