package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.parser.DateKeyword;

/**
 * Represents a Meeting's date in MyInsuRec.
 */
public class MeetingDate implements Comparable<MeetingDate> {

    public static final String MESSAGE_CONSTRAINTS =
            "Meeting dates should be in the format DDMMYYYY and should be a valid day of the year";
    public static final String MESSAGE_INVALID_DATE = "Date should not be in the past!";
    public static final String VALIDATION_REGEX = "\\d{8}";
    private final LocalDate date;

    /**
     * Constructs a {@code MeetingDate}.
     *
     * @param meetingDate A valid meeting date.
     */
    public MeetingDate(LocalDate meetingDate) {
        requireNonNull(meetingDate);
        date = meetingDate;
    }

    /**
     * Returns true is a given string is a valid meeting date.
     */
    public static boolean isValidMeetingDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the meeting falls within the given period.
     */
    public boolean isInPeriod(DateKeyword keyword) {
        LocalDate today = LocalDate.now();
        LocalDate startDate;
        LocalDate endDate;

        switch (keyword) {
        case ALL_TIME:
            return true;
        case TOMORROW:
            startDate = today.plusDays(1);
            endDate = today.plusDays(1);
            break;
        case THIS_MONTH:
            startDate = today.withDayOfMonth(1);
            endDate = today.withDayOfMonth(today.getMonth().length(today.isLeapYear()));
            break;
        case THIS_WEEK:
            startDate = today;
            endDate = today.plusDays(7);
            break;
        default:
            startDate = null;
            endDate = null;
        }
        requireNonNull(startDate);
        requireNonNull(endDate);

        return isAfterDate(startDate) && isBeforeDate(endDate);
    }

    private boolean isAfterDate(LocalDate other) {
        return date.compareTo(other) >= 0;
    }

    private boolean isBeforeDate(LocalDate other) {
        return date.compareTo(other) <= 0;
    }

    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MeetingDate
                && date.equals(((MeetingDate) other).date));
    }

    @Override
    public int compareTo(MeetingDate other) {
        return date.compareTo(other.date);
    }

    public static boolean isBeforeToday(MeetingDate meetingDate) {
        return meetingDate.date.isBefore(LocalDate.now());
    }
}
