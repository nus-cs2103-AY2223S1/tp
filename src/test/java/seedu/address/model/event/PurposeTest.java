package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PurposeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Purpose(null));
    }

    @Test
    public void constructor_invalidPurpose_throwsIllegalArgumentException() {
        String invalidPurpose = "";
        assertThrows(IllegalArgumentException.class, () -> new Purpose(invalidPurpose));
    }

    @Test
    public void isValidPurpose() {
        // null address
        assertThrows(NullPointerException.class, () -> Purpose.isValidPurpose(null));

        // invalid addresses
        assertFalse(Purpose.isValidPurpose("")); // empty string
        assertFalse(Purpose.isValidPurpose(" ")); // spaces only

        // valid addresses
        assertTrue(Purpose.isValidPurpose("100 dollars off all Cars"));
        assertTrue(Purpose.isValidPurpose("-")); // one character
        assertTrue(Purpose.isValidPurpose("$100 off all...Cars!")); // any characters including non-alphanumeric
        assertTrue(Purpose.isValidPurpose(
                "$0.10000000 off all toothpastes that are sold in the next 60 mins")); // long purpose
    }
}
