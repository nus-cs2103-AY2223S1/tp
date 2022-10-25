package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HospitalWingTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HospitalWing(null));
    }

    @Test
    public void constructor_invalidHospitalWing_throwsIllegalArgumentException() {
        String invalidHospitalWing = "";
        assertThrows(IllegalArgumentException.class, () -> new HospitalWing(invalidHospitalWing));
    }

    @Test
    public void isValidHospitalWing() {
        // null HospitalWing
        assertThrows(NullPointerException.class, () -> HospitalWing.isValidHospitalWing(null));

        // blank HospitalWing
        assertFalse(HospitalWing.isValidHospitalWing("")); // empty string
        assertFalse(HospitalWing.isValidHospitalWing(" ")); // spaces only

        //invalid inputs
        assertFalse(HospitalWing.isValidHospitalWing("1")); // numbers
        assertFalse(HospitalWing.isValidHospitalWing("@@")); // symbols
        assertFalse(HospitalWing.isValidHospitalWing("me")); // strings which is not part of the enum
        assertFalse(HospitalWing.isValidHospitalWing("NORTH--")); // invalid domain name
        assertFalse(HospitalWing.isValidHospitalWing("SOU  TH")); // strings with spaces in between
        assertFalse(HospitalWing.isValidHospitalWing("WESTNORTH")); // combination of two enum strings
        assertFalse(HospitalWing.isValidHospitalWing("WEST NORTH")); // combination of two enum strings


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
    public void isValidEnum() {
        assertAll(() -> assertEquals("east", HospitalWing.HospitalWingTypes.EAST.toString()), (
                ) -> assertEquals("west", HospitalWing.HospitalWingTypes.WEST.toString()), (
                        ) -> assertEquals("north", HospitalWing.HospitalWingTypes.NORTH.toString()), (
                                ) -> assertEquals("south", HospitalWing.HospitalWingTypes.SOUTH.toString()));
    }
}
