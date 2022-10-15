package foodwhere.model.review;

import static foodwhere.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Review's rating in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(Integer)}
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
    public Rating(Integer rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        value = rating;
    }

    /**
     * Returns true if a given number is a valid rating.
     */
    public static boolean isValidRating(Integer test) {
        return test >= MIN_RATING && test <= MAX_RATING;
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
