package seedu.address.model.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StaffNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StaffName(null));
    }

    @Test
    public void constructor_invalidStaffName_throwsIllegalArgumentException() {
        String invalidStaffName = "";
        assertThrows(IllegalArgumentException.class, () -> new StaffName(invalidStaffName));
    }

    @Test
    public void isValidStaffName() {
        // null name
        assertThrows(NullPointerException.class, () -> StaffName.isValidStaffName(null));

        // invalid staff name
        assertFalse(StaffName.isValidStaffName("")); // empty string
        assertFalse(StaffName.isValidStaffName(" ")); // spaces only
        assertFalse(StaffName.isValidStaffName("^")); // only non-alphanumeric characters
        assertFalse(StaffName.isValidStaffName("peter*")); // contains non-alphanumeric characters

        // valid staff name
        assertTrue(StaffName.isValidStaffName("peter jack")); // alphabets only
        assertTrue(StaffName.isValidStaffName("12345")); // numbers only
        assertTrue(StaffName.isValidStaffName("peter the 2nd")); // alphanumeric characters
        assertTrue(StaffName.isValidStaffName("Capital Tan")); // with capital letters
        assertTrue(StaffName.isValidStaffName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}

