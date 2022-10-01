package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateTimeParserTest {
    private static final String INVALID_YEAR = "1-Apr-23 12:30 PM";
    private static final String INVALID_MONTH = "1-January-2023 12:30 PM";
    private static final String INVALID_DAY = "1000-Apr-2023 12:30 PM";
    private static final String INVALID_DAY_ERROR_MESSAGE = "Text '1000-Apr-2023 12:30 PM' could not be parsed: "
            + "Invalid value for DayOfMonth (valid values 1 - 28/31): 1000";
    private static final String INVALID_MONTH_ERROR_MESSAGE = "Text '1-January-2023 12:30 PM' could not be parsed at "
            + "index 5";
    private static final String INVALID_YEAR_ERROR_MESSAGE = "Text '1-Apr-23 12:30 PM' could not be parsed at index 6";
    private DateTimeParser parser = new DateTimeParser();

    @Test
    public void parse_dateTimeCorrectFormat_success() {
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 4, 1, 12, 30);
        LocalDateTime parsedLocalDateTime = parser.getLocalDateTimeFromString("1-Apr-2023 12:30 PM");
        assertEquals(expectedLocalDateTime, parsedLocalDateTime);
    }

    @Test
    public void parse_dateTimeInvalidDayOfMonthFormat_exceptionThrown() {
        try {
            LocalDateTime parsedLocalDateTime = parser.getLocalDateTimeFromString(INVALID_DAY);
        } catch (Exception e) {
            assertEquals(INVALID_DAY_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void parse_dateTimeInvalidMonthFormat_exceptionThrown() {
        try {
            LocalDateTime parsedLocalDateTime = parser.getLocalDateTimeFromString(INVALID_MONTH);
        } catch (Exception e) {
            assertEquals(INVALID_MONTH_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void parse_dateTimeInvalidYearFormat_exceptionThrown() {
        try {
            LocalDateTime parsedLocalDateTime = parser.getLocalDateTimeFromString(INVALID_YEAR);
        } catch (Exception e) {
            assertEquals(INVALID_YEAR_ERROR_MESSAGE, e.getMessage());
        }
    }
}
