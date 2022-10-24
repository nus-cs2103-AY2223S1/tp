package seedu.clinkedin.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's rating in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRatingStr(String)}
 */
public class Rating {

    public static final String MESSAGE_CONSTRAINTS = "Rating should only be an integer between 1 to 10 inclusive.";
    public static final String VALIDATION_REGEX = "/^([0-9]|10)$/";
    public final int value;

    /**
     * Constructs a {@code Rating}.
     *
     * @param ratingStr A valid rating number.
     */
    public Rating(String ratingStr) {
        requireNonNull(ratingStr);
        checkArgument(isValidRatingStr(ratingStr), MESSAGE_CONSTRAINTS);
        int rating = Integer.parseInt(ratingStr);
        value = rating;
    }

    /**
     * Returns true if a given string can be parsed into an integer and is between 1 and 10 inclusive.
     */
    public static boolean isValidRatingStr(String test) {
        return test.equals("0") || test.equals("1") || test.equals("2") || test.equals("3") || test.equals("4")
                || test.equals("5") || test.equals("6") || test.equals("7") || test.equals("8") || test.equals("9")
                || test.equals("10");
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rating // instanceof handles nulls
                && value == (((Rating) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return Integer.toString(value).hashCode();
    }

}
