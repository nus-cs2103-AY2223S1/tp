package seedu.address.model.datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DatetimeRangeTest {

    DatetimeRange datetimeRange = DatetimeRange.fromFormattedString("2023-02-02","15:00", "17:00");
    
    @Test
    public void getValuesDatetimeRange_success() {
        assertEquals("2023-02-02 15:00", datetimeRange.getStartDatetimeFormatted());

        assertEquals("2023-02-02 17:00", datetimeRange.getEndDatetimeFormatted());
    }

    @Test
    public void toString_DatetimeRange_success() {
        assertEquals("2023 Feb 02, 15:00 to 17:00", datetimeRange.toString());
    }
}
