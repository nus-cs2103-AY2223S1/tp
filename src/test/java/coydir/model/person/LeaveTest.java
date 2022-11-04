package coydir.model.person;

import static coydir.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class LeaveTest {
    private Leave leave1 = new Leave("02-01-2022", "05-01-2022");
    private Leave leave2 = new Leave("04-01-2022", "08-01-2022");
    private Leave leave3 = new Leave("01-03-2022", "31-03-2022");
    private Leave leave4 = new Leave("01-03-2022", "01-04-2022");
    private Leave leave5 = new Leave("01-01-2022", "01-01-2023");
    private Leave leave6 = new Leave("01-01-2022", "31-12-2022");
    private Leave leave7 = new Leave("05-01-2022", "08-01-2022");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Leave(null, null));
    }

    @Test
    public void constructor_invalidLeave_throwsDateTimeParseException() {
        String invalidStartDate = "";
        String invalidEndDate = "";
        assertThrows(DateTimeParseException.class, () -> new Leave(invalidStartDate, invalidEndDate));
    }

    @Test
    public void test_isValidLeave() {
        //startDate and endDate null
        assertThrows(NullPointerException.class, () -> Leave.isValidLeave(null, null));

        //startDate or endDate null
        assertThrows(NullPointerException.class, () -> Leave.isValidLeave(null, "12-12-2000"));
        assertThrows(NullPointerException.class, () -> Leave.isValidLeave("12-12-2000", null));

        // blank Leave
        assertFalse(Leave.isValidLeave("", "")); // empty string
        assertFalse(Leave.isValidLeave(" ", " ")); // spaces only

        //Start Date and/or End Date nonsense values
        assertFalse(Leave.isValidLeave("iojfkd", "jdnjfsnsd"));
        assertFalse(Leave.isValidLeave("12-12-2000", "jdnjfsnsd"));
        assertFalse(Leave.isValidLeave("jdnjfsnsd", "12-12-2000"));

        //Start Date and/or End Date in wrong format
        assertFalse(Leave.isValidLeave("12/12/2000", "13/12/2000"));
        assertFalse(Leave.isValidLeave("2000-12-12", "2000-12-13"));
        assertFalse(Leave.isValidLeave("1-1-2000", "2-1-2000"));

        //Start Date and End Date are valid (following the calendar)
        assertFalse(Leave.isValidLeave("30-04-2000", "32-04-2000")); //day more than 31
        assertFalse(Leave.isValidLeave("30-12-2000", "32-13-2000")); //month more than 12
        assertFalse(Leave.isValidLeave("30-12-2000", "32-12-3000")); //year more than 3000
        assertFalse(Leave.isValidLeave("30-04-2000", "31-04-2000")); //31st April does not exist
        assertFalse(Leave.isValidLeave("27-02-2001", "29-02-2001")); //non-leap year feb

        //Start Date later than End Date
        assertFalse(Leave.isValidLeave("31-04-2000", "30-04-2000"));
        assertFalse(Leave.isValidLeave("30-12-2000", "30-11-2000"));
        assertFalse(Leave.isValidLeave("30-12-2000", "30-12-1999"));

        //Valid Leaves
        assertTrue(Leave.isValidLeave("01-01-2000", "03-01-2000"));
        assertTrue(Leave.isValidLeave("12-01-2000", "15-01-2000"));
        assertTrue(Leave.isValidLeave("18-01-2000", "30-03-2000"));
        assertTrue(Leave.isValidLeave("20-02-2000", "30-10-2000"));
        assertTrue(Leave.isValidLeave("20-02-2000", "30-10-2000"));
        assertTrue(Leave.isValidLeave("20-01-2000", "30-12-2000"));
        assertTrue(Leave.isValidLeave("30-12-1900", "30-12-2999"));
        assertTrue(Leave.isValidLeave("01-01-2000", "01-01-2000")); //same day leave
        assertTrue(Leave.isValidLeave("27-02-2016", "29-02-2016")); //Leap Year feb
    }

    @Test
    public void test_getTotalDays() {
        assertEquals(4, leave1.getTotalDays());
        assertEquals(5, leave2.getTotalDays());
        assertEquals(31, leave3.getTotalDays());
        assertEquals(32, leave4.getTotalDays());
        assertEquals(366, leave5.getTotalDays());
        assertEquals(365, leave6.getTotalDays());
        assertEquals(4, leave7.getTotalDays());
    }

    @Test
    public void test_isOverlapping() {
        assertTrue(leave1.isOverlapping(leave2));
        assertTrue(leave2.isOverlapping(leave1));
        assertTrue(leave1.isOverlapping(leave6));
        assertTrue(leave6.isOverlapping(leave1));
        assertTrue(leave1.isOverlapping(leave1));
        assertTrue(leave1.isOverlapping(leave7));
        assertTrue(leave7.isOverlapping(leave1));
        assertTrue(leave5.isOverlapping(leave6));
        assertTrue(leave6.isOverlapping(leave5));

        assertFalse(leave3.isOverlapping(leave1));
        assertFalse(leave1.isOverlapping(leave3));
        assertFalse(leave3.isOverlapping(leave2));
        assertFalse(leave2.isOverlapping(leave3));
    }
}
