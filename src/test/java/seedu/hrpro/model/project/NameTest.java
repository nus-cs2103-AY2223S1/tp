package seedu.hrpro.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProjectName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ProjectName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ProjectName.isValidProjectName(null));

        // invalid name
        assertFalse(ProjectName.isValidProjectName("")); // empty string
        assertFalse(ProjectName.isValidProjectName(" ")); // spaces only
        assertFalse(ProjectName.isValidProjectName("^")); // only non-alphanumeric characters
        assertFalse(ProjectName.isValidProjectName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ProjectName.isValidProjectName("peter jack")); // alphabets only
        assertTrue(ProjectName.isValidProjectName("12345")); // numbers only
        assertTrue(ProjectName.isValidProjectName("peter the 2nd")); // alphanumeric characters
        assertTrue(ProjectName.isValidProjectName("Capital Tan")); // with capital letters
        assertTrue(ProjectName.isValidProjectName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
