package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's price
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Price should only contain numbers and an optional exponent within the maximum range of a Double."
            + " For example: 123.45";
    private static final double EPSILON = 0.01d;
    private final String value;
    private final double numericalValue;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price number.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        value = price;
        numericalValue = Double.parseDouble(price);
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        double value;
        try {
            value = Double.parseDouble(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return value >= 0;
    }

    // TODO testing for these methods

    /**
     * Returns true if the stored numerical value is greater than or equal to a given {@code Price}.
     */
    public boolean isGreaterThanOrEqual(Price p) {
        double d = p.getNumericalValue();
        return numericalValue - d > EPSILON || numericalValue - d == 0;
    }

    /**
     * Returns true if the stored numerical value is smaller than or equal to a given {@code Price}.
     */
    public boolean isSmallerThanOrEqual(Price p) {
        double d = p.getNumericalValue();
        return d - numericalValue > EPSILON || numericalValue - d == 0;
    }

    public double getNumericalValue() {
        return this.numericalValue;
    }

    @Override
    public String toString() {
        return value;
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
