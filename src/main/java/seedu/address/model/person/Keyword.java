package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a keyword for use in the command "suggest".
 */
public class Keyword {

    public static final String MESSAGE_CONSTRAINTS = "Any string can be accepted";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = ".*";

    public final String value;

    /**
     * Constructs an {@code Keyword}.
     *
     * @param keyword A valid keyword.
     */
    public Keyword(String keyword) {
        requireNonNull(keyword);
        checkArgument(isValidKeyword(keyword), MESSAGE_CONSTRAINTS);
        value = keyword;
    }

    /**
     * Returns true if a given string is a valid keyword.
     *
     * @param test A string.
     * @return A boolean value.
     */
    public static boolean isValidKeyword(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Keyword // instanceof handles nulls
                && value.equals(((Keyword) other).value)); // state check
    }

    /**
     * Returns hashcode for purpose of the  {@link #equals(Object)} method.
     * @return The hashcode of the String representation of the object.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
