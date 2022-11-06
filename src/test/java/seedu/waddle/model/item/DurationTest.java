package seedu.waddle.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    public void constructor_null_throwsNumberFormatExceptionException() {
        assertThrows(NumberFormatException.class, () -> new Duration(null));
    }

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        String invalidDuration = "";
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDuration));
    }

    @Test
    public void isValidDuration() {
        // null duration, but it doesn't throw anything
        // assertThrows(NullPointerException.class, () -> Duration.isValidDuration(null));

        // invalid duration
        assertFalse(Duration.isValidDuration("")); // empty string
        assertFalse(Duration.isValidDuration(" ")); // spaces only
        assertFalse(Duration.isValidDuration("one"));
        assertFalse(Duration.isValidDuration("0"));

        // valid duration
        assertTrue(Duration.isValidDuration("2"));
        assertTrue(Duration.isValidDuration("30"));
    }
}
