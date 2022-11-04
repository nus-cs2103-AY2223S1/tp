package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;



public class DateTimeParserTest {

    public static final String FIRST_VALID_DATE_TIME = "01-04-2023 12:30";
    public static final String SECOND_VALID_DATE_TIME = "02-04-2023 12:30";
    public static final String THIRD_VALID_DATE_TIME = "03-04-2023 12:30";
    private static final String INVALID_YEAR = "01-04-23 12:30";
    private static final String INVALID_MONTH = "01-13-2023 12:30";
    private static final String INVALID_DAY = "1000-04-2023 12:30";
    private static final String INVALID_DAY_ERROR_MESSAGE = "Text '1000-04-2023 12:30' could not be parsed: "
            + "Invalid value for DayOfMonth (valid values 1 - 28/31): 1000";
    private static final String INVALID_MONTH_ERROR_MESSAGE =
            "Text '01-13-2023 12:30' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 13";
    private static final String INVALID_YEAR_ERROR_MESSAGE = "Text '01-04-23 12:30' could not be parsed at index 6";
    private DateTimeParser parser = new DateTimeParser();


    @Test
    public void parse_dateTimeCorrectFormat_success() {
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 4, 1, 12, 30);
        LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(FIRST_VALID_DATE_TIME);
        assertEquals(expectedLocalDateTime, parsedLocalDateTime);
    }

    @Test
    public void parse_dateTimeInvalidDayOfMonthFormat_exceptionThrown() {
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(INVALID_DAY);
        } catch (DateTimeException e) {
            assertEquals(INVALID_DAY_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void parse_dateTimeInvalidMonthFormat_exceptionThrown() {
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(INVALID_MONTH);
        } catch (DateTimeException e) {
            assertEquals(INVALID_MONTH_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void parse_dateTimeInvalidYearFormat_exceptionThrown() {
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(INVALID_YEAR);
        } catch (DateTimeException e) {
            assertEquals(INVALID_YEAR_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void isValidDateTime_success() {
        assertTrue(DateTimeParser.isValidDateTime(FIRST_VALID_DATE_TIME));
    }


    @Test
    public void isValidDateTime_failure() {
        assertFalse(DateTimeParser.isValidDateTime(INVALID_DAY));
    }
}
