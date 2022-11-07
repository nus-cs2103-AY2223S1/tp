package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Professor/Teaching Assistant's rating in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(String)}
 */
public class Rating {

    public static final String MESSAGE_CONSTRAINTS =
            "Rating can only take integer values from 0 to 5.";

    public static final String EMPTY_RATING = "";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-5]";

    public final String value;

    /**
     * Constructs a {@code Rating}.
     *
     * @param rating A valid rating.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        value = rating;
    }

    /**
     * @param rating A valid rating.
     * @param isPresent Whether prefix was present in user input.
     */
    public Rating(String rating, boolean isPresent) {
        requireNonNull(rating);
        if (isPresent) {
            checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
            value = rating;
        } else {
            value = EMPTY_RATING;
        }
    }

    /**
     * Returns true if a given string is a valid rating.
     */
    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
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
