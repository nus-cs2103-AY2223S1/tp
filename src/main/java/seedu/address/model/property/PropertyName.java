package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's name in the property book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPropertyName(String)}
 */
public class PropertyName {

    public static final String MESSAGE_CONSTRAINTS =
            "Property names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public PropertyName(String name) {
        requireNonNull(name);
        checkArgument(isValidPropertyName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPropertyName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyName // instanceof handles nulls
                && fullName.equalsIgnoreCase(((PropertyName) other).fullName)); // state check
    }

    public int compareTo(Object other) {
        return fullName.compareToIgnoreCase(((PropertyName) other).fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
