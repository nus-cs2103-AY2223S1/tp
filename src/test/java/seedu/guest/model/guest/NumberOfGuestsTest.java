package seedu.guest.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NumberOfGuestsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NumberOfGuests(null));
    }

    @Test
    public void constructor_invalidNumberOfGuests_throwsIllegalArgumentException() {
        String invalidNumberOfGuests = "";
        assertThrows(IllegalArgumentException.class, () -> new NumberOfGuests(invalidNumberOfGuests));
    }

    @Test
    public void isValidNumberOfGuests() {
        // null number of guests
        assertThrows(NullPointerException.class, () -> NumberOfGuests.isValidNumberOfGuests(null));

        // invalid number of guests
        assertFalse(NumberOfGuests.isValidNumberOfGuests("")); // empty string
        assertFalse(NumberOfGuests.isValidNumberOfGuests(" ")); // spaces only
        assertFalse(NumberOfGuests.isValidNumberOfGuests("-1")); // negative number
        assertFalse(NumberOfGuests.isValidNumberOfGuests("5")); // outside 1 to 4 range

        // valid number of guests
        assertTrue(NumberOfGuests.isValidNumberOfGuests("1")); // range of 1 to 4
        assertTrue(NumberOfGuests.isValidNumberOfGuests("2"));
        assertTrue(NumberOfGuests.isValidNumberOfGuests("3"));
        assertTrue(NumberOfGuests.isValidNumberOfGuests("4"));
    }

    @Test
    public void hashcode() {
        NumberOfGuests tempNumberOfGuests = new NumberOfGuests("4");

        // same values -> return true
        assertEquals(tempNumberOfGuests, new NumberOfGuests("4"));

        // different values -> return false
        assertNotEquals(tempNumberOfGuests, new NumberOfGuests("3"));
    }
}

