package seedu.hrpro.model.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StaffTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StaffTitle(null));
    }

    @Test
    public void constructor_invalidStaffTitle_throwsIllegalArgumentException() {
        String invalidStaffTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new StaffTitle(invalidStaffTitle));
    }

    @Test
    public void isValidStaffTitle() {
        // null name
        assertThrows(NullPointerException.class, () -> StaffTitle.isValidStaffTitle(null));

        // invalid staff title
        assertFalse(StaffTitle.isValidStaffTitle("")); // empty string
        assertFalse(StaffTitle.isValidStaffTitle(" ")); // spaces only
        assertFalse(StaffTitle.isValidStaffTitle("!")); // only non-alphanumeric characters
        assertFalse(StaffTitle.isValidStaffTitle("Senior UX designer*")); // contains non-alphanumeric characters

        // valid staff title
        assertTrue(StaffTitle.isValidStaffTitle("Senior UX Designer")); // alphabets only
        assertTrue(StaffTitle.isValidStaffTitle("peter the 2nd")); // alphanumeric characters
        assertTrue(StaffTitle.isValidStaffTitle("12345")); // numbers only
        assertTrue(StaffTitle.isValidStaffTitle("Capital Tan")); // with capital letters
        assertTrue(StaffTitle.isValidStaffTitle("David Roger Jackson Ray Jr 2nd")); // long titles
    }
}


