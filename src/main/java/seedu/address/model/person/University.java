package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's university in the address book.
 * Guarantees: immutable; is always valid
 */
public class University {

    public static final String MESSAGE_CONSTRAINTS =
            "Universities should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String name;

    /**
     * Constructs an {@code University}.
     *
     * @param universityName A valid university name.
     */
    public University(String universityName) {
        requireNonNull(universityName);
        checkArgument(isValidUniversity(universityName), MESSAGE_CONSTRAINTS);
        name = universityName;
    }

    /**
     * Returns true if a given string is a valid university name.
     */
    public static boolean isValidUniversity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof University // instanceof handles nulls
                && name.equals(((University) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
