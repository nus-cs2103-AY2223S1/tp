package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline((invalidDate)));
    }

    @Test
    void isValidDeadline() {
        // null date
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid date
        assertFalse(Deadline.isValidDeadline("")); // empty date
        assertFalse(Deadline.isValidDeadline("13/3/2022")); // DD/MM/YYYY
        assertFalse(Deadline.isValidDeadline("13022021")); // DDMMYYYY
        assertFalse(Deadline.isValidDeadline("2031-01-5")); // day and month should be two digit
        assertFalse(Deadline.isValidDeadline("2031-06-31")); // wrong date
        assertFalse(Deadline.isValidDeadline("2031-13-01")); // wrong month
        assertTrue(Deadline.isValidDeadline("2000-02-29")); // not leap year

        // valid date
        assertTrue(Deadline.isValidDeadline("2022-12-15"));
        assertTrue(Deadline.isValidDeadline("2020-02-29")); // leap year
        assertTrue(Deadline.isValidDeadline("2031-01-01")); // leap year
    }

    @Test
    void compareTo() {
        // same date
        Deadline x = new Deadline("2022-10-15");
        Deadline y = new Deadline("2022-10-15");
        assertEquals(0, x.compareTo(x));
        assertEquals(0, x.compareTo(y));

        // before
        assertTrue(x.compareTo(new Deadline("2022-12-05")) < 0);
        assertTrue(x.compareTo(new Deadline("2022-10-30")) < 0);
        assertTrue(x.compareTo(new Deadline("2023-02-05")) < 0);

        // after
        assertTrue(new Deadline("2022-12-05").compareTo(x) > 0);
        assertTrue(new Deadline("2022-10-30").compareTo(x) > 0);
        assertTrue(new Deadline("2023-02-05").compareTo(x) > 0);
    }
}
