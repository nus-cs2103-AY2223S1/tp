package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    }

    @Test
    public void isKeyNameCorrect() {
        assertEquals(Key.TAG.toString(), "Tag");
        assertEquals(Key.PATIENT.toString(), "Patient");
    }

    @Test
    public void isKeyConversionCorrect() {
        assertThrows(IllegalArgumentException.class, () -> Key.convertToKey("Date"));
        assertEquals(Key.convertToKey("Tag"), Key.TAG);
    }
}
