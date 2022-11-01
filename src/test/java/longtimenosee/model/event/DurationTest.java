package longtimenosee.model.event;

import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Duration(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidDuration = "";
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDuration));
    }

    @Test
    public void isValidDuration() {
        // null times
        assertThrows(NullPointerException.class, () -> Duration.isValidStartAndEnd(null));
        assertThrows(NullPointerException.class, () -> Duration.isValidTime(null));
    }

    /**
     * Tests if start time is before end time (in format HH:mm__HH:mm)
     */
    @Test
    public void isValidStartandEnd() {
        assertFalse(Duration.isValidStartAndEnd("13:00__12:00"));
        assertFalse(Duration.isValidStartAndEnd("13:01__13:00"));
        assertFalse(Duration.isValidStartAndEnd("13:00__13:00"));
        assertFalse(Duration.isValidStartAndEnd("23:00__01:00"));

        assertTrue(Duration.isValidStartAndEnd("13:00__13:01"));
        assertTrue(Duration.isValidStartAndEnd("20:00__21:00"));
    }

    /**
     * Tests if input given is in HH:mm format
     */
    @Test
    public void isValidTime() {
        assertFalse((Duration.isValidTime("25:00")));
        assertFalse(Duration.isValidTime("-10:00"));
        assertFalse(Duration.isValidTime("AB:CC"));
        assertFalse(Duration.isValidTime("20-00"));
        assertFalse(Duration.isValidTime("20 00"));

        assertTrue(Duration.isValidTime("20:00"));
        assertTrue(Duration.isValidTime("01:00"));
        assertTrue(Duration.isValidTime("00:00"));


    }

    /**
     * Tests if 2 different events have overlapping times, given that both are in the right format
     */
    @Test
    public void isOverlap() {
        Duration d1 = new Duration("09:00__17:00");
        Duration d2 = new Duration("12:00__13:00");
        Duration d3 = new Duration("09:00__09:01");
        Duration d4 = new Duration("19:00__21:00");
        Duration d5 = new Duration("15:00__17:00");
        Duration d6 = new Duration("20:00__21:00");
        Duration d7 = new Duration("21:00__22:00");

        assertFalse(d1.isOverlap(d6));
        assertFalse(d6.isOverlap(d1));
        assertFalse(d2.isOverlap(d5));
        assertFalse(d5.isOverlap(d2));
        assertFalse(d4.isOverlap(d1));

        assertTrue(d6.isOverlap(d7)); // 8-9pm and 9-10pm considered overlap, stated in UG
        assertTrue(d1.isOverlap(d2));
        assertTrue(d2.isOverlap(d1));
        assertTrue(d1.isOverlap(d3));
        assertTrue(d3.isOverlap(d1));


    }

}
