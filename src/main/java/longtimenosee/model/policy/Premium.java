package longtimenosee.model.policy;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.util.AppUtil.checkArgument;

/**
 * Represents what a policy's yearly premium is.
 * Guarantees: immutable; name is valid as declared in {@link #isValidPremium(String)}
 */
public class Premium {

    public static final String MESSAGE_CONSTRAINTS = "Premium should be any positive numeric value below "
            + "`1000000000` with or without 2 decimal places,"
            + " and it should not be blank";

    /*
     * The first character of the premium must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "([1-9]\\d{1,9})?\\d(\\.\\d\\d)?";

    public final String value;
    public final float numericValue;

    /**
     * Constructs an {@code Address}.
     *
     * @param premium A valid premium.
     */
    public Premium(String premium) {
        requireNonNull(premium);
        checkArgument(isValidPremium(premium), MESSAGE_CONSTRAINTS);
        value = premium;
        numericValue = Float.valueOf(premium);
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidPremium(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the value of the monthly premium.
     * @return The float value of the premium.
     */
    public float getNumericValue() {
        return numericValue;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Premium // instanceof handles nulls
                && value.equals(((Premium) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
