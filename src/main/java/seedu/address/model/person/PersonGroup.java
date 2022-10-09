package seedu.address.model.person;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's group in the address book.
 * Guarantees: immutable; is valid as declared in {}
 */
public class PersonGroup {

    public static final String MESSAGE_CONSTRAINTS = "Group can take any values, and it should not be blank";

    /*
     * The first character of the group must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private String group;

    /**
     * Empty constructor to prevent error reading from jsonFile.
     */
    public PersonGroup() {};

    /**
     * Constructs an {@code Group}.
     *
     * @param group A valid Group.
     */
    public PersonGroup(String group) {
        requireNonNull(group);
        //checkArgument(isValidGroup(group, MESSAGE_CONSTRAINTS));
        group = group;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Get name of Group
     */
    public String getGroup() {
        return group;
    }

    public boolean isEmpty() {
        return isNull(group);
    }
    @Override
    public String toString() {
        return group;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonGroup // instanceof handles nulls
                && group.equals(((PersonGroup)other).group)); // state check
    }

    @Override
    public int hashCode() {
        return group.hashCode();
    }

}
