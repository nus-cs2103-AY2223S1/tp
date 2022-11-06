package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StartTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StartTime(null));
    }

    @Test
    public void constructor_invalidStartTime_throwsIllegalArgumentException() {
        String invalidStartTime = "";
        assertThrows(IllegalArgumentException.class, () -> new StartTime(invalidStartTime));
    }

    @Test
    public void equals_sameStartTime_success() {
        StartTime timeA = new StartTime("00:00");
        StartTime timeB = new StartTime("00:00");
        assertTrue(timeA.equals(timeB));
    }

    @Test
    public void equals_differentStartTime_fail() {
        StartTime timeA = new StartTime("00:00");
        StartTime timeB = new StartTime("00:01");
        assertFalse(timeA.equals(timeB));
    }

    @Test
    public void compareTo_sameStartTime_success() {
        StartTime timeA = new StartTime("00:00");
        StartTime timeB = new StartTime("00:00");
        assertTrue(timeA.compareTo(timeB) == 0);
    }

    @Test
    public void compareTo_sameStartTime_fail() {
        StartTime timeA = new StartTime("00:00");
        StartTime timeB = new StartTime("00:01");
        assertFalse(timeA.compareTo(timeB) == 0);
    }

    @Test
    public void compareTo_olderStartTime_success() {
        StartTime timeA = new StartTime("00:00");
        StartTime timeB = new StartTime("00:01");
        assertTrue(timeA.compareTo(timeB) < 0);
    }

    @Test
    public void compareTo_olderStartTime_fail() {
        StartTime timeA = new StartTime("01:00");
        StartTime timeB = new StartTime("00:01");
        assertFalse(timeA.compareTo(timeB) < 0);
    }

    @Test
    public void compareTo_earlierStartTime_success() {
        StartTime timeA = new StartTime("01:00");
        StartTime timeB = new StartTime("00:01");
        assertTrue(timeA.compareTo(timeB) > 0);
    }

    @Test
    public void compareTo_earlierStartTime_fail() {
        StartTime timeA = new StartTime("00:00");
        StartTime timeB = new StartTime("00:01");
        assertFalse(timeA.compareTo(timeB) > 0);
    }

    @Test
    public void isValidStartTimeFormatAndValue() {

        // null StartTime
        assertThrows(NullPointerException.class, () -> StartTime.isValidStartTime(null));

        // blank StartTime
        assertFalse(StartTime.isValidStartTime("")); // empty string
        assertFalse(StartTime.isValidStartTime(" ")); // spaces only
        assertFalse(StartTime.isValidStartTime("      ")); // many spaces
        assertFalse(StartTime.isValidStartTime(":"));

        // Time 00:00 corner case
        assertFalse(StartTime.isValidStartTime("24:00"));
        assertTrue(StartTime.isValidStartTime("00:00"));

        // not supposed to have alphabetical or other characters
        assertFalse(StartTime.isValidStartTime("01:A0"));
        assertFalse(StartTime.isValidStartTime("0A:10"));
        assertFalse(StartTime.isValidStartTime("0A:#0"));
        assertFalse(StartTime.isValidStartTime("0$:00"));

        // wrong format
        assertFalse(StartTime.isValidStartTime("10/10"));
        assertFalse(StartTime.isValidStartTime("10 10"));
        assertFalse(StartTime.isValidStartTime("10:0"));
        assertFalse(StartTime.isValidStartTime("0:10"));
        assertFalse(StartTime.isValidStartTime(":10"));
        assertFalse(StartTime.isValidStartTime("10:"));
        assertFalse(StartTime.isValidStartTime("12AM"));
        assertFalse(StartTime.isValidStartTime("12/00/AM"));
        assertFalse(StartTime.isValidStartTime("12/00/PM"));

        //out of range
        assertFalse(StartTime.isValidStartTime("23:61"));
        assertFalse(StartTime.isValidStartTime("00:61"));
        assertFalse(StartTime.isValidStartTime("25:00"));
        assertFalse(StartTime.isValidStartTime("99:10"));
        assertFalse(StartTime.isValidStartTime("10:99"));
        assertFalse(StartTime.isValidStartTime("24:01"));

        // valid StartTime
        assertTrue(StartTime.isValidStartTime("14:00")); //UG example
        assertTrue(StartTime.isValidStartTime("10:40")); //UG example
        assertTrue(StartTime.isValidStartTime("10:10")); //UG example
    }
}
