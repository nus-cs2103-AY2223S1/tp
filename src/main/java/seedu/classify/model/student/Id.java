package seedu.classify.model.student;

import static java.util.Objects.requireNonNull;

import seedu.classify.commons.util.AppUtil;

/**
 * Represents a Student Id in the record.
 * Id is the last 4 characters of the NRIC (e.g. 123A).
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS = "ID should only contain 3 digits followed by 1 character";
    public static final String VALIDATION_REGEX = "\\d{3}[a-zA-Z]";
    public final String value;

    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid id.
     */
    public Id(String id) {
        requireNonNull(id);
        AppUtil.checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = id.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidId(String test) {
        return test.length() == 4 && test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Id // instanceof handles nulls
                && value.equals(((Id) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
