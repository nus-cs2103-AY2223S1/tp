package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class KeyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Key.convertToKey(null));
    }

    @Test
    public void constructor_invalidKeyName_throwsIllegalArgumentException() {
        String invalidKeyName = "";
        assertThrows(IllegalArgumentException.class, () -> Key.convertToKey(invalidKeyName));
    }

    @Test
    public void isValidKeyName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Key.isValidKey(null));

        // valid tag names
        assertTrue(Key.isValidKey("patIent"));
        assertTrue(Key.isValidKey("marK"));
        assertTrue(Key.isValidKey("TaG"));

        // shortcuts
        assertTrue(Key.isValidKey("p"));
        assertTrue(Key.isValidKey("m"));
        assertTrue(Key.isValidKey("t"));
    }

    @Test
    public void isKeyNameCorrect() {
        assertEquals(Key.TAG.toString(), "Tag");
        assertEquals(Key.PATIENT.toString(), "Patient");
        assertEquals(Key.MARK.toString(), "Mark");
    }

    @Test
    public void isKeyConversionCorrect() {
        assertThrows(IllegalArgumentException.class, () -> Key.convertToKey("Date"));
        assertEquals(Key.convertToKey("Tag"), Key.TAG);
        assertEquals(Key.convertToKey("p"), Key.PATIENT);
        assertEquals(Key.convertToKey("m"), Key.MARK);
    }
}
