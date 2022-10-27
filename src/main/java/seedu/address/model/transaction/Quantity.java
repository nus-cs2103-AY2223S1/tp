package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Class to store the quantity of goods transacted.
 */
public class Quantity {
    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should only contain numbers, and it should be at least 1 digits long";
    public static final String MESSAGE_CONSTRAINTS_POSITIVE =
            "Quantity should be not be negative.";

    public final String quantity;

    /**
     * Constructs a {@code Quantity}.
     * @param quantity A valid quantity.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        checkArgument(isPositiveQuantity(quantity), MESSAGE_CONSTRAINTS_POSITIVE);
        this.quantity = quantity;
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

        return isInteger && num != 0 && !test.equals("null");
    }

    /**
     * Returns true if a give string is a valid positive quantity.
     */
    public static boolean isPositiveQuantity(String test) {
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
