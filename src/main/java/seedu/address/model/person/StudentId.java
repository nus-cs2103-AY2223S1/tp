package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's ID in the address book.
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS =
            "Student ID should start and end with a letter, and should only be 9 characters long.";
    public static final String VALIDATION_REGEX = "[a-zA-Z]\\d{7}[a-zA-Z]";
    public final String value;

    /**
     * Constructs a {@code StudentID}.
     *
     * @param id A valid id number.
     */
    public StudentId(String id) {
        requireNonNull(id);
        checkArgument(isValidStudentID(id), MESSAGE_CONSTRAINTS);
        value = id.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidStudentID(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && value.equals(((StudentId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
