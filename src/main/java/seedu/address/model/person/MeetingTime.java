package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Person's MeetingTime in the address book.
 */
public class MeetingTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting time should have the format 'DD-MM-YYYY-HH:MM'!\n"
            + "DD: Range from 01-31\n"
            + "MM: Range from 01-12\n"
            + "YYYY: Range from 2000-2099\n"
            + "HH:MM: Range from 00:00-23:59\n";
    public static final String MESSAGE_INVALID_DATE =
            "Meeting time input has an invalid date!\n"
            + "Such as: '29-02-2022-15:00', since 2022 is not a leap year!\n"
            + "Or such as: '31-11-2022-17:00', since November only has 30 days\n";
    public static final String VALIDATION_REGEX =
            "^([0][1-9]|([12][0-9])|(3[01]))\\-([0][1-9]|1[012])\\-(20\\d\\d)\\-([0-1]?[0-9]|2?[0-3]):([0-5]\\d)$";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu-HH:mm");
    public final String value;
    public final String displayValue;
    private final LocalDateTime date;

    /**
     * Constructs a {@code MeetingTime}.
     *
     * @param meetingTimeString A valid date and time.
     */
    public MeetingTime(String meetingTimeString) {
        requireNonNull(meetingTimeString);
        checkArgument(isValidMeetingTime(meetingTimeString), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDayMonth(meetingTimeString), MESSAGE_INVALID_DATE);
        date = LocalDateTime.parse(meetingTimeString, formatter);
        value = meetingTimeString;
        displayValue = formatMeetingTime();
    }

    /**
     * Returns true if a given string is a valid meeting time.
     */
    public static boolean isValidMeetingTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string has a valid day within a given month.
     */
    public static boolean isValidDayMonth(String meetingTimeString) {
        try {
            LocalDateTime temp = LocalDateTime.parse(meetingTimeString,
                    formatter.withResolverStyle(ResolverStyle.STRICT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Gets a LocalDateTime Object
     * @return LocalDateTime object
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Formats meeting time for display
     * @return String formattedMeetingTime
     */
    public String formatMeetingTime() {
        int day = date.getDayOfMonth();
        String month = date.getMonth().toString();
        int year = date.getYear();
        String time = date.toLocalTime().toString();
        return day + " " + month + " " + year + " " + time;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingTime // instanceof handles nulls
                && value.equals(((MeetingTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
