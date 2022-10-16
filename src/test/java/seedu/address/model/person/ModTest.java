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
        assertFalse(Mod.isValidModName("ALLWORDS")); // all words
        assertFalse(Mod.isValidModName("1234")); // all numbers
        assertFalse(Mod.isValidModName("cs2103")); // not uppercase
        assertFalse(Mod.isValidModName("CS2103t")); // not uppercase
        assertFalse(Mod.isValidModName("#CS@2103")); // special characters

        // valid mod names
        assertTrue(Mod.isValidModName("CS1101")); // regular mod name
        assertTrue(Mod.isValidModName("GESS1025")); // mod with long prefix
        assertTrue(Mod.isValidModName("CS2103T")); // mod name with letter(s) behind
        assertTrue(Mod.isValidModName("CS1231S")); // mod name with letter(s) behind
    }
}
