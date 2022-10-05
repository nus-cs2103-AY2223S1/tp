package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {
    private static final DateTime EARLIER_TIME = new DateTime("2022-10-05T10:30:15.0000000");
    private static final DateTime LATER_TIME = new DateTime("2022-10-05T10:50:15.0000000");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void isAfter() {
        assertTrue(LATER_TIME.isAfter(EARLIER_TIME));
    }

    @Test
    public void isOnSameDay() {
        assertTrue(LATER_TIME.isOnSameDay(EARLIER_TIME));
    }
}
