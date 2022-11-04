package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HospitalWingTest {

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
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing("SOU TH")); // enum values with unexpected character in between
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
        HospitalWing HospitalWingSouth = new HospitalWing("south");
        HospitalWing HospitalWingNorth = new HospitalWing("north");

        // same hospital wing
        assertEquals(expectedMessageForSouth, HospitalWingSouth.toString());
        assertEquals(expectedMessageNorth, HospitalWingNorth.toString());

        // different hospital wing
        assertNotEquals(expectedMessageForSouth, HospitalWingNorth.toString());
        assertNotEquals(expectedMessageNorth, HospitalWingSouth.toString());
    }

    @Test
    public void enumToStringTest() {
        String south = "South" ;
        String north = "North" ;
        String east = "East" ;
        String west = "West" ;

        assertEquals(south, HospitalWing.HospitalWingTypes.SOUTH.toString());
        assertEquals(north, HospitalWing.HospitalWingTypes.NORTH.toString());
        assertEquals(east, HospitalWing.HospitalWingTypes.EAST.toString());
        assertEquals(west, HospitalWing.HospitalWingTypes.WEST.toString());
    }

    @Test
    public void equalsTest() {
        HospitalWing HospitalWing = new HospitalWing("south");

        // same object -> returns true
        assertTrue(HospitalWing.equals(HospitalWing));

        // same values -> returns true
        HospitalWing HospitalWingCopy = new HospitalWing("south");
        assertTrue(HospitalWing.equals(HospitalWingCopy));

        // case insensitive -> returns true
        HospitalWing HospitalWingCaseInsenstive = new HospitalWing("SOUTH");
        assertTrue(HospitalWing.equals(HospitalWingCaseInsenstive));

        // different types -> returns false
        assertFalse(HospitalWing.equals(1));

        // null -> returns false
        assertFalse(HospitalWing.equals(null));

        // different hospital wing -> returns false
        HospitalWing differentHospitalWing = new HospitalWing("east");
        assertFalse(HospitalWing.equals(differentHospitalWing));
    }

    @Test
    public void hashCodeTest() {
        HospitalWing HospitalWing1 = new HospitalWing("south");
        HospitalWing HospitalWing2 = new HospitalWing("south");
        HospitalWing HospitalWing3 = new HospitalWing("SOuth");
        HospitalWing HospitalWing4 = new HospitalWing("east");

        // same object -> same hashcode
        assertEquals(HospitalWing1.hashCode(), HospitalWing1.hashCode());

        // same values -> same hashcode
        assertEquals(HospitalWing1.hashCode(), HospitalWing2.hashCode());

        // same values (case insensitive)-> same hashcode
        assertEquals(HospitalWing1.hashCode(), HospitalWing3.hashCode());

        // different values -> different hashcode
        assertNotEquals(HospitalWing1.hashCode(), HospitalWing4.hashCode());
        assertNotEquals(HospitalWing3.hashCode(), HospitalWing4.hashCode());
    }
}
