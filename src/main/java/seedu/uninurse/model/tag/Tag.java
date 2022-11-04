package seedu.uninurse.model.tag;

import static seedu.uninurse.commons.util.AppUtil.checkArgument;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Patient's tag.
 */
public class Tag {
    public static final String MESSAGE_CONSTRAINTS = "Tag names can take any values, and it should not be blank";

    /*
     * The first character of the tag must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String tagName;

    /**
     * Constructs a tag.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireAllNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Checks if a given string is a valid tag.
     *
     * @param test The given string to be tested.
     * @return True if a given string is a valid tag.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return tagName;
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return tagName;
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
}
