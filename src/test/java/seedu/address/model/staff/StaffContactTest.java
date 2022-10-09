package seedu.address.model.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StaffContactTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StaffContact(null));
    }

    @Test
    public void constructor_invalidStaffContact_throwsIllegalArgumentException() {
        String invalidStaffContact = "";
        assertThrows(IllegalArgumentException.class, () -> new StaffContact(invalidStaffContact));
    }

    @Test
    public void isValidStaffContact() {
        // null name
        assertThrows(NullPointerException.class, () -> StaffContact.isValidStaffContact(null));

        // invalid staff contact
        assertFalse(StaffContact.isValidStaffContact("")); // empty string
        assertFalse(StaffContact.isValidStaffContact(" ")); // spaces only
        assertFalse(StaffContact.isValidStaffContact("^")); // only non-alphanumeric characters
        assertFalse(StaffContact.isValidStaffContact("peter*")); // contains non-alphanumeric characters
        assertFalse(StaffContact.isValidStaffContact("peter the 2nd")); // alphanumeric characters
        assertFalse(StaffContact.isValidStaffContact("Capital Tan")); // with capital letters
        assertFalse(StaffContact.isValidStaffContact("David Roger Jackson Ray Jr 2nd")); // long names
        assertFalse(StaffContact.isValidStaffContact("peter jack")); // alphabets only

        // valid staff contact
        assertTrue(StaffContact.isValidStaffContact("12345")); // numbers only

    }
}


