package seedu.nutrigoals.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the food list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be either breakfast, lunch or dinner";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;
    private enum Tags {
        BREAKFAST, LUNCH, DINNER
    }

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        // return test.matches(VALIDATION_REGEX);
        String trimmed = test.trim();
        boolean isValidTag = trimmed.equalsIgnoreCase("breakfast")
                || trimmed.equalsIgnoreCase("lunch")
                || trimmed.equalsIgnoreCase("dinner");
        return isValidTag;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

    public void edit(String editedName) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

}
