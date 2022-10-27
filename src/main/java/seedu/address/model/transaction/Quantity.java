package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Class to store the quantity of goods transacted.
 */
public class Quantity {

    public static final String VALIDATION_REGEX = "\\d{1,}";

    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should only contain numbers and no decimal point.";

    public static final String MESSAGE_CONSTRAINTS_ZERO =
            "Quantity should not be 0.";
    public static final String MESSAGE_CONSTRAINTS_POSITIVE =
            "Quantity should be not be negative.";

    public static final String MESSAGE_CONSTRAINTS_EMPTY = "Quantity should not be left empty.";

    public final String quantity;

    /**
     * Constructs a {@code Quantity}.
     * @param quantity A valid quantity.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);

        checkArgument(isValidQuantity_empty(quantity), MESSAGE_CONSTRAINTS_EMPTY);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        checkArgument(isPositiveQuantity(quantity), MESSAGE_CONSTRAINTS_POSITIVE);
        checkArgument(isValidQuantity_regex(quantity), MESSAGE_CONSTRAINTS);
        checkArgument(isValidQuantity_nonZero(quantity), MESSAGE_CONSTRAINTS_ZERO);
        this.quantity = Integer.toString(Integer.parseInt(quantity));
    }

    /**
     * Returns true if a given string is a valid quantity.
     */

    public static boolean isValidQuantity(String test) {
        requireNonNull(test);
        boolean isInteger = true;
        int num = 0;

        try {
            num = Integer.parseInt(test);
        } catch (NumberFormatException e) {
            isInteger = false;
        }

        return isInteger;
    }

    /**
     * Returns true of a given string fits the regex.
     */
    public static boolean isValidQuantity_regex(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true of a given string is not empty.
     */
    public static boolean isValidQuantity_empty(String test) {
        requireNonNull(test);
        return !test.isEmpty();
    }


    /**
     * Returns true of a given string is non-zero.
     */
    public static boolean isValidQuantity_nonZero(String test) {
        requireNonNull(test);
        return Integer.parseInt(test) != 0;
    }

    /**
     * Returns true if a give string is a valid positive quantity.
     */
    public static boolean isPositiveQuantity(String test) {
        requireNonNull(test);
        return !test.contains("-");
    }

    @Override
    public String toString() {
        return quantity;
    }

    public double value() {
        return Integer.parseInt(quantity);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && quantity.equals(((Quantity) other).quantity)); // state check
    }
}
