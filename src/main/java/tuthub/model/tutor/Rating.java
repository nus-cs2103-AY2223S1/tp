package tuthub.model.tutor;

import static java.util.Objects.requireNonNull;
import static tuthub.commons.util.AppUtil.checkArgument;

/**
 * Represents the rating that a Tutor has.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(String)}
 */
public class Rating {

    public static final String MESSAGE_CONSTRAINTS = "Rating should only contain decimal values between 0 to 5"
            + "with no spaces in between, and it should not be blank.\n"
            + "Decimals are optional, but there are a maximum of 2 decimal places.";

    public static final Double MAX_RATING = 5.0;
    public static final Double MIN_RATING = 0.0;

    /**
     * Covers positive double values, where decimals are optional.
     * Maximum of 2 decimals.
     * The first value should be between 0 (MIN_RATING) and 5 (MAX_RATING).
     */
    public static final String VALIDATION_REGEX = "^[" + MIN_RATING + "-" + MAX_RATING + "](\\.[0-9]([0-9]?))?$";

    public final String value;

    /**
     * Constructs a {@code Rating}
     *
     * @param rating Number of teaching nominations Tutor has.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        value = rating;
    }

    /**
     * Returns true if a given string is a valid Rating.
     */
    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX) && isInRange(test);
    }

    /**
     * Returns true if a given string is between minimum and maximum rating.
     */
    public static boolean isInRange(String test) {
        return Double.parseDouble(test) <= MAX_RATING && Double.parseDouble(test) >= MIN_RATING;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rating // instanceof handles null
                && value.equals(((Rating) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
