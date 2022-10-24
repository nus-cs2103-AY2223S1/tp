package seedu.application.model.application.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidLocation_throwsIllegalArgumentException() {
        String invalidLocation = "";
        assertThrows(IllegalArgumentException.class, () -> new Location(invalidLocation));
    }

    @Test
    public void isValidLocation() {
        // null location
        assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        // invalid location
        assertFalse(Location.isValidLocation("")); // empty string
        assertFalse(Location.isValidLocation("    ")); // spaces only

        // valid location
        assertTrue(Location.isValidLocation("zoom")); // alphabets only
        assertTrue(Location.isValidLocation("12345")); // numbers only
        assertTrue(Location.isValidLocation("skype channel 1")); // alphanumeric characters
        assertTrue(Location.isValidLocation("Microsoft Teams")); // with capital letters
        assertTrue(Location.isValidLocation("23, Kallang Way 78, #03-14, 116789"));
        // contains non-alphanumeric characters
        assertTrue(Location.isValidLocation("69, Central Marina Square Building 1, #09-66, 119765"));
        // long location name
    }

    @Test
    public void round() {
        assertEquals(new Location("23, Kallang Way 78, #03-14, 116789").toString(),
                "23, Kallang Way 78, #03-14, 116789");
    }

}
