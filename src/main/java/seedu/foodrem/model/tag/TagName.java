package seedu.foodrem.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class TagName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters, spaces, and the punctuations within the "
                    + "list of allowed punctuations. "
                    + "Names must start with an alphanumeric character.";

    /*
     * Regex to check for valid punctuation
     */
    public static final String ALLOWED_PUNCTUATION_REGEX =
            "[\\?\\.\\'\\\"\\[\\]\\{\\}\\+\\^\\$\\*\\(\\)\\-<>,:;~@!#%&_=`]";

    /*
     * Only alphanumeric characters, whitespaces and punctuation within the ALLOWED_PUNCTUATION list are allowed.
     * The first character of the name must be alphanumeric.
     */
    public static final String VALIDATION_REGEX = String.format(
            "[\\p{Alnum}][\\p{Alnum} | \\p{Space} | %s]*",
            ALLOWED_PUNCTUATION_REGEX
    );

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public TagName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagName // instanceof handles nulls
                && fullName.equals(((TagName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
