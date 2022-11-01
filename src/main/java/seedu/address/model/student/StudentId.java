package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a student ID in ModQuik.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class StudentId {
    public static final String MESSAGE_CONSTRAINTS =
            "ID should follow the format AXXXXXXXY, where X is a number, and Y is an alphabet";

    /*
     * The first character of the id must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[aA]\\d{7}[a-zA-Z]";

    public final String id;

    /**
     * Constructs a {@code ID}.
     *
     * @param id A valid id.
     */
    public StudentId(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = id.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && id.equals(((StudentId) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
