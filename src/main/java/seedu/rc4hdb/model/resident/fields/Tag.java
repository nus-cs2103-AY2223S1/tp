package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag extends ResidentField {

    public static final String IDENTIFIER = "Tags";

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";

    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public static final Tag NIL_TAG = new Tag("nil");

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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && value.equals(((Tag) other).value)); // state check
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + value + ']';
    }

}
