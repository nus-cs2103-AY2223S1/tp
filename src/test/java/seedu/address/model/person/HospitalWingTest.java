package seedu.address.model.person;

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
    public void constructor_emptyString_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing(""));
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing(" "));
    }

    @Test
    public void constructor_garbledHospitalWing_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("abcdefgh")); // garbled text
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("/inp /fn /outp")); // other prefixes
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("@@")); // symbols
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("123456")); // numbers
    }

    @Test
    public void constructor_invalidHospitalWing_throwsIllegalArgumentException() {
        // enum values with unexpected character in between
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("SOU TH"));
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("souths")); // one additional character
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("WES")); // missing character
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("WES ")); // missing character
    }

    @Test
    public void isValidHospitalWingTest() {
        // null hospital wing
        assertThrows(NullPointerException.class, (
                ) -> HospitalWing.isValidHospitalWing(null));

        // blank hospital wing
        assertFalse(HospitalWing.isValidHospitalWing("")); // empty string
        assertFalse(HospitalWing.isValidHospitalWing(" ")); // spaces only

        // invalid hospital wing
        assertFalse(HospitalWing.isValidHospitalWing("sou th")); //space in between
        assertFalse(HospitalWing.isValidHospitalWing("southsouth")); //duplicate enum values
        assertFalse(HospitalWing.isValidHospitalWing("south north")); //two enum values with space
        assertFalse(HospitalWing.isValidHospitalWing("wests")); //additonal character
        assertFalse(HospitalWing.isValidHospitalWing("WES")); //missing character
        assertFalse(HospitalWing.isValidHospitalWing("WEST ")); //additional space
        assertFalse(HospitalWing.isValidHospitalWing("")); //empty string
        assertFalse(HospitalWing.isValidHospitalWing(" ")); //space only
        assertFalse(HospitalWing.isValidHospitalWing("123")); //numbers
        assertFalse(HospitalWing.isValidHospitalWing("@@@")); //symbols
        assertFalse(HospitalWing.isValidHospitalWing("abdadew")); //not valid enum values

        // valid hospital wing
        assertTrue(HospitalWing.isValidHospitalWing("SOUTH"));
        assertTrue(HospitalWing.isValidHospitalWing("SOUth"));
        assertTrue(HospitalWing.isValidHospitalWing("south"));
        assertTrue(HospitalWing.isValidHospitalWing("NORth"));
        assertTrue(HospitalWing.isValidHospitalWing("NORTH"));
        assertTrue(HospitalWing.isValidHospitalWing("north"));
        assertTrue(HospitalWing.isValidHospitalWing("EAST"));
        assertTrue(HospitalWing.isValidHospitalWing("EAst"));
        assertTrue(HospitalWing.isValidHospitalWing("east"));
        assertTrue(HospitalWing.isValidHospitalWing("west"));
        assertTrue(HospitalWing.isValidHospitalWing("WESt"));
        assertTrue(HospitalWing.isValidHospitalWing("WEST"));
    }

    @Test
    public void toStringTest() {
        String expectedMessageForSouth = "Hospital Wing: South";
        String expectedMessageNorth = "Hospital Wing: North";
        HospitalWing hospitalWingSouth = new HospitalWing("south");
        HospitalWing hospitalWingNorth = new HospitalWing("north");

        // same hospital wing
        assertEquals(expectedMessageForSouth, hospitalWingSouth.toString());
        assertEquals(expectedMessageNorth, hospitalWingNorth.toString());
        // different hospital wing
        assertNotEquals(expectedMessageForSouth, hospitalWingNorth.toString());
        assertNotEquals(expectedMessageNorth, hospitalWingSouth.toString());
    }

    @Test
    public void equalsTest() {
        HospitalWing hospitalWing = new HospitalWing("south");

        // same object -> returns true
        assertTrue(hospitalWing.equals(hospitalWing));

        // same values -> returns true
        HospitalWing hospitalWingCopy = new HospitalWing("south");
        assertTrue(hospitalWing.equals(hospitalWingCopy));

        // case insensitive -> returns true
        HospitalWing hospitalWingCaseInsenstive = new HospitalWing("SOUTH");
        assertTrue(hospitalWing.equals(hospitalWingCaseInsenstive));

        // different types -> returns false
        assertFalse(hospitalWing.equals(1));

        // null -> returns false
        assertFalse(hospitalWing.equals(null));

        // different hospital wing -> returns false
        HospitalWing differentHospitalWing = new HospitalWing("east");
        assertFalse(hospitalWing.equals(differentHospitalWing));
    }

    @Test
    public void hashCodeTest() {
        HospitalWing hospitalWing1 = new HospitalWing("south");
        HospitalWing hospitalWing2 = new HospitalWing("south");
        HospitalWing hospitalWing3 = new HospitalWing("SOuth");
        HospitalWing hospitalWing4 = new HospitalWing("east");

        // same object -> same hashcode
        assertEquals(hospitalWing1.hashCode(), hospitalWing1.hashCode());

        // same values -> same hashcode
        assertEquals(hospitalWing1.hashCode(), hospitalWing2.hashCode());

        // same values (case insensitive)-> same hashcode
        assertEquals(hospitalWing1.hashCode(), hospitalWing3.hashCode());

        // different values -> different hashcode
        assertNotEquals(hospitalWing1.hashCode(), hospitalWing4.hashCode());
        assertNotEquals(hospitalWing3.hashCode(), hospitalWing4.hashCode());
    }
}
