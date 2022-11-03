package seedu.application.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.AppUtil.checkArgument;

/**
 * Represents a Company's contact in the application list.
 * Guarantees: immutable; is valid as declared in {@link #isValidContact(String)}
 */
public class Contact {


    public static final String MESSAGE_CONSTRAINTS =
            "Contact numbers should only contain numbers, and it should be 5-15 digits long";
    public static final String VALIDATION_REGEX = "\\d{5,15}";
    public final String value;

    /**
     * Constructs a {@code Contact}.
     *
     * @param contact A valid contact number.
     */
    public Contact(String contact) {
        requireNonNull(contact);
        checkArgument(isValidContact(contact), MESSAGE_CONSTRAINTS);
        value = contact;
    }

    /**
     * Returns true if a given string is a valid contact number.
     */
    public static boolean isValidContact(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Contact // instanceof handles nulls
                && value.equals(((Contact) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
