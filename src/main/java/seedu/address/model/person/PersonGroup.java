package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;

/**
 * Represents a Person's group in the address book.
 * Guarantees: immutable; is valid as declared in {}
 */
public class PersonGroup extends ArrayList<PersonGroup> {

    public static final String MESSAGE_CONSTRAINTS = "Group has to exist currently, and it should not be blank";

    /*
     * The first character of the group must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private String value;

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
        checkArgument(isValidGroup(group), MESSAGE_CONSTRAINTS);
        this.value = group;
    }

    /**
     * Returns true if a given string is a valid group.
     */
    public static boolean isValidGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Get name of Group
     */
    public String getGroupName() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonGroup // instanceof handles nulls
                && value.equals(((PersonGroup) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
