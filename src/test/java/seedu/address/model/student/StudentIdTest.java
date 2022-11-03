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
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidId));
    }

    @Test
    public void isValidStudentId() {
        // null id
        assertThrows(NullPointerException.class, () -> StudentId.isValidId(null));

        // blank id
        assertFalse(StudentId.isValidId("")); // empty string
        assertFalse(StudentId.isValidId(" ")); // spaces only

        // invalid id
        assertFalse(StudentId.isValidId("A123")); // wrong length
        assertFalse(StudentId.isValidId("B1231231B")); // wrong first letter
        assertFalse(StudentId.isValidId("A000 0000B")); // spaces in id
        assertFalse(StudentId.isValidId("123456789")); // all numbers
        assertFalse(StudentId.isValidId(" A0000000A")); // leading space
        assertFalse(StudentId.isValidId("A0000000A ")); // trailing space

        // valid id
        assertTrue(StudentId.isValidId("a0000000a")); // small letters
        assertTrue(StudentId.isValidId("A0000000A")); // capital letters
    }
}
