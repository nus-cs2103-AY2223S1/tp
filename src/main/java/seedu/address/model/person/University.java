package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's university in the address book.
 * Guarantees: immutable; is always valid
 */
public class University {

    public static final int LENGTH_LIMIT = 50;

    public static final String MESSAGE_LENGTH_LIMIT_EXCEEDED = "After trimming leading and trailing whitespaces, and "
            + "replacing multiple spaces with a single space, "
            + "Universities can only be of length max " + LENGTH_LIMIT;

    public static final String MESSAGE_CONSTRAINTS =
            "Universities should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the university name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs an {@code University}.
     *
     * @param universityName A valid university name.
     */
    public University(String universityName) {
        requireNonNull(universityName);
        checkArgument(isValidUniversity(universityName), MESSAGE_CONSTRAINTS);
        value = universityName;
    }

    /**
     * Returns true if the length of a given string is within the length limit
     */
    public static boolean isWithinLengthLimit(String test) {
        return test.length() <= LENGTH_LIMIT;
    }

    /**
     * Returns true if a given string is a valid university name.
     */
    public static boolean isValidUniversity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof University // instanceof handles nulls
                && value.equals(((University) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
