package jeryl.fyp.model.student;

import static jeryl.fyp.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalArgumentException() {
        String invalidStudentId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidStudentId));
    }

    @Test
    public void isValidStudentId() {
        // null student ID
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid student IDs
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only
        assertFalse(StudentId.isValidStudentId("A91P")); // less than 7 numbers
        assertFalse(StudentId.isValidStudentId("matric")); // non-numeric
        assertFalse(StudentId.isValidStudentId("183q0791")); // alphabets within digits
        assertFalse(StudentId.isValidStudentId("A931 1534Q")); // spaces within digits
        assertFalse(StudentId.isValidStudentId("A 9311534Q")); // spaces within
        assertFalse(StudentId.isValidStudentId("A9311534 Q")); // spaces within
        assertFalse(StudentId.isValidStudentId("a0193773X")); // lower capitals
        assertFalse(StudentId.isValidStudentId("A0193773w")); // lower capitals

        // valid student IDs
        assertTrue(StudentId.isValidStudentId("A0123456X")); // A + 7 digits + an alphabet
        assertTrue(StudentId.isValidStudentId("A0000000A")); // A + 7 digits + an alphabet
    }
}
