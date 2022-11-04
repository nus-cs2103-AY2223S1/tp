package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Class to store the quantity of goods transacted.
 */
public class Quantity {

    public static final String VALIDATION_REGEX = "\\d{1,}";

    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should only contain an unsigned positive integer.";

    public static final String MESSAGE_CONSTRAINTS_ZERO =
            "Quantity should not be 0.";
    public static final String MESSAGE_CONSTRAINTS_POSITIVE =
            "Quantity should not be be negative.";

    public static final String MESSAGE_CONSTRAINTS_LARGE =
            "Quantity should be less than 1 million.";

    public static final String MESSAGE_CONSTRAINTS_EMPTY = "Quantity should not be left empty.";

    public final String quantity;

    /**
     * Constructs a {@code Quantity}.
     * @param quantity A valid quantity.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);

        checkArgument(isValidQuantityEmpty(quantity), MESSAGE_CONSTRAINTS_EMPTY);
        checkArgument(isValidDouble(quantity), MESSAGE_CONSTRAINTS);
        checkArgument(isPositiveQuantity(quantity), MESSAGE_CONSTRAINTS_POSITIVE);
        checkArgument(isValidQuantityRegex(quantity), MESSAGE_CONSTRAINTS);
        checkArgument(isSmallQuantity(quantity), MESSAGE_CONSTRAINTS_LARGE);
        checkArgument(isValidQuantityNonZero(quantity), MESSAGE_CONSTRAINTS_ZERO);
        this.quantity = Integer.toString(Integer.parseInt(quantity));
    }

    /**
     * Returns true if a given string is a valid quantity.
     */

    public static boolean isValidQuantity(String test) {
        requireNonNull(test);
        try {
            parseQuantityArguments(test);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }



    /**
     * Returns true if a given string is a valid double.
     */
    private static boolean isValidDouble(String test) {
        requireNonNull(test);
        boolean isDouble = true;
        try {
            Double.parseDouble(test);
        } catch (NumberFormatException e) {
            isDouble = false;
        }

        return isDouble && !test.contains("+");
    }

    /**
     * Returns true of a given string fits the regex.
     */
    private static boolean isValidQuantityRegex(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true of a given string is not empty.
     */
    private static boolean isValidQuantityEmpty(String test) {
        requireNonNull(test);
        return !test.isEmpty();
    }


    /**
     * Returns true of a given string is non-zero.
     */
    private static boolean isValidQuantityNonZero(String test) {
        requireNonNull(test);
        return Integer.parseInt(test) != 0;
    }

    /**
     * Returns true if a give string is a valid positive quantity.
     */
    private static boolean isPositiveQuantity(String test) {
        requireNonNull(test);
        return !test.contains("-");
    }

    /**
     * Returns true if a give quantity is a less than 1 million.
     */
    private static boolean isSmallQuantity(String test) {
        requireNonNull(test);
        try {
            return Integer.parseInt(test) < 1000000;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parsers the quantity and checks if it is a valid quantity.
     */
    public static void parseQuantityArguments(String trimmedQuantity) throws ParseException {

        requireNonNull(trimmedQuantity);

        if (!Quantity.isValidQuantityEmpty(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS_EMPTY);
        }
        if (!Quantity.isValidDouble(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        if (!Quantity.isPositiveQuantity(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS_POSITIVE);
        }
        if (!Quantity.isValidQuantityRegex(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        if (!Quantity.isSmallQuantity(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS_LARGE);
        }
        if (!Quantity.isValidQuantityNonZero(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS_ZERO);
        }
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
