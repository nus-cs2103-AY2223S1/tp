package seedu.address.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Product in MyInsuRec.
 */
public class Product {
    public static final String MESSAGE_CONSTRAINTS = "Product names should be alphanumeric";
    /*
     * The first character of the product must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String productName;

    /**
     * Constructs a {@code Product}.
     *
     * @param productName A valid product type.
     */
    public Product(String productName) {
        requireNonNull(productName);
        checkArgument(isValidProductName(productName), MESSAGE_CONSTRAINTS);
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    /**
     * Returns true if a given string is a valid product name.
     */
    public static boolean isValidProductName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Product // instanceof handles nulls
                && productName.equals(((Product) other).productName)); // state check
    }

    @Override
    public int hashCode() {
        return productName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + productName + ']';
    }
}
