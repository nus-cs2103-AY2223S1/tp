package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class LocationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidLocation_throwsIllegalArgumentException() {
        String invalidName = " ";
        assertThrows(IllegalArgumentException.class, () -> new Location(invalidName));
    }

    @Test
    public void isValidLocation() {
        // null location
        assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        // invalid location
        assertFalse(Location.isValidLocation(" ")); // spaces only
        assertFalse(Location.isValidLocation("")); // empty string

        // valid location
        assertTrue(Location.isValidLocation("NUS")); // alphabets only
        assertTrue(Location.isValidLocation("12345")); // numbers only
        assertTrue(Location.isValidLocation("LT4")); // alphanumeric characters
        assertTrue(Location.isValidLocation("UTown Residences, 5th floor")); // long location with comma
    }

    @Test
    public void equals() {
        Location locA = new Location("a");
        Location locB = new Location("b");
        Location locA2 = new Location("a");

        // equals
        assertEquals(locA, locA);
        assertEquals(locA, locA2);
        assertEquals(locB, locB);

        // not equals
        assertNotEquals(locA, locB);
    }

    @Test
    public void toStringTest() {
        Location locA = new Location("SOC 1st floor toilet");
        assertEquals(locA.toString(), "SOC 1st floor toilet");
    }

}