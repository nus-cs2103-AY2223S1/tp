package seedu.rc4hdb.model.resident.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MatricNumber}.
 */
public class MatricNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MatricNumber(null));
    }

    @Test
    public void constructor_invalidMatricNumber_throwsIllegalArgumentException() {
        String invalidMatricNumber = "a1101234567a";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidMatricNumber));
    }

    @Test
    public void constructor_validMatricNumber_constructMatricNumber() {
        assertTrue(new MatricNumber("A0123456A") instanceof MatricNumber);
    }

    @Test
    public void isValidMatricNumber() {
        // null matricNumber
        assertThrows(NullPointerException.class, () -> MatricNumber.isValidMatricNumber(null));

        // blank matricNumber
        assertFalse(MatricNumber.isValidMatricNumber("")); // empty string
        assertFalse(MatricNumber.isValidMatricNumber(" ")); // spaces only

        // wrong format
        assertFalse(MatricNumber.isValidMatricNumber("B0123456A")); // first letter not A
        assertFalse(MatricNumber.isValidMatricNumber("!0123456A")); // first letter not alphabet
        assertFalse(MatricNumber.isValidMatricNumber("A0123456~")); // last letter not alphabet
        assertFalse(MatricNumber.isValidMatricNumber("A012345-1B")); // negative number
        assertFalse(MatricNumber.isValidMatricNumber("A012345~B")); // non-numerical character in middle
        assertFalse(MatricNumber.isValidMatricNumber("A01234567A")); // more than 7 non-negative integers
        assertFalse(MatricNumber.isValidMatricNumber("A012345A")); // less than 7 non-negative integers

        // valid matricNumber
        assertTrue(MatricNumber.isValidMatricNumber("A0123456A"));
        assertTrue(MatricNumber.isValidMatricNumber("A0123456B"));
        assertTrue(MatricNumber.isValidMatricNumber("A1234567A"));
        assertTrue(MatricNumber.isValidMatricNumber("A1234567B"));
        assertTrue(MatricNumber.isValidMatricNumber("A4657123Z"));
    }

}
