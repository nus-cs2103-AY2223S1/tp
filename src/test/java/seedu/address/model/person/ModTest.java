package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Mod(null));
    }

    @Test
    public void constructor_invalidMod_throwsIllegalArgumentException() {
        String invalidMod = "";
        assertThrows(IllegalArgumentException.class, () -> new Mod(invalidMod));
    }

    @Test
    public void isValidMod() {
        // null mod
        assertThrows(NullPointerException.class, () -> Mod.isValidModName(null));

        // invalid mod names
        assertFalse(Mod.isValidModName("")); // empty string
        assertFalse(Mod.isValidModName(" ")); // spaces only
        assertFalse(Mod.isValidModName("9312 1534")); // spaces within digits
        assertFalse(Mod.isValidModName("this is a mod")); // spaces within words

        // valid mod names
        assertTrue(Mod.isValidModName("CS1")); // exactly 3 chars
        assertTrue(Mod.isValidModName("CS2103"));
        assertTrue(Mod.isValidModName("ST124293842033123")); // long phone numbers
    }
}
