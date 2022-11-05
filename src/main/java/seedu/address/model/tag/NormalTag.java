package seedu.address.model.tag;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidNormalTagName(String)}
 */
public class NormalTag extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric and up to 50 characters";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}]{0,50}$";
    public static final String MESSAGE_MAX_TAGS = "Max of only 5 tags can be added at any time.";


    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public NormalTag(String tagName) {
        super(tagName);
        checkArgument(isValidNormalTagName(tagName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidNormalTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NormalTag // instanceof handles nulls
                && tagName.equals(((NormalTag) other).tagName)); // state check
    }

}
