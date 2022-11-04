package seedu.hrpro.model.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StaffLeaveTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StaffLeave(null));
    }

    @Test
    public void constructor_invalidStaffLeave_throwsIllegalArgumentException() {
        String invalidStaffLeave = "";
        assertThrows(IllegalArgumentException.class, () -> new StaffLeave(invalidStaffLeave));
    }

    @Test
    public void isValidStaffLeave() {
        // null name
        assertThrows(NullPointerException.class, () -> StaffLeave.isValidStaffLeave(null));

        // invalid staff leave
        assertFalse(StaffLeave.isValidStaffLeave("")); // empty string
        assertFalse(StaffLeave.isValidStaffLeave(" ")); // spaces only
        assertFalse(StaffLeave.isValidStaffLeave("^")); // only non-alphanumeric characters
        assertFalse(StaffLeave.isValidStaffLeave("peter*")); // contains non-alphanumeric characters
        assertFalse(StaffLeave.isValidStaffLeave("12345")); // numbers only
        assertFalse(StaffLeave.isValidStaffLeave("peter jack")); // alphabets only
        assertFalse(StaffLeave.isValidStaffLeave("peter the 2nd")); // alphanumeric characters
        assertFalse(StaffLeave.isValidStaffLeave("Capital Tan")); // with capital letters
        assertFalse(StaffLeave.isValidStaffLeave("David Roger Jackson Ray Jr 2nd")); // long names

        // valid staff leave
        assertTrue(StaffLeave.isValidStaffLeave("true")); // On leave
        assertTrue(StaffLeave.isValidStaffLeave("false")); // working

    }
}



