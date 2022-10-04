package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAssignment(String)}
 */
public class Assignment {

    public static final String MESSAGE_CONSTRAINTS = "Assignment can take any values, and it should not be blank";

    /*
     * The first character of the remark must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public String value;

    /**
     * Empty constructor to prevent error reading from jsonFile.
     */
    public Assignment() {};

    /**
     * Constructs an {@code Remark}.
     *
     * @param name A valid Remark.
     */
    public Assignment(String name) {
        requireNonNull(name);
        checkArgument(isValidAssignment(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAssignment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Get name of assignment
     */
    public String getAssignment() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && value.equals(((Assignment) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}