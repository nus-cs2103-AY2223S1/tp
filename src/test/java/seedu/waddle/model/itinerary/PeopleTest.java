package seedu.waddle.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PeopleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new People(null));
    }

    @Test
    public void constructor_invalidPeople_throwsIllegalArgumentException() {
        String invalidPeople = "";
        assertThrows(IllegalArgumentException.class, () -> new People(invalidPeople));
    }

    @Test
    public void isValidPeople() {
        // null people
        assertThrows(NullPointerException.class, () -> People.isValidPeople(null));

        // invalid people
        assertFalse(People.isValidPeople("")); // empty string
        assertFalse(People.isValidPeople(" ")); // spaces only
        assertFalse(People.isValidPeople("one"));
        assertFalse(People.isValidPeople("seven"));

        // valid people
        assertTrue(People.isValidPeople("2"));
        assertTrue(People.isValidPeople("19"));
    }
}
