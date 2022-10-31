package foodwhere.model.commons;

import static foodwhere.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;


/**
 * Represents a Tag in FoodWhere.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTag(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tag;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tag A valid tag.
     */
    public Tag(String tag) {
        requireNonNull(tag);
        checkArgument(isValidTag(tag), MESSAGE_CONSTRAINTS);
        this.tag = tag.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid tag.
     */
    public static boolean isValidTag(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tag.equals(((Tag) other).tag)); // state check
    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tag + ']';
    }

}
