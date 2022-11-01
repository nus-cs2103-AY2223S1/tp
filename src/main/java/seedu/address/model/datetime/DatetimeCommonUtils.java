package seedu.address.model.datetime;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains utilities for other datetime-related classes, including parsers.
 */
public class DatetimeCommonUtils {

    public static final String DATE_INPUT_FORMAT = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_INPUT_FORMATTER = DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT);
    public static final String DATE_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public static final String DATE_READABLE_FORMAT = "yyyy MMM dd";
    public static final DateTimeFormatter DATE_READABLE_FORMATTER = DateTimeFormatter.ofPattern(DATE_READABLE_FORMAT);
    public static final String DATE_MESSAGE_CONSTRAINTS_BASE = String.format(
            "Date should be in %s format", DATE_INPUT_FORMAT);
    public static final String DATE_MESSAGE_CONSTRAINTS =
            DATE_MESSAGE_CONSTRAINTS_BASE + ", e.g. 2022-01-01";
    public static final String DATE_MESSAGE_CONSTRAINTS_UNPARSABLE =
            DATE_MESSAGE_CONSTRAINTS_BASE + ", and it must be valid!";

    public static final String TIME_INPUT_FORMAT = "HH:mm";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_INPUT_FORMAT);
    public static final String TIME_FORMAT_REGEX = "\\d{2}:\\d{2}";
    public static final String TIME_MESSAGE_CONSTRAINTS_BASE = String.format(
            "Time should be in %s format", TIME_INPUT_FORMAT);
    public static final String TIME_MESSAGE_CONSTRAINTS =
            TIME_MESSAGE_CONSTRAINTS_BASE + ", e.g. 08:00";

    public static final String TIMERANGE_FORMAT_REGEX = TIME_FORMAT_REGEX + "-" + TIME_FORMAT_REGEX;
    public static final String TIMERANGE_MESSAGE_CONSTRAINTS_BASE = String.format(
            "Time range should be in %s-%s format", TIME_INPUT_FORMAT, TIME_INPUT_FORMAT);
    public static final String TIMERANGE_MESSAGE_CONSTRAINTS =
            TIMERANGE_MESSAGE_CONSTRAINTS_BASE + ", e.g. 08:00-09:00";
    public static final String TIMERANGE_MESSAGE_CONSTRAINTS_UNPARSABLE =
            TIMERANGE_MESSAGE_CONSTRAINTS_BASE + ", and it must be valid!";
    public static final String TIMERANGE_MESSAGE_CONSTRAINTS_START_END =
            TIMERANGE_MESSAGE_CONSTRAINTS_BASE + ", and start time should not be after end time!";

    public static final String DAY_FORMAT = "d";
    public static final String DAY_FORMAT_REGEX = "[1-7]";
    public static final String DAY_MESSAGE_CONSTRAINTS =
            "Day should only contain a number from 1 (Monday) to 7 (Sunday)";

    public static final String DATETIME_INPUT_FORMAT = DATE_INPUT_FORMAT + " " + TIME_INPUT_FORMAT;
    public static final DateTimeFormatter DATETIME_INPUT_FORMATTER =
            DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);
    public static final String DATETIME_FORMAT_REGEX = DATE_FORMAT_REGEX + " " + TIME_FORMAT_REGEX;
    public static final String DATETIME_READABLE_FORMAT = DATE_READABLE_FORMAT + " " + TIME_INPUT_FORMAT;
    public static final DateTimeFormatter DATETIME_READABLE_FORMATTER =
            DateTimeFormatter.ofPattern(DATETIME_READABLE_FORMAT);
    public static final String DATETIME_MESSAGE_CONSTRAINTS_BASE = String.format(
            "Datetime should be in %s %s format", DATE_INPUT_FORMAT, TIME_INPUT_FORMAT);
    public static final String DATETIME_MESSAGE_CONSTRAINTS =
            DATETIME_MESSAGE_CONSTRAINTS_BASE + ", e.g. 2022-01-01 08:00";
    public static final String DATETIME_MESSAGE_CONSTRAINTS_UNPARSABLE =
            DATETIME_MESSAGE_CONSTRAINTS_BASE + ", and it must be valid!";

    public static final String DATETIME_MESSAGE_CONSTRAINTS_DATETIMEPASSED = "Please input a date and time that is "
            + "after the current time. The current date and time as of now is ";

    /**
     * Converts a DayOfWeek to a readable form, e.g. Mon, Tue
     * @param day A DayOfWeek to convert
     * @return Human-readable day
     */
    public static String dayOfWeekToReadable(DayOfWeek day) {
        return day.getDisplayName(TextStyle.SHORT, Locale.getDefault());
    }

    /**
     * Splits a time range string by the hyphen "-".
     * Checks whether the start time and end time is logically valid
     *
     * @param timeRangeString  String to be split
     * @throws ParseException If the string does not appear to be a time range
     */
    public static String[] splitTimeRangeString(String timeRangeString) throws ParseException {
        if (!timeRangeString.matches(TIMERANGE_FORMAT_REGEX)) {
            throw new ParseException(TIMERANGE_MESSAGE_CONSTRAINTS);
        }
        return timeRangeString.trim().split("-");
    }

    /**
     * Checks whether the date string is valid.
     * The strings must be parsable by LocalTime.
     *
     * @param dateString Date string
     * @throws ParseException If the date is invalid.
     */
    public static void assertDateValid(String dateString) throws ParseException {
        try {
            LocalDate.parse(dateString, DATE_INPUT_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new ParseException(DATE_MESSAGE_CONSTRAINTS_UNPARSABLE);
        }
    }

    /**
     * Checks whether the datetime string is valid and is after the current time as given by LocalDateTime.now().
     * The strings must be parsable by LocalDateTime.
     *
     * @param datetimeString Datetime string
     * @throws ParseException If the datetime is invalid.
     */
    public static void assertDatetimeValid(String datetimeString) throws ParseException {
        LocalDateTime parsedDatetime;
        try {
            parsedDatetime = LocalDateTime.parse(datetimeString, DATETIME_INPUT_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new ParseException(DATETIME_MESSAGE_CONSTRAINTS_UNPARSABLE);
        }
        if (parsedDatetime.isBefore(LocalDateTime.now())) {
            throw new ParseException(DATETIME_MESSAGE_CONSTRAINTS_DATETIMEPASSED
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT)));
        }
    }

    /**
     * Checks whether the LocalDateTime is after the current time as given by LocalDateTime.now().
     *
     * @param localDateTime the LocalDateTime of the input date and start time
     * @throws ParseException If the LocalDateTime is after LocalDateTime.now()
     */
    public static void assertStartDatetimeIsAfterCurrentDatetime(LocalDateTime localDateTime) throws ParseException {
        if (localDateTime.isBefore(LocalDateTime.now())) {
            throw new ParseException(DATETIME_MESSAGE_CONSTRAINTS_DATETIMEPASSED
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT)));
        }
    }

    /**
     * Checks whether the time range strings are valid.
     * The strings must be parsable by LocalTime, and the start time must be before the end time, and after the current
     * time.
     *
     * @param startTimeString Start time
     * @param endTimeString End time
     * @throws ParseException If the time range is invalid.
     */
    public static void assertTimeRangeValid(String startTimeString, String endTimeString) throws ParseException {
        LocalTime startTime;
        LocalTime endTime;
        try {
            startTime = LocalTime.parse(startTimeString, TIME_FORMATTER);
            endTime = LocalTime.parse(endTimeString, TIME_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new ParseException(TIMERANGE_MESSAGE_CONSTRAINTS_UNPARSABLE);
        }
        if (!endTime.isAfter(startTime)) {
            throw new ParseException(TIMERANGE_MESSAGE_CONSTRAINTS_START_END);
        }
    }

    /**
     * Parses a {@code String datetimeString} into a {@code Datetime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code datetimeString} is invalid.
     */
    public static Datetime parseDatetime(String date, String time) throws ParseException {
        requireAllNonNull(date, time);

        String trimmedDatetime = date.trim() + " " + time.trim();

        if (!trimmedDatetime.matches(DATETIME_FORMAT_REGEX)) {
            throw new ParseException(DATETIME_MESSAGE_CONSTRAINTS);
        }
        assertDatetimeValid(trimmedDatetime);

        return Datetime.fromFormattedString(trimmedDatetime);
    }

    /**
     * Parses a {@code String timeRange} into a {@code DatetimeRange}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timeRange} is invalid.
     */
    public static DatetimeRange parseDatetimeRange(String date, String timeRange) throws ParseException {
        requireAllNonNull(date, timeRange);

        if (!date.matches(DATE_FORMAT_REGEX)) {
            throw new ParseException(DATE_MESSAGE_CONSTRAINTS);
        }

        assertDateValid(date);
        String[] times = splitTimeRangeString(timeRange);
        assertTimeRangeValid(times[0], times[1]);

        DatetimeRange parsedDatetimeRange = DatetimeRange.fromFormattedString(date, times[0], times[1]);

        assertStartDatetimeIsAfterCurrentDatetime(parsedDatetimeRange.getStartDatetime());
        return parsedDatetimeRange;
    }

    /**
     * Parses a {@code String timeslot} into a {@code WeeklyTimeslot}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timeslot} is invalid.
     */
    public static WeeklyTimeslot parseWeeklyTimeslot(String dayString, String timeslot) throws ParseException {
        requireAllNonNull(dayString, timeslot);

        if (!dayString.matches(DAY_FORMAT_REGEX)) {
            throw new ParseException(DAY_MESSAGE_CONSTRAINTS);
        }

        String[] times = splitTimeRangeString(timeslot);
        assertTimeRangeValid(times[0], times[1]);
        return WeeklyTimeslot.fromFormattedString(dayString, times[0], times[1]);
    }
}
