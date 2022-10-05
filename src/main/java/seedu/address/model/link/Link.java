package seedu.address.model.link;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Link in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidLinkName(String)}
 */
public class Link {

    public static final String MESSAGE_CONSTRAINTS = "links should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String linkName;

    /**
     * Constructs a {@code Link}.
     *
     * @param linkName A valid link name.
     */
    public Link(String linkName) {
        requireNonNull(linkName);
        checkArgument(isValidLinkName(linkName), MESSAGE_CONSTRAINTS);
        this.linkName = linkName;
    }

    /**
     * Returns true if a given string is a valid link name.
     */
    public static boolean isValidLinkName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && linkName.equals(((Link) other).linkName)); // state check
    }

    @Override
    public int hashCode() {
        return linkName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + linkName + ']';
    }

}

