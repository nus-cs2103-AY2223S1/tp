package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Duration(null));
    }

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        String invalidDuration = "";
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDuration));
    }

    @Test
    public void isValidDuration() {
        // null name
        assertThrows(NullPointerException.class, () -> Duration.isValidDuration(null));

        // invalid duration
        assertFalse(Duration.isValidDuration("")); // empty string
        assertFalse(Duration.isValidDuration(" ")); // spaces only
        assertFalse(Duration.isValidDuration("08:30-09:0X")); // contains alphabetic character
        assertFalse(Duration.isValidDuration("08:30-09:0*")); // contains non-alphanumeric characters

        // valid duration
        assertTrue(Duration.isValidDuration("08:00-09:00")); // valid HH-mm
        assertTrue(Duration.isValidDuration("08:30-12:00")); // valid HH-mm
        assertTrue(Duration.isValidDuration("09:00-11:00")); // valid HH-mm
    }
}
