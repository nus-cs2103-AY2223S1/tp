package seedu.address.model.price;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;

/**
 * Represents a Price Range for a property that a buyer can accept.
 * Guarantees: immutable; is valid as declared.
 */
public class PriceRange {

    public static final String MESSAGE_CONSTRAINTS = "Price ranges must be specified in the form: "
            + "<low>-<high>, where <low> and <high> are non-negative numbers within the maximum range of a Double "
            + "and <low> must be smaller than <high>.";
    /*
     * The first part of the range must be digits, followed by a hyphen (whitespaces optional),
     * and then followed by more digits. A decimal / floating point value is also valid.
     * This helps prevent "123a-123b" from being considered a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-9]*\\.?[0-9]+\\s*-\\s*[0-9]*\\.?[0-9]+";

    public static final PriceRange RESET_PRICE_RANGE = new PriceRange();

    public final boolean isReset;
    private final Price low;
    private final Price high;

    /**
     * Constructs a {@code PriceRange}.
     * Guarantees: Immutable, is valid as declared in isValidPriceRange.
     */
    public PriceRange(String priceRange) {
        requireNonNull(priceRange);
        checkArgument(isValidPriceRange(priceRange), MESSAGE_CONSTRAINTS);

        String[] rangeValues = priceRange.split("-");
        this.low = new Price(rangeValues[0].trim());
        this.high = new Price(rangeValues[1].trim());
        this.isReset = false;
    }

    /**
     * Constructs a {@code PriceRange} that is going to be reset.
     */
    private PriceRange() {
        this.low = null;
        this.high = null;
        this.isReset = true;
    }

    /**
     * Returns true if a given string is a valid price range in format.
     * Left value of the price range must be smaller than the right value of the price range.
     * Both values must be non-negative and within the maximum range of a Double.
     * Accepts an empty string.
     */
    public static boolean isValidPriceRange(String test) {
        requireNonNull(test);

        if (test.trim().isEmpty()) {
            return true;
        }

        boolean isValid = test.matches(VALIDATION_REGEX);

        // to prevent out of bounds error below
        if (!isValid) {
            return false;
        }

        String[] rangeValues = test.split("-");

        double leftValue;
        double rightValue;

        try {
            leftValue = Double.parseDouble(rangeValues[0].trim());
            rightValue = Double.parseDouble(rangeValues[1].trim());
        } catch (NumberFormatException e) {
            return false;
        }

        boolean isLeftValueValid = leftValue >= 0;
        boolean isRightValueValid = rightValue >= 0;

        return isLeftValueValid
                && isRightValueValid
                && (leftValue - rightValue <= 0);
    }

    /**
     * Checks if a given Price is within the PriceRange.
     */
    public boolean isWithinPriceRange(Price p) {
        return (low.isSmallerThanOrEqual(p) && high.isGreaterThanOrEqual(p));
    }

    /**
     * Compare the upperbound of two PriceRange objects.
     * Upperbounds will always be compared in descending order.
     */
    public int compareUpperBound(PriceRange other) {
        return high.isSmallerThanOrEqual(other.high) ? 1 : -1;
    }

    /**
     * Compare the lowerbound of two PriceRange objects.
     * Lowerbounds will always be compared in ascending order.
     */
    public int compareLowerBound(PriceRange other) {
        return low.isGreaterThanOrEqual(other.low) ? 1 : -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // to avoid floats being saved to storage in scientific notation
        // and causing parsing bugs when being converted back into a float
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(0);
        sb.append(df.format(low.getNumericalValue()));
        sb.append(" - ");
        sb.append(df.format(high.getNumericalValue()));
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceRange // instanceof handles nulls
                && this.toString().equals(((PriceRange) other).toString())); // state check
    }

    public boolean isReset() {
        return isReset;
    }
}
