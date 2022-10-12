package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidID_throwsIllegalArgumentException() {
        String invalidId1 = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidId1));

        String invalidId2 = "C1234567A";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidId2));

        String invalidId3 = "A123X";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidId3));

        String invalidId4 = "1234567P";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidId4));

        String invalidId5 = "A12345678";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidId5));

    }

    @Test
    public void isValidAddress() {
        // null student id
        assertThrows(NullPointerException.class, () -> StudentId.isValidId(null));

        // invalid student ids
        assertFalse(StudentId.isValidId("")); // empty string
        assertFalse(StudentId.isValidId(" ")); // spaces only
        assertFalse(StudentId.isValidId("123")); // 3 numbers only
        assertFalse(StudentId.isValidId("123456789")); // starts with number
        assertFalse(StudentId.isValidId("C1234567A")); // does not start with A
        assertFalse(StudentId.isValidId("A123456C")); // 6 numbers only
        assertFalse(StudentId.isValidId("A12345678")); // ends with number
        assertFalse(StudentId.isValidId("A1234567899B")); // too many numbers

        // valid student ids
        assertTrue(StudentId.isValidId("A1234567A"));
        assertTrue(StudentId.isValidId("A0000000Y"));
        assertTrue(StudentId.isValidId("A0297654M"));
    }
}
