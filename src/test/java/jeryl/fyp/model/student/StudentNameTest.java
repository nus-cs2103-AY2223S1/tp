package jeryl.fyp.model.student;

import static jeryl.fyp.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
        assertThrows(NullPointerException.class, () -> StudentName.isValidStudentName(null));

        // invalid name
        assertFalse(StudentName.isValidStudentName("")); // empty string
        assertFalse(StudentName.isValidStudentName(" ")); // spaces only
        assertFalse(StudentName.isValidStudentName("^")); // only unsupportedcharacters
        assertFalse(StudentName.isValidStudentName("/////")); // only supported characters

        // valid name
        assertTrue(StudentName.isValidStudentName("peter jack")); // alphabets only
        assertTrue(StudentName.isValidStudentName("Raju S/O Muthu")); // contains non-alphanumeric characters
        assertTrue(StudentName.isValidStudentName("Capital Tan")); // with capital letters
        assertTrue(StudentName.isValidStudentName("David Roger Jackson Ray Jr")); // long names
    }
}
