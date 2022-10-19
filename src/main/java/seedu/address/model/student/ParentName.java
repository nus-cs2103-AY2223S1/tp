package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Parent's name in the record.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class ParentName {

    public static final String MESSAGE_CONSTRAINTS =
            "Parent name should only contain alphanumeric characters and spaces. It can be blank.";


    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9 ]*$";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public ParentName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParentName // instanceof handles nulls
                && fullName.equals(((ParentName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
