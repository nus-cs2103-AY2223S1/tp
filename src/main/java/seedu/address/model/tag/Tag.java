package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.Collection;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String[] BANNED_TAG_NAMES = new String[]{"create", "c", "remove", "r"};
    public static final String MESSAGE_BANNED_TAG_NAME = "The following tag name is not allowed: \n%1$s";
    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric and "
            + "cannot contain only numerals";
    public static final String VALIDATION_REGEX_ALPHA_NUMERIC = "\\p{Alnum}+";
    public static final String VALIDATION_REGEX_ONLY_NUMERALS = "\\d+";

    public final String tagName;

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
        return !test.matches(VALIDATION_REGEX_ONLY_NUMERALS) && test.matches(VALIDATION_REGEX_ALPHA_NUMERIC);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isBannedTagName(String test) {
        return Arrays.asList(BANNED_TAG_NAMES).stream().anyMatch(test::equals);
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

    /**
     * Format tags for pretty printing.
     * @returns "tag1, tag2, tag3"
     */
    public static String toString(Collection<Tag> tags) {
        requireNonNull(tags);
        if (tags.isEmpty()) {
            return "";
        }

        String result = tags.stream().reduce(
                "", (tagString, tag) -> tagString + ", " + tag.tagName, (x, y) -> x + y);
        // Remove unneeded preceding comma and space
        result = result.substring(2);
        return result;
    }
}
