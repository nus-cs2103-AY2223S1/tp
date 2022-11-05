package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Link's name in the TruthTable.
 * Guarantees: immutable; is valid as declared in {@link #isValidLinkName(String)}
 */
public class LinkName {
    public static final String MESSAGE_CONSTRAINTS =
            "Link names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String linkName;

    /**
     * Constructs a {@code LinkName}.
     *
     * @param name A valid name.
     */
    public LinkName(String name) {
        requireNonNull(name);
        checkArgument(isValidLinkName(name), MESSAGE_CONSTRAINTS);
        linkName = name;
    }

    /**
     * Returns true if a given string is a valid link name.
     */
    public static boolean isValidLinkName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return linkName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkName // instanceof handles nulls
                && linkName.equals(((LinkName) other).linkName)); // state check
    }

    @Override
    public int hashCode() {
        return linkName.hashCode();
    }
}
