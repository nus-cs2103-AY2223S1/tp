package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void isValidEnum() {
        assertAll(() -> assertEquals("EAST", HospitalWing.HospitalWingTypes.EAST.name()),
                () -> assertEquals("WEST", HospitalWing.HospitalWingTypes.WEST.name()),
                        () -> assertEquals("NORTH", HospitalWing.HospitalWingTypes.NORTH.name()),
                                () -> assertEquals("SOUTH", HospitalWing.HospitalWingTypes.SOUTH.name()));
    }
}
