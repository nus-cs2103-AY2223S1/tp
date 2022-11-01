package seedu.address.model.datetime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatetimeTest {

    @Test
    public void validDatetime_success() {
        assertTrue(Datetime.isValidDatetime("2023-02-02 15:00"));
    }

    @Test
    public void isValidDatetime_failure() {
        //invalid time -> false
        assertFalse(Datetime.isValidDatetime("2023-02-02 15:60"));

        //invalid date -> false
        assertFalse(Datetime.isValidDatetime("2023-13-13:"));

        //invalid time format -> false
        assertFalse(Datetime.isValidDatetime("2023-02-02 15:"));

        //invalid date format -> false
        assertFalse(Datetime.isValidDatetime("2023-02- 15:"));
    }

    @Test
    public void datetimeFormatted_success() {
        Datetime datetime = Datetime.fromFormattedString("2023-02-02 15:00");
        
        assertEquals("2023-02-02 15:00", datetime.toFormatted());
    }
}
