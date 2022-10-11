package seedu.address.model.pricerange;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.address.Address;

/**
 * Represents a Price Range for a property that a buyer can accept.
 * Guarantees: immutable; is valid as declared.
 */
public class PriceRange {

    public static final String MESSAGE_CONSTRAINTS = "Price ranges must be specified in the form: "
            + "<low> - <high>, where <low> and <high> are numbers (can be floating point decimals).";
    /*
     * The first part of the range must be digits, followed by a hyphen (whitespaces optional),
     * and then followed by more digits. A decimal / floating point value is also valid.
     * This helps prevent "123a-123b" from being considered a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-9]*\\.?[0-9]+\\s?-\\s?[0-9]*\\.?[0-9]+";

    public final double low;
    public final double high;

    /**
     * Constructs a {@code PriceRange}.
     * Guarantees: Immutable, is valid as declared in isValidPriceRange.
     */
    public PriceRange(String priceRange) {
        requireNonNull(priceRange);
        checkArgument(isValidPriceRange(priceRange), MESSAGE_CONSTRAINTS);

        String[] rangeValues = priceRange.split("-");
        this.low = Float.parseFloat(rangeValues[0].trim());
        this.high = Float.parseFloat(rangeValues[1].trim());
    }

    /**
     * Returns true if a given string is a valid price range in format.
     * Left value of the price range must be smaller than the right value of the price range.
     */
    public static boolean isValidPriceRange(String test) {

        boolean isValid = test.matches(VALIDATION_REGEX);
        
        // to prevent out of bounds error below
        if (!isValid) {
            return false;
        }

        String[] rangeValues = test.split("-");
        double leftValue = Float.parseFloat(rangeValues[0].trim());
        double rightValue = Float.parseFloat(rangeValues[1].trim());

        return isValid && (leftValue - rightValue <= 0);
    }

    /*
    Checks if a given float value is within the price range.
     */
    public boolean isWithinPriceRange(float f) {
        return (f - low >= 0 && high - f >= 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(low);
        sb.append(" - ");
        sb.append(high);
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceRange // instanceof handles nulls
                && this.toString().equals(((PriceRange) other).toString())); // state check
    }
}
