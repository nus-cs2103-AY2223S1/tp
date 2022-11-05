package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HospitalWingTest {

    @Test
    public void isValidEnum() {
        assertAll(() -> assertEquals("EAST", HospitalWing.HospitalWingTypes.EAST.name()), () ->
                assertEquals("WEST", HospitalWing.HospitalWingTypes.WEST.name()), () ->
                        assertEquals("NORTH", HospitalWing.HospitalWingTypes.NORTH.name()), () ->
                                assertEquals("SOUTH", HospitalWing.HospitalWingTypes.SOUTH.name()));
    }

    @Test
    public void enum_toStringTest() {
        assertAll(() -> assertEquals("East", HospitalWing.HospitalWingTypes.EAST.toString()), () ->
                assertEquals("West", HospitalWing.HospitalWingTypes.WEST.toString()), () ->
                        assertEquals("North", HospitalWing.HospitalWingTypes.NORTH.toString()), () ->
                                assertEquals("South", HospitalWing.HospitalWingTypes.SOUTH.toString()));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HospitalWing(null));
    }

    @Test
    public void constructor_invalidHospitalWing_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing(""));
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing(" "));
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("1")); // numbers
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("@@")); //symbols
        // strings which is not part of the enum
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("me"));
        // invalid inputs
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("NORTH--"));
        // strings with spaces in between
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("SOU TH"));
        // combination of two enum strings
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("WESTNORTH"));
        // combination of two enum strings with spaces in between
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("west north"));
    }

    @Test
    public void isValidHospitalWing() {
        // null HospitalWing
        assertThrows(NullPointerException.class, () -> HospitalWing.isValidHospitalWing(null));

        // valid HospitalWing
        assertTrue(HospitalWing.isValidHospitalWing("north")); // north in all lower-case
        assertTrue(HospitalWing.isValidHospitalWing("south")); // south in all lower-case
        assertTrue(HospitalWing.isValidHospitalWing("east")); // east in all lower-case
        assertTrue(HospitalWing.isValidHospitalWing("west")); // west in all lower-case
        assertTrue(HospitalWing.isValidHospitalWing("NORTH")); // north in all upper-case
        assertTrue(HospitalWing.isValidHospitalWing("SOUTH")); // south in all upper-case
        assertTrue(HospitalWing.isValidHospitalWing("EAST")); // east in all upper-case
        assertTrue(HospitalWing.isValidHospitalWing("WEST")); // west in all upper-case
        assertTrue(HospitalWing.isValidHospitalWing("NOrth")); // north in mix of upper and lower cases
        assertTrue(HospitalWing.isValidHospitalWing("SOuth")); // south in mix of upper and lower cases
        assertTrue(HospitalWing.isValidHospitalWing("EAst")); // east in mix of upper and lower cases
        assertTrue(HospitalWing.isValidHospitalWing("weST")); // west in mix of upper and lower cases
    }

    @Test
    public void toStringTest() {
        String expectedMessage1 = "Hospital Wing: North";
        String expectedMessage2 = "Hospital Wing: South";

        // same HospitalWing
        assertEquals(expectedMessage1, new HospitalWing("north").toString());
        assertEquals(expectedMessage2, new HospitalWing("south").toString());

        // different HospitalWing
        assertNotEquals(expectedMessage1, new HospitalWing("south").toString());
        assertNotEquals(expectedMessage2, new HospitalWing("north").toString());
    }

    @Test
    public void equals() {
        HospitalWing hospitalWing = new HospitalWing("north");

        // same object -> returns true
        assertTrue(hospitalWing.equals(hospitalWing));

        // same values -> returns true
        HospitalWing hospitalWingCopy = new HospitalWing("north");
        assertTrue(hospitalWing.equals(hospitalWingCopy));

        // different types -> returns false
        assertFalse(hospitalWing.equals(1));
        assertFalse(hospitalWing.equals("north"));

        // null -> returns false
        assertFalse(hospitalWing.equals(null));

        // different HospitalWing -> returns false
        HospitalWing differentHospitalWing = new HospitalWing("south");
        assertFalse(hospitalWing.equals(differentHospitalWing));
    }

    @Test
    public void hashCodeTest() {
        HospitalWing hospitalWing1 = new HospitalWing("north");
        HospitalWing hospitalWing2 = new HospitalWing("north");
        HospitalWing hospitalWing3 = new HospitalWing("south");

        // same object -> same hashcode
        assertEquals(hospitalWing1.hashCode(), hospitalWing1.hashCode());

        // same values -> same hashcode
        assertEquals(hospitalWing1.hashCode(), hospitalWing2.hashCode());

        // different values -> different hashcode
        assertNotEquals(hospitalWing1.hashCode(), hospitalWing3.hashCode());
        assertNotEquals(hospitalWing2.hashCode(), hospitalWing3.hashCode());
    }
}
