package seedu.masslinkers.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.masslinkers.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModTest {
    /**
     * Tests the behaviour of the constructor when {@code modName} is null.
     */
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Mod(null));
    }

    /**
     * Tests the behaviour of the constructor when {@code modName} is invalid.
     */
    @Test
    public void constructor_invalidMod_throwsIllegalArgumentException() {
        String invalidMod = "";
        assertThrows(IllegalArgumentException.class, () -> new Mod(invalidMod));
    }

    /**
     * Tests the behaviour of the {@code isValidModName} method
     */
    @Test
    public void isValidMod() {
        // null mod
        assertThrows(NullPointerException.class, () -> Mod.isValidModName(null));

        // invalid mod names
        assertFalse(Mod.isValidModName("")); // empty string
        assertFalse(Mod.isValidModName(" ")); // spaces only
        assertFalse(Mod.isValidModName("9312 1534")); // spaces within digits
        assertFalse(Mod.isValidModName("this is a mod")); // more than 10 chars
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

    /**
     * Tests the behaviour of the {@code isSameMod} method
     */
    @Test
    public void isSameMod() {
        Mod mod = new Mod("CS2103T");

        // different mods
        assertFalse(mod.isSameMod(new Mod("CS2103")));
        assertFalse(mod.isSameMod(null));

        // same mod
        assertTrue(mod.isSameMod(mod));
        assertTrue(mod.isSameMod(new Mod("CS2103T")));
    }

    /**
     * Tests the behaviour of the {@code getModCategory} method
     */
    @Test
    public void modCategory_correctAssigment() {
        // CS Mods
        assertEquals(Mod.ModCategory.COMP, new Mod("CS2100").getModCategory());
        assertEquals(Mod.ModCategory.COMP, new Mod("CS2103T").getModCategory());
        assertEquals(Mod.ModCategory.COMP, new Mod("CP2106").getModCategory());
        assertEquals(Mod.ModCategory.COMP, new Mod("IS1103").getModCategory());

        // Math Mods
        assertEquals(Mod.ModCategory.MATH, new Mod("ST2334").getModCategory());
        assertEquals(Mod.ModCategory.MATH, new Mod("MA1521").getModCategory());
        assertEquals(Mod.ModCategory.MATH, new Mod("MA2001").getModCategory());

        // Sci Mods
        assertEquals(Mod.ModCategory.SCI, new Mod("LSM1301").getModCategory());
        assertEquals(Mod.ModCategory.SCI, new Mod("CM1102").getModCategory());
        assertEquals(Mod.ModCategory.SCI, new Mod("PC1201").getModCategory());

        // GE Mods
        assertEquals(Mod.ModCategory.GE, new Mod("GEA1000").getModCategory());
        assertEquals(Mod.ModCategory.GE, new Mod("UTC1102B").getModCategory());
        assertEquals(Mod.ModCategory.GE, new Mod("GESS1025").getModCategory());

        // COMMS Mods
        assertEquals(Mod.ModCategory.COMMS, new Mod("ES2660").getModCategory());

        // UE Mods
        assertEquals(Mod.ModCategory.UE, new Mod("CFG1002").getModCategory());
    }
}
