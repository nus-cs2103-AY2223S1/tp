package seedu.studmap.model.attribute;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.util.AppUtil.checkArgument;

/**
 * Represents an attribute.
 * Guarantees: immutable; name is valid as declared in {@link #isValidAttributeName(String)}
 */
public class Attribute {

    public static final String MESSAGE_CONSTRAINTS = "Invalid Attribute entered";

    public final String attributeName;

    /**
     * Constructs a {@code Attribute}.
     *
     * @param attributeName A valid attribute.
     */
    public Attribute(String attributeName) {
        requireNonNull(attributeName);
        checkArgument(isValidAttributeName(attributeName), MESSAGE_CONSTRAINTS);
        this.attributeName = attributeName;
    }

    /**
     * Returns true if a given string is a valid attribute name.
     */
    public static boolean isValidAttributeName(String test) {
        return test.matches("name")
                || test.matches("phone")
                || test.matches("address")
                || test.matches("email")
                || test.matches("attendance");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.studmap.model.attribute.Attribute // instanceof handles nulls
                && attributeName.equals((
                        (seedu.studmap.model.attribute.Attribute) other).attributeName)); // state check
    }

    @Override
    public int hashCode() {
        return attributeName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + attributeName + ']';
    }

}
