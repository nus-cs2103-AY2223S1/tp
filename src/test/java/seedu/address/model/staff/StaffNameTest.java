package seedu.address.model.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.project.ProjectName;

public class StaffNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProjectName(null));
    }

    @Test
    public void constructor_invalidStaffName_throwsIllegalArgumentException() {
        String invalidStaffName = "";
        assertThrows(IllegalArgumentException.class, () -> new ProjectName(invalidStaffName));
    }

    @Test
    public void isValidStaffName() {
        // null name
        assertThrows(NullPointerException.class, () -> ProjectName.isValidProjectName(null));

        // invalid staff name
        assertFalse(ProjectName.isValidProjectName("")); // empty string
        assertFalse(ProjectName.isValidProjectName(" ")); // spaces only
        assertFalse(ProjectName.isValidProjectName("^")); // only non-alphanumeric characters
        assertFalse(ProjectName.isValidProjectName("peter*")); // contains non-alphanumeric characters

        // valid staff name
        assertTrue(ProjectName.isValidProjectName("peter jack")); // alphabets only
        assertTrue(ProjectName.isValidProjectName("12345")); // numbers only
        assertTrue(ProjectName.isValidProjectName("peter the 2nd")); // alphanumeric characters
        assertTrue(ProjectName.isValidProjectName("Capital Tan")); // with capital letters
        assertTrue(ProjectName.isValidProjectName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}

