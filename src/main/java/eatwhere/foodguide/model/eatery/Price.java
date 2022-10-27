package eatwhere.foodguide.model.eatery;

import static java.util.Objects.requireNonNull;

import eatwhere.foodguide.commons.util.AppUtil;

/**
 * Represents an Eatery's phone number in the food guide.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Price should only contain 1-3 '$' characters, with 1 ($) indicating cheapest "
                    + "and 3 ($$$) indicating most expensive";
    public static final String VALIDATION_REGEX = "[$]{1,3}";
    public final String value;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        AppUtil.checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        value = price;
    }

    /**
     * Constructs an empty {@code Price}.
     */
    public Price() {
        value = "";
    }

    /**
     * Returns true if a given string is a valid price indicator.
     */
    public static boolean isValidPrice(String test) {
        return test == null || test.equals("") || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value == null ? "" : value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && value.equals(((Price) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
