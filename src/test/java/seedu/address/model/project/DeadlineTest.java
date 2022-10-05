package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    //    @Test
    //    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
    //        String invalidDeadline = "";
    //        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    //    }

    @Test
    public void isValidDeadline() {
        //        // null email
        //        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));
        //
        //        // blank email
        //        assertFalse(Deadline.isValidDeadline("")); // empty string
        //        assertFalse(Deadline.isValidDeadline(" ")); // spaces only

        //        // missing parts
        //        assertFalse(Deadline.isValidDeadline("2022-12")); // missing day part
        //        assertFalse(Deadline.isValidDeadline("2022-15")); // missing month part
        //        assertFalse(Deadline.isValidDeadline("12-20")); // missing year part

        // invalid parts
        // invalid month
        // invalid day

        // valid email
        assertTrue(Deadline.isValidDeadline("2022-12-31")); // underscore in local part
    }
}
