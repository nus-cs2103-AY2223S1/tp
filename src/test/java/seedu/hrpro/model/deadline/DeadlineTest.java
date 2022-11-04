package seedu.hrpro.model.deadline;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
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
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // blank deadline
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only

        // missing deadline parts
        assertFalse(Deadline.isValidDeadline("2022-12")); // missing day part
        assertFalse(Deadline.isValidDeadline("2022-15")); // missing month part
        assertFalse(Deadline.isValidDeadline("12-20")); // missing year part

        // invalid parts
        assertFalse(Deadline.isValidDeadline("2022-12-32")); // invalid day
        assertFalse(Deadline.isValidDeadline("2012-20-10")); // invalid month
        assertFalse(Deadline.isValidDeadline("2023-02-29")); //Invalid leap year

        // valid deadline
        assertTrue(Deadline.isValidDeadline("2022-12-31")); // underscore in local part
        assertTrue(Deadline.isValidDeadline("2024-02-29")); //valid leap year
    }
}
