package seedu.condonery.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's tag in Condonery.
 * Guarantees: immutable; is valid as declared in {@link #isValidTag(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS =
        "Tags can take any values, and it should not be blank";

    /*
     * Tag needs to contain at least one word.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tag A valid tag.
     */
    public Tag(String tag) {
        requireNonNull(tag);
        checkArgument(isValidTag(tag), MESSAGE_CONSTRAINTS);
        value = tag;
    }

    /**
     * Returns true if a given string is a valid tag.
     */
    public static boolean isValidTag(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && value.equals(((Tag) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
