package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's group in the address book.
 * Guarantees: immutable; is valid as declared in {}
 */
public class Group {

    public static final String MESSAGE_CONSTRAINTS = "Group can take any values, and it should not be blank";

    /*
     * The first character of the group must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private String value;

    /**
     * Empty constructor to prevent error reading from jsonFile.
     */
    public Group() {};

    /**
     * Constructs an {@code Group}.
     *
     * @param group A valid Group.
     */
    public Group(String group) {
        requireNonNull(group);
        //checkArgument(isValidAssignment(group, MESSAGE_CONSTRAINTS));
        value = group;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAssignment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Get name of Group
     */
    public String getGroup() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Group // instanceof handles nulls
                && value.equals(((Group) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
