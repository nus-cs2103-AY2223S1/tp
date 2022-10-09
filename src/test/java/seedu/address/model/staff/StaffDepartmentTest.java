package seedu.address.model.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StaffDepartmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StaffDepartment(null));
    }

    @Test
    public void constructor_invalidStaffDepartment_throwsIllegalArgumentException() {
        String invalidStaffDepartment = "";
        assertThrows(IllegalArgumentException.class, () -> new StaffDepartment(invalidStaffDepartment));
    }

    @Test
    public void isValidStaffDepartment() {
        // null name
        assertThrows(NullPointerException.class, () -> StaffDepartment.isValidStaffDepartment(null));

        // invalid staff department
        assertFalse(StaffDepartment.isValidStaffDepartment("")); // empty string
        assertFalse(StaffDepartment.isValidStaffDepartment(" ")); // spaces only
        assertFalse(StaffDepartment.isValidStaffDepartment("^")); // only non-alphanumeric characters
        assertFalse(StaffDepartment.isValidStaffDepartment("peter*")); // contains non-alphanumeric characters
        assertFalse(StaffDepartment.isValidStaffDepartment("12345")); // numbers only
        assertFalse(StaffDepartment.isValidStaffDepartment("peter the 2nd")); // alphanumeric characters

        // valid staff department
        assertTrue(StaffDepartment.isValidStaffDepartment("peter jack")); // alphabets only
        assertTrue(StaffDepartment.isValidStaffDepartment("Capital Tan")); // with capital letters
        //TODO: Fix long department name test
        assertTrue(StaffDepartment.isValidStaffDepartment("David Roger Jackson Ray Jr 2nd")); // long names
    }
}



