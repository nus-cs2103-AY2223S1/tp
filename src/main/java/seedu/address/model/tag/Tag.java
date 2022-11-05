package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tag names should be alphanumeric and one word long.";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;
    private int count;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
        this.count = 1;
    }

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     * @param tagCount Number of tags of this name retrieved from storage.
     */
    public Tag(String tagName, int tagCount) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
        this.count = tagCount;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getName() {
        return this.tagName;
    }

    /**
     * Adds to counter every time this tag is added to a new contact or task.
     */
    public void addToCount() {
        this.count++;
    }

    /**
     * Decreases count every time this tag is removed from a contact or task.
     */
    public void removeFromCount() {
        this.count--;
    }

    /**
     * Returns true if other tag has the same name as this tag.
     *
     * @param otherTag Tag instance to be checked.
     */
    public boolean isSameTag(Tag otherTag) {
        if (otherTag == this) {
            return true;
        }

        return otherTag != null
                && otherTag.getName().equals(getName());
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

    public int getCount() {
        return count;
    }
}
