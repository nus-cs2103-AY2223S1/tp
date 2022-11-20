package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.DateTimeParser.MESSAGE_INVALID_DATE_TIME_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.reminder.DateTime;

class DateTimeParserTest {
    private DateTimeParser parser = new DateTimeParser();

    @Test
    public void parseDateTime_invalidAndNullInput_exceptions() throws ParseException {
        assertThrows(NullPointerException.class, () -> parser.parseDateTime(null));

        ParseException invalidException = assertThrows(ParseException.class, ()
                -> parser.parseDateTime("somethingNotADate"));
        assertEquals(invalidException.getMessage(), MESSAGE_INVALID_DATE_TIME_FORMAT);
    }

    @Test
    public void parseDateTime_validDateTime_success() throws ParseException {
        assertEquals(parser.parseDateTime("22-1-1 5:30 AM"), new DateTime("2022-01-01 at 05:30"));
        assertEquals(parser.parseDateTime("22-Jan-1 7:30"), new DateTime("2022-01-01 at 07:30"));
        assertEquals(parser.parseDateTime("2022-12-5 4:20"), new DateTime("2022-12-05 at 04:20"));
        assertEquals(parser.parseDateTime("2023-Oct-1 6:00"), new DateTime("2023-10-01 at 06:00"));
        assertEquals(parser.parseDateTime("25-7-1 8:00 PM"), new DateTime("2025-07-01 at 20:00"));
        assertEquals(parser.parseDateTime("24-Feb-3 9:00 AM"), new DateTime("2024-02-03 at 09:00"));
        assertEquals(parser.parseDateTime("2022-10-1 3:17 AM"), new DateTime("2022-10-01 at 03:17"));
        assertEquals(parser.parseDateTime("2023-May-5 6:30 PM"), new DateTime("2023-05-05 at 18:30"));
        assertEquals(parser.parseDateTime("30-1-4 7:33AM"), new DateTime("2030-01-04 at 07:33"));
        assertEquals(parser.parseDateTime("24-Feb-3 9:00AM"), new DateTime("2024-02-03 at 09:00"));
        assertEquals(parser.parseDateTime("2022-10-1 3:17AM"), new DateTime("2022-10-01 at 03:17"));
        assertEquals(parser.parseDateTime("2023-May-5 6:30PM"), new DateTime("2023-05-05 at 18:30"));
    }
}
