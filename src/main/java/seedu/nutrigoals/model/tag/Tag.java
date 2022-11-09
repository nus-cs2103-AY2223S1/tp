package seedu.nutrigoals.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the food list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be either breakfast, lunch or dinner";
    public static final String BREAKFAST_REGEX = "^\\s*(?i)breakfast\\s*$";
    public static final String LUNCH_REGEX = "^\\s*(?i)lunch\\s*$";
    public static final String DINNER_REGEX = "^\\s*(?i)dinner\\s*$";

    /**
     * Represents the different meal types a tag name can have.
     */
    public enum TagName {
        BREAKFAST, LUNCH, DINNER
    }

    public final TagName tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = TagName.valueOf(tagName.toUpperCase());
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        boolean isValidTag = test.matches(BREAKFAST_REGEX)
                || test.matches(LUNCH_REGEX)
                || test.matches(DINNER_REGEX);

        return isValidTag;
    }

    /**
     * Returns the name of the tag in lower case.
     * @return The tag name in lower case.
     */
    public String getTagName() {
        return tagName.toString().toLowerCase();
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
        return getTagName();
    }

    /**
     * Test if the tag name can be edited
     * Since a new tag is created if the user wants to edit a tag, the tag name should
     * be final and the user should not be able to modify it
     * @param editedName New tag name
     * @throws UnsupportedOperationException as the tag name is not modifiable
     */
    public void edit(String editedName) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

}
