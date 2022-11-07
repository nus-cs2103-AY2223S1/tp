package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.model.StringField;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag extends StringField {

    public static final String IDENTIFIER = "Tags";

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";

    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name string.
     */
    public Tag(String tagName) {
        super(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}
