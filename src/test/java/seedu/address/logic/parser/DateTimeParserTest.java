package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class DateTimeParserTest {

    public static final String FIRST_VALID_DATE_TIME = "01-04-2023 12:30";

    public static final  String MINUTE_FORMAT = "01-04-2023 12:%s";
    public static final  String HOUR_FORMAT = "01-04-2023 %s:30";
    public static final  String DAY_FORMAT = "%s-04-2023 12:30";
    public static final  String MONTH_FORMAT = "01-%s-2023 12:30";
    public static final  String YEAR_FORMAT = "01-04-%s 12:30";
    private static final String OUTOFBOUNDS_MINUTE = "60";
    private static final String OUTOFBOUNDS_HOUR = "24";
    private static final String OUTOFBOUNDS_DAY = "32";
    private static final String OUTOFBOUNDS_MONTH = "13";

    private static final String OUTOFBOUNDS_MINUTE_INPUT = String.format(MINUTE_FORMAT, OUTOFBOUNDS_MINUTE);
    private static final String OUTOFBOUNDS_HOUR_INPUT = String.format(HOUR_FORMAT, OUTOFBOUNDS_HOUR);
    private static final String OUTOFBOUNDS_DAY_INPUT= String.format(DAY_FORMAT, OUTOFBOUNDS_DAY);
    private static final String OUTOFBOUNDS_MONTH_INPUT = String.format(MONTH_FORMAT, OUTOFBOUNDS_MONTH);
    public static final String OUTOFBOUNDS_FORMAT = "Text '%s' could not be parsed: Invalid value for %s (valid values %s): %s";
    private static final String WRONGLENGTH_MINUTE_1_INPUT = String.format(MINUTE_FORMAT, "2");
    private static final String WRONGLENGTH_MINUTE_2_INPUT = String.format(MINUTE_FORMAT, "060");
    private static final String WRONGLENGTH_HOUR_1_INPUT = String.format(HOUR_FORMAT, "2");
    private static final String WRONGLENGTH_HOUR_2_INPUT = String.format(HOUR_FORMAT, "020");
    private static final String WRONGLENGTH_YEAR_1_INPUT = String.format(YEAR_FORMAT, 23);
    private static final String WRONGLENGTH_YEAR_2_INPUT = String.format(YEAR_FORMAT, 23000);

    private static final String OUTOFBOUNDS_MINUTE_ERROR_MESSAGE = String.format(OUTOFBOUNDS_FORMAT, OUTOFBOUNDS_MINUTE_INPUT, "MinuteOfHour", "0 - 59", OUTOFBOUNDS_MINUTE);
    private static final String OUTOFBOUNDS_HOUR_ERROR_MESSAGE = String.format(OUTOFBOUNDS_FORMAT, OUTOFBOUNDS_HOUR_INPUT, "HourOfDay", "0 - 23", OUTOFBOUNDS_HOUR);
    private static final String OUTOFBOUNDS_DAY_ERROR_MESSAGE = String.format(OUTOFBOUNDS_FORMAT, OUTOFBOUNDS_DAY_INPUT, "DayOfMonth", "1 - 28/31", OUTOFBOUNDS_DAY);
    private static final String OUTOFBOUNDS_MONTH_ERROR_MESSAGE = String.format(OUTOFBOUNDS_FORMAT, OUTOFBOUNDS_MONTH_INPUT, "MonthOfYear", "1 - 12", OUTOFBOUNDS_MONTH);
    private static final String WRONGLENGTH_MINUTE_ERROR_MESSAGE_1 = "Text '" + WRONGLENGTH_MINUTE_1_INPUT + "' could not be parsed at index 14";
    private static final String WRONGLENGTH_MINUTE_ERROR_MESSAGE_2 = "Text '" + WRONGLENGTH_MINUTE_2_INPUT + "' could not be parsed, unparsed text found at index 16";
    private static final String WRONGLENGTH_HOUR_ERROR_MESSAGE_1 = "Text '" + WRONGLENGTH_HOUR_1_INPUT + "' could not be parsed at index 11";
    private static final String WRONGLENGTH_HOUR_ERROR_MESSAGE_2 = "Text '" + WRONGLENGTH_HOUR_2_INPUT +"' could not be parsed at index 13";
    private static final String WRONGLENGTH_YEAR_ERROR_MESSAGE_1 = "Text '" + WRONGLENGTH_YEAR_1_INPUT + "' could not be parsed at index 6";
    private static final String WRONGLENGTH_YEAR_ERROR_MESSAGE_2 = "Text '" + WRONGLENGTH_YEAR_2_INPUT + "' could not be parsed at index 6";
    private DateTimeParser parser = new DateTimeParser();


    @Test
    public void parse_dateTimeCorrectFormat_success() {
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 4, 1, 12, 30);
        LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(FIRST_VALID_DATE_TIME);
        assertEquals(expectedLocalDateTime, parsedLocalDateTime);
    }

    @Test
    public void parse_outOfBounds_Field_Format_exceptionThrown() {
        // out of bounds minute
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(OUTOFBOUNDS_MINUTE_INPUT);
        } catch (DateTimeException e) {
            assertEquals(OUTOFBOUNDS_MINUTE_ERROR_MESSAGE, e.getMessage());
        }
        // out of bounds hour
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(OUTOFBOUNDS_HOUR_INPUT);
        } catch (DateTimeException e) {
            assertEquals(OUTOFBOUNDS_HOUR_ERROR_MESSAGE, e.getMessage());
        }
        // out of bounds day
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(OUTOFBOUNDS_DAY_INPUT);
        } catch (DateTimeException e) {
            assertEquals(OUTOFBOUNDS_DAY_ERROR_MESSAGE, e.getMessage());
        }
        // out of bounds month
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(OUTOFBOUNDS_MONTH_INPUT);
        } catch (DateTimeException e) {
            assertEquals(OUTOFBOUNDS_MONTH_ERROR_MESSAGE, e.getMessage());
        }
    }


    @Test
    public void parse_wrongNumberOfDigits_Field_Format_exceptionThrown() {
        // wrong number of digits (minute)
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(WRONGLENGTH_MINUTE_1_INPUT);
        } catch (DateTimeException e) {
            assertEquals(WRONGLENGTH_MINUTE_ERROR_MESSAGE_1, e.getMessage());
        }
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(WRONGLENGTH_MINUTE_2_INPUT);
        } catch (DateTimeException e) {
            assertEquals(WRONGLENGTH_MINUTE_ERROR_MESSAGE_2, e.getMessage());
        }
        // wrong number of digits (hour)
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(WRONGLENGTH_HOUR_1_INPUT);
        } catch (DateTimeException e) {
            assertEquals(WRONGLENGTH_HOUR_ERROR_MESSAGE_1, e.getMessage());
        }
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(WRONGLENGTH_HOUR_2_INPUT);
        } catch (DateTimeException e) {
            assertEquals(WRONGLENGTH_HOUR_ERROR_MESSAGE_2, e.getMessage());
        }
        // wrong number of digits (year)
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(WRONGLENGTH_YEAR_1_INPUT);
        } catch (DateTimeException e) {
            assertEquals(WRONGLENGTH_YEAR_ERROR_MESSAGE_1, e.getMessage());
        }
        try {
            LocalDateTime parsedLocalDateTime = parser.parseLocalDateTimeFromString(WRONGLENGTH_YEAR_2_INPUT);
        } catch (DateTimeException e) {
            assertEquals(WRONGLENGTH_YEAR_ERROR_MESSAGE_2, e.getMessage());
        }
    }
    @Test
    public void isValidDateTime_success() {
        assertTrue(DateTimeParser.isValidDateTime(FIRST_VALID_DATE_TIME));
    }


    @Test
    public void isValidDateTime_failure() {
        assertFalse(DateTimeParser.isValidDateTime(OUTOFBOUNDS_DAY));
    }
}
