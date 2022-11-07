package foodwhere.model.review;

import static foodwhere.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Review's rating in FoodWhere.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(String)}.
 */
public class Rating {

    public static final String MESSAGE_CONSTRAINTS =
            "Ratings can take any integer between 0 to 5, and it should not be blank";

    public static final Integer MAX_RATING = 5;

    public static final Integer MIN_RATING = 0;

    public final Integer value;

    /**
     * Constructs an {@code Rating}.
     *
     * @param rating A valid rating.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);

        value = Integer.valueOf(rating);
    }

    /**
     * Returns true if a given number is a valid rating.
     */
    public static boolean isValidRating(String test) {
        if (test == null) {
            throw new NullPointerException("Rating is null");
        }

        Integer value;
        try {
            value = Integer.valueOf(test);
        } catch (NumberFormatException exception) {
            return false;
        }
        return value >= MIN_RATING && value <= MAX_RATING;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rating // instanceof handles nulls
                && value.equals(((Rating) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
