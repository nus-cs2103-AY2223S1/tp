package seedu.foodrem.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in FoodRem.
 * Guarantees: immutable; name is valid
 */
public class Tag {

    private final TagName tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        this.tagName = new TagName(tagName);
    }

    public String getName() {
        return this.tagName.toString();
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
