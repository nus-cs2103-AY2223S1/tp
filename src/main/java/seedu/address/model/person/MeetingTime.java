package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's MeetingTime in the address book.
 */
public class MeetingTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting time should have the format 'DD-MM-YYYY-HH:MM'!\n"
            + "DD: Range from 01-31\n"
            + "MM: Range from 01-12\n"
            + "YYYY: Any 4 digits\n"
            + "HH:MM: Range from 00:00-23:59\n";
    public static final String VALIDATION_REGEX =
            "^([0][1-9]|([12][0-9])|(3[01]))\\-([0][1-9]|1[012])\\-\\d\\d\\d\\d\\-([0-1]?[0-9]|2?[0-3]):([0-5]\\d)$";
    public final String value;
    public final String displayValue;

    /**
     * Constructs a {@code MeetingTime}.
     *
     * @param meetingTimeString A valid date and time.
     */
    public MeetingTime(String meetingTimeString) {
        requireNonNull(meetingTimeString);
        checkArgument(isValidMeetingTime(meetingTimeString), MESSAGE_CONSTRAINTS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm");
        LocalDateTime date = LocalDateTime.parse(meetingTimeString, formatter);
        value = meetingTimeString;
        int day = date.getDayOfMonth();
        String month = date.getMonth().toString();
        int year = date.getYear();
        String time = date.toLocalTime().toString();
        displayValue = day + " " + month + " " + year + " " + time;
    }

    /**
     * Returns true if a given string is a valid networth.
     */
    public static boolean isValidMeetingTime(String test) {
        return test.matches(VALIDATION_REGEX);
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
