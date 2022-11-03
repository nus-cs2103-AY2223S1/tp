package seedu.address.model.commons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class VenueTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Venue(null));
    }

    @Test
    public void constructor_invalidVenue_throwsIllegalArgumentException() {
        String invalidVenue = "";
        assertThrows(IllegalArgumentException.class, () -> new Venue(invalidVenue));
    }

    @Test
    public void isValidName() {
        // null venue
        assertThrows(NullPointerException.class, () -> Venue.isValidVenue(null));

        // invalid venue
        assertFalse(Venue.isValidVenue("")); // empty string
        assertFalse(Venue.isValidVenue(" ")); // spaces only
        assertTrue(Venue.isValidVenue("^")); // only non-alphanumeric characters
        assertTrue(Venue.isValidVenue("peter*")); // contains non-alphanumeric characters

        // valid venue
        assertTrue(Venue.isValidVenue("peter jack")); // alphabets only
        assertTrue(Venue.isValidVenue("12345")); // numbers only
        assertTrue(Venue.isValidVenue("peter the 2nd")); // alphanumeric characters
        assertTrue(Venue.isValidVenue("Capital Tan")); // with capital letters
        assertTrue(Venue.isValidVenue("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
