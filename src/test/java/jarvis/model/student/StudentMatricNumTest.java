package jarvis.model.student;

import static jarvis.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jarvis.model.MatricNum;

public class StudentMatricNumTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MatricNum(null));
    }

    @Test
    public void constructor_invalidMatric_throwsIllegalArgumentException() {
        String invalidMatric = "";
        assertThrows(IllegalArgumentException.class, () -> new MatricNum(invalidMatric));
    }

    @Test
    public void isValidMatricNum() {
        // null matric number
        assertThrows(NullPointerException.class, () -> MatricNum.isValidMatricNum(null));

        // invalid matric number
        assertFalse(MatricNum.isValidMatricNum("")); // empty string
        assertFalse(MatricNum.isValidMatricNum(" ")); // spaces only
        assertFalse(MatricNum.isValidMatricNum("B1234567U")); // not start with A
        assertFalse(MatricNum.isValidMatricNum("a1234567U")); // starts with small letter A
        assertFalse(MatricNum.isValidMatricNum("A1234567u")); // ends with small letter
        assertFalse(MatricNum.isValidMatricNum("A123456U")); // 6 digits

        // valid matric number
        assertTrue(MatricNum.isValidMatricNum("A1234567U")); // alphabets only
        assertTrue(MatricNum.isValidMatricNum("A1234567A")); // numbers only
    }

    @Test
    public void equals() {

    }
}
