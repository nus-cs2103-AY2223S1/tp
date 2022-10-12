package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Contact is a Person who is related to a task and is present in the address book.
 * Guarantees: immutable; contact is valid as declared in {@link #isValidContactName(String)}
 */
public class Contact {

    public static final String MESSAGE_CONSTRAINTS = "Contact names should be letters";
    public static final String VALIDATION_REGEX = "^[a-zA-Z ]*$";

    public final String contactName;

    /**
     * Constructs a {@code Contact}.
     *
     * @param contactName A valid contact name.
     */
    public Contact(String contactName) {
        // TODO: Contact must be in address book
        requireNonNull(contactName);
        this.contactName = contactName;
    }

    /**
     * Returns true if a given string is a valid contact name.
     */
    public static boolean isValidContactName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getContactName() {
        return contactName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Contact) // instanceof handles nulls
            && contactName.equals(((Contact) other).getContactName()); // state check
    }

    @Override
    public int hashCode() {
        return contactName.hashCode();
    }

    @Override
    public String toString() {
        return contactName;
    }
}
