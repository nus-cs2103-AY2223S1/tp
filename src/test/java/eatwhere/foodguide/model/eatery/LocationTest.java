package eatwhere.foodguide.model.eatery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.testutil.Assert;

public class LocationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Location(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        // invalid addresses
        assertFalse(Location.isValidLocation("")); // empty string
        assertFalse(Location.isValidLocation(" ")); // spaces only

        // valid addresses
        assertTrue(Location.isValidLocation("Blk 456, Den Road, #01-355"));
        assertTrue(Location.isValidLocation("-")); // one character
        assertTrue(Location.isValidLocation("Leng Inc; 1234 Market St; "
                + "San Francisco CA 2349879; USA")); // long location
    }

    @Test
    public void isSimilarLocation() {
        Location baseLocation = new Location("Location");

        assertTrue(baseLocation.similarTo(baseLocation)); // same object
        assertTrue(baseLocation.similarTo(new Location("Location"))); // same spelling
        assertTrue(baseLocation.similarTo(new Location("location"))); // all lowercase
        assertTrue(baseLocation.similarTo(new Location("LOCATION"))); // all uppercase

        assertFalse(baseLocation.similarTo(new Location("Location "))); // extra trailing whitespace
        assertFalse(baseLocation.similarTo(new Location("Locationn"))); // different spelling
    }
}
