package seedu.address.model.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class GradeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Grade(null));
    }

    @Test
    public void constructor_invalidGrade_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Grade(""));
        assertThrows(IllegalArgumentException.class, () -> new Grade(" "));
    }

    @Test
    public void isValidGrade() {
        assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null)); // null grade name

        assertFalse(Grade.isValidGrade("z")); // alphabets not A, B, C, D or F
        assertFalse(Grade.isValidGrade("w")); // alphabets not A, B, C, D or F
        assertFalse(Grade.isValidGrade("s")); // alphabets not A, B, C, D or F
        assertFalse(Grade.isValidGrade("+")); // symbols
        assertFalse(Grade.isValidGrade(" ")); // space
        assertFalse(Grade.isValidGrade("")); // empty

        // valid grade
        assertTrue(Grade.isValidGrade("A")); // A, B, C, D or F
        assertTrue(Grade.isValidGrade("B")); // A, B, C, D or F
        assertTrue(Grade.isValidGrade("C")); // A, B, C, D or F
        assertTrue(Grade.isValidGrade("D")); // A, B, C, D or F
        assertTrue(Grade.isValidGrade("F")); // A, B, C, D or F
        assertTrue(Grade.isValidGrade("a")); // A, B, C, D or F in small
        assertTrue(Grade.isValidGrade("b")); // A, B, C, D or F in small
        assertTrue(Grade.isValidGrade("c")); // A, B, C, D or F in small
        assertTrue(Grade.isValidGrade("d")); // A, B, C, D or F in small
        assertTrue(Grade.isValidGrade("f")); // A, B, C, D or F in small
        assertTrue(Grade.isValidGrade("PENDING...")); // pending
    }
}
