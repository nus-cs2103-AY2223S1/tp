package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.PriceFormatter.formatPrice;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Class to store the price of the goods transacted.
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS_EMPTY =
            "Price should not be left empty.";
    public static final String MESSAGE_CONSTRAINTS_POSITIVE =
            "Price should not be negative.";

    public static final String MESSAGE_CONSTRAINTS_LARGE =
            "Price should be less than 1 million.";

    public static final String MESSAGE_CONSTRAINTS_GENERAL =
            "Price should be an unsigned positive number and contain only 1 decimal point.";


    public final String price;


    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPriceEmpty(price), MESSAGE_CONSTRAINTS_EMPTY);
        checkArgument(isValidDouble(price), MESSAGE_CONSTRAINTS_GENERAL);
        checkArgument(isPositivePrice(price), MESSAGE_CONSTRAINTS_POSITIVE);
        checkArgument(isSmallPrice(price), MESSAGE_CONSTRAINTS_LARGE);
        this.price = price;
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        requireNonNull(test);
        try {
            parsePriceArguments(test);
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
     * Returns true if a give string is a valid positive price.
     */
    private static boolean isPositivePrice(String test) {
        return !test.contains("-");
    }

    /**
     * Returns true of a given string is not empty.
     */
    private static boolean isValidPriceEmpty(String test) {
        requireNonNull(test);
        return !test.isEmpty();
    }

    /**
     * Returns true if a give price is a less than 1 million.
     */
    private static boolean isSmallPrice(String test) {
        requireNonNull(test);
        return Double.parseDouble(test) < 1000000;
    }

    /**
     * Parsers the price and checks if it is a valid price.
     */
    public static void parsePriceArguments(String trimmedPrice) throws ParseException {
        requireNonNull(trimmedPrice);

        if ((!Price.isValidPriceEmpty(trimmedPrice))) {
            throw new ParseException((Price.MESSAGE_CONSTRAINTS_EMPTY));
        }
        if (!Price.isValidDouble(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS_GENERAL);
        }
        if (!Price.isPositivePrice(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS_POSITIVE);
        }
        if (!Price.isSmallPrice(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS_LARGE);
        }
    }

    @Override
    public String toString() {
        return formatPrice(this.value());
    }

    public double value() {
        return Double.parseDouble(price);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && price.equals(((Price) other).price)); // state check
    }

}
