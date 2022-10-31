package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(DateTimeParseException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null address
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid addresses
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("15 June 2015")); // spaces only
        assertFalse(Deadline.isValidDeadline("03/03/2004")); // spaces only
        assertFalse(Deadline.isValidDeadline("4th August 2000")); // spaces only
        assertFalse(Deadline.isValidDeadline("32/01/2020")); // spaces only

        // valid addresses
        assertTrue(Deadline.isValidDeadline("03-03-1900"));
        assertTrue(Deadline.isValidDeadline("17-06-2000")); // one character
        assertTrue(Deadline.isValidDeadline("28-06-2000")); // long address
    }
}
