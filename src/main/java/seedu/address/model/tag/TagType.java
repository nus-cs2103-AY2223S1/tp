package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.address.logic.parser.Prefix;

/**
 * Represents a TagType in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagType(String)}
 */
public class TagType {

    public static final String MESSAGE_CONSTRAINTS =
            "Tag types should contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String tagType;
    public final Prefix p;
    /**
     * Constructs a {@code TagType}.
     *
     * @param tagType A valid tag type.
     */
    public TagType(String tagType, Prefix p) {
        requireNonNull(tagType);
        checkArgument(isValidTagType(tagType), MESSAGE_CONSTRAINTS);
        this.tagType = tagType;
        this.p = p;
    }

    /**
     * Returns true if a given string is a valid tag type.
     */
    public static boolean isValidTagType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || ((other instanceof TagType // instanceof handles nulls
                && this.tagType.equals(((TagType) other).tagType)
                && p.equals(((TagType) other).p))); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagType, p);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return tagType;
    }
    public String getTagTypeName() {
        return this.tagType;
    }
}
