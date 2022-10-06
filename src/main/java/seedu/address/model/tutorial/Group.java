package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the group number of a tutorial in SETA
 */
public class Group {

    public static final String MESSAGE_CONSTRAINTS =
            "group should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the group must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    // I still don't understand what it means right now, and I will find out and change it later.

    public final String group;

    /**
     * Constructs a {@code group}.
     *
     * @param group A valid group.
     */
    public Group(String group) {
        requireNonNull(group);
        checkArgument(isValidGroup(group), MESSAGE_CONSTRAINTS);
        this.group = group;
    }

    /**
     * Returns true if a given string is a valid group.
     */
    public static boolean isValidGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return group;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Group // instanceof handles nulls
                && group.equals(((Group) other).group)); // state check
    }

    @Override
    public int hashCode() {
        return group.hashCode();
    }
}
