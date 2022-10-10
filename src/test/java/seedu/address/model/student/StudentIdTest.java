package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalArgumentException() {
        String invalidStudentId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidStudentId));
    }

    @Test
    public void isValidStudentId() {
        // null studentId
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid studentIds
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId("E0341923")); // capitalized e

        // valid studentIds
        assertTrue(StudentId.isValidStudentId("e0123456"));
        assertTrue(StudentId.isValidStudentId("e0000000")); // all repeated numbers
        assertTrue(StudentId.isValidStudentId("e0394718"));
    }
}
