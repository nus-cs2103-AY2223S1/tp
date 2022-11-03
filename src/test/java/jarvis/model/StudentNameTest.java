package jarvis.model;

import static jarvis.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jarvis.model.StudentName;

public class StudentNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> StudentName.isValidName(null));

        // invalid name
        assertFalse(StudentName.isValidName("")); // empty string
        assertFalse(StudentName.isValidName(" ")); // spaces only
        assertFalse(StudentName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(StudentName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(StudentName.isValidName("peter jack")); // alphabets only
        assertTrue(StudentName.isValidName("12345")); // numbers only
        assertTrue(StudentName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(StudentName.isValidName("Capital Tan")); // with capital letters
        assertTrue(StudentName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
