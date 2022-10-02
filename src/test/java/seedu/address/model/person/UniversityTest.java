package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UniversityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new University(null));
    }

    @Test
    public void constructor_invalidUniversityName_throwsIllegalArgumentException() {
        String invalidUniversityName = "";
        assertThrows(IllegalArgumentException.class, () -> new University(invalidUniversityName));
    }

    @Test
    public void isValidUniversity() {
        // null university name
        assertThrows(NullPointerException.class, () -> University.isValidUniversity(null));

        // invalid university name
        assertFalse(University.isValidUniversity("")); // empty string
        assertFalse(University.isValidUniversity(" ")); // spaces only
        assertFalse(University.isValidUniversity("^")); // only non-alphanumeric characters
        assertFalse(University.isValidUniversity("nus*")); // contains non-alphanumeric characters

        // valid university name
        assertTrue(University.isValidUniversity("nus")); // alphabets only
        assertTrue(University.isValidUniversity("12345")); // numbers only
        assertTrue(University.isValidUniversity("university of 25th december")); // alphanumeric characters
        assertTrue(University.isValidUniversity("NTU")); // with capital letters
        assertTrue(University.isValidUniversity("national ancient university of wakanda empire")); // long names
    }
}
