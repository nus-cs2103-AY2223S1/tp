package seedu.hrpro.model.staff;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.commons.util.AppUtil.checkArgument;

/**
 * Represents a staff contact in HR Pro Max++.
 * Guarantees: immutable; is valid as declared in {@link #isValidStaffContact(String)}.
 */
public class StaffContact {
    public static final String MESSAGE_CONSTRAINTS =
            "Staff contact should only contain exactly 8 digits and begin with 6, 8 or 9. Also it should not be blank.";

    /*
     * Staff contact should have exactly 8 digits starting with 6, 8 or 9.
     */
    public static final String VALIDATION_REGEX = "[689]{1}\\d{7}";

    private final String staffContact;

    /**
     * Constructs a {@code StaffContact}.
     *
     * @param contact A valid contact.
     */
    public StaffContact(String contact) {
        requireNonNull(contact);
        checkArgument(isValidStaffContact(contact), MESSAGE_CONSTRAINTS);
        staffContact = contact;
    }

    /**
     * Returns true if a given string is a valid staff contact.
     */
    public static boolean isValidStaffContact(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return staffContact;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StaffContact // instanceof handles nulls
                && staffContact.equals(((StaffContact) other).staffContact)); // state check
    }

    @Override
    public int hashCode() {
        return staffContact.hashCode();
    }

    public String getStaffContact() {
        return staffContact;
    }
}
