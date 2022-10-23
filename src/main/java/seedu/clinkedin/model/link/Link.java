package seedu.clinkedin.model.link;

import static java.util.Objects.requireNonNull;

/**
 * Represents an optional note about a person in the clinkedin book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLink(String)}
 */
public class Link {

    public static final String MESSAGE_CONSTRAINTS = "Link should be valid links, "
            + "and it should not be blank. If you do not want to add a link, please leave the field blank. "
            + "Blank links will be ignored.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code link}.
     *
     * @param s A valid link.
     */
    public Link(String s) {
        requireNonNull(s);
        value = s.trim();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && value.equals(((Link) other).value)); // state check
    }

    /**
     * Returns true if a given string is a valid link.
     */
    public static boolean isValidLink(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
