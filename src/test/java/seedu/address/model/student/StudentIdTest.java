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

    @Test
    void contains_studentIdContainsKeyword_returnsTrue() {
        // Blank keyword
        assertTrue(new StudentId("e0123456").contains(""));

        // Exact keyword
        assertTrue(new StudentId("e0123456").contains("e0123456"));

        // Partial matching keyword
        assertTrue(new StudentId("e0123456").contains("234"));
    }

    @Test
    void contains_studentIdContainsKeyword_returnsFalse() {
        // Keyword with only alphabets
        assertFalse(new StudentId("e0123456").contains("aaaaa"));

        // Non-matching keyword
        assertFalse(new StudentId("e0123456").contains("a9d87g"));

        // Mixed-case keyword
        assertFalse(new StudentId("e0123456").contains("E01234"));
    }
}
