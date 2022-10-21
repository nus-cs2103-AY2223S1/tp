package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public enum Tag {
    EAR("Ear"), NOSE("Nose"), THROAT("Throat");

    public static final String MESSAGE_CONSTRAINTS = "Tag names should be ear, nose or throat (not case sensitive).";
    private String tagName;

    Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public String toString() {
        return tagName;
    }

    /**
     * Checks whether the given input is in the restricted list of possible names.
     *
     * @param test The given input.
     * @return True if valid.
     */
    public static boolean isValidTagName(String test) {
        return test.equalsIgnoreCase("ear")
                || test.equalsIgnoreCase("nose")
                || test.equalsIgnoreCase("throat");
    }

    /**
     * Converts the given input to {@code Tag} if possible.
     *
     * @param tagName The given input.
     * @return The resulting {@code Tag}.
     */
    public static Tag convertToTag(String tagName) {
        requireNonNull(tagName);
        if (tagName.equalsIgnoreCase("ear")) {
            return Tag.EAR;
        } else if (tagName.equalsIgnoreCase("nose")) {
            return Tag.NOSE;
        } else if (tagName.equalsIgnoreCase("throat")) {
            return Tag.THROAT;
        } else {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }
}
