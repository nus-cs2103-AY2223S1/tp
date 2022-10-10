package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIDTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentID(null));
    }

    @Test
    public void constructor_invalidID_throwsIllegalArgumentException() {
        String invalidID1 = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentID(invalidID1));

        String invalidID2 = "C1234567A";
        assertThrows(IllegalArgumentException.class, () -> new StudentID(invalidID2));

        String invalidID3 = "A123X";
        assertThrows(IllegalArgumentException.class, () -> new StudentID(invalidID3));

        String invalidID4 = "1234567P";
        assertThrows(IllegalArgumentException.class, () -> new StudentID(invalidID4));

        String invalidID5 = "A12345678";
        assertThrows(IllegalArgumentException.class, () -> new StudentID(invalidID5));

    }

    @Test
    public void isValidAddress() {
        // null student id
        assertThrows(NullPointerException.class, () -> StudentID.isValidID(null));

        // invalid student ids
        assertFalse(StudentID.isValidID("")); // empty string
        assertFalse(StudentID.isValidID(" ")); // spaces only
        assertFalse(StudentID.isValidID("123")); // 3 numbers only
        assertFalse(StudentID.isValidID("123456789")); // starts with number
        assertFalse(StudentID.isValidID("C1234567A")); // does not start with A
        assertFalse(StudentID.isValidID("A123456C")); // 6 numbers only
        assertFalse(StudentID.isValidID("A12345678")); // ends with number
        assertFalse(StudentID.isValidID("A1234567899B")); // too many numbers

        // valid student ids
        assertTrue(StudentID.isValidID("A1234567A"));
        assertTrue(StudentID.isValidID("A0000000Y"));
        assertTrue(StudentID.isValidID("A0297654M"));
    }
}
