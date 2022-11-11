package seedu.address.model.price;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's price
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Price should only contain numbers and an optional exponent within the maximum range of a Double,"
                    + " and it should not be blank."
                    + " For example: 123.45";
    private static final String VALIDATION_REGEX = "^[0-9]*\\.*[0-9]+$";
    private static final double EPSILON = 0.01d;
    public final String value;
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
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        // check for possible overflow
        try {
            Double.parseDouble(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

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
