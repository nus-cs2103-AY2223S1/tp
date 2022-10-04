package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> seedu.address.model.project.Deadline.isValidDeadline(null));

        // invalid deadline
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("Testing")); // string of text
        assertFalse(Deadline.isValidDeadline("27 October 2022")); // date in full

        // valid deadline
        assertTrue(Deadline.isValidDeadline("2022-11-13")); // date in yyyy-mm-dd
        assertTrue(Deadline.isValidDeadline("2022-3-5")); // date in yyyy-m-d
    }
}