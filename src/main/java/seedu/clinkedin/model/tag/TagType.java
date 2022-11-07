package seedu.clinkedin.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.person.UniqueTagTypeMap;

/**
 * Represents a TagType in the clinkedin book.
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
     * @param tagType A valid tag type.
     * @param p A valid prefix.
     */
    public TagType(String tagType, Prefix p) throws IllegalArgumentException {
        requireNonNull(tagType);
        checkArgument(isValidTagType(tagType), MESSAGE_CONSTRAINTS);
        this.tagType = tagType;
        this.p = p;
    }

    /**
     * Constructs a {@code TagType}.
     * @param tagType A valid tag type.
     */
    public TagType(String tagType) {
        requireNonNull(tagType);
        checkArgument(isValidTagType(tagType), MESSAGE_CONSTRAINTS);
        this.tagType = tagType;
        this.p = UniqueTagTypeMap.getPrefixFromTagType(tagType);
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

    public Prefix getPrefix() {
        return this.p;
    }

    /**
     * Returns a copy of the tag type.
     */
    public TagType copy() {
        return new TagType(tagType, p.copy());
    }
}
