package seedu.address.model.person.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SchoolTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new School(null));
    }

    @Test
    public void constructor_invalidSchool_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new School(invalidName));
    }

    @Test
    public void isValidSchool() {
        // null name
        assertThrows(NullPointerException.class, () -> School.isValidSchool(null));

        // invalid name
        assertFalse(School.isValidSchool("")); // empty string
        assertFalse(School.isValidSchool(" ")); // spaces only
        assertFalse(School.isValidSchool("^")); // only non-alphanumeric characters
        assertFalse(School.isValidSchool("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(School.isValidSchool("peter jack")); // alphabets only
        assertTrue(School.isValidSchool("12345")); // numbers only
        assertTrue(School.isValidSchool("peter the 2nd")); // alphanumeric characters
        assertTrue(School.isValidSchool("Capital Tan")); // with capital letters
        assertTrue(School.isValidSchool("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
