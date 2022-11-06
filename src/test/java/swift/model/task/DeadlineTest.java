package swift.model.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.testutil.Assert.assertThrows;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "invalid";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline_invalidDeadline_false() {
        // null name
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadline
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("milk")); // alphabets only
        assertFalse(Deadline.isValidDeadline("12345")); // numbers only
    }

    @Test
    public void isValidDeadline_validDeadline_true() {
        assertTrue(Deadline.isValidDeadline("12-12-2001 1200"));
        assertTrue(Deadline.isValidDeadline("06-05-2020 2359"));
    }

    @Test
    public void equals_sameObject_true() {
        Deadline deadline = new Deadline("12-12-2001 1200");
        assertEquals(deadline, deadline);
    }

    @Test
    public void equals_sameDeadline_true() {
        assertEquals(new Deadline("12-12-2001 1200"), new Deadline("12-12-2001 1200"));
    }

    @Test
    public void equals_differentDeadline_false() {
        assertNotEquals(new Deadline("12-12-2001 1200"), new Deadline("13-05-2020 2359"));
    }
}
