package seedu.foodrem.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in FoodRem.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {
    public static final String EXCEED_MAX_CHARS_MESSAGE_CONSTRAINTS = "Tags names have a max length of 30 characters";

    private static final int MAX_CHAR_LIMIT = 30;

    private final TagName tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), EXCEED_MAX_CHARS_MESSAGE_CONSTRAINTS);
        this.tagName = new TagName(tagName);
    }

    public String getName() {
        return this.tagName.toString();
    }

    /**
     * Returns {@code true} if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        requireNonNull(test);
        return test.length() <= MAX_CHAR_LIMIT;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof Tag
            && tagName.equals(((Tag) other).tagName));
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return tagName.toString();
    }
}
