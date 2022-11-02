package hobbylist.model.tag;

import static java.util.Objects.requireNonNull;

import hobbylist.commons.util.AppUtil;

/**
 * Represents a Tag in HobbyList.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    // Limit the length of the tags names to 15 to prevent text overrun in UI
    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric with underscores "
            + "and must not be more than 15 characters.";
    public static final String VALIDATION_REGEX = "[A-Za-z0-9_]+";
    public static final String TAG_NAME_TOO_LONG = "Tags names must not be more than 15 characters.";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        AppUtil.checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    public boolean match(String keyword) {
        return tagName.equals(keyword);
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

}
