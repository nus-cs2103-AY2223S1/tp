package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Meeting's time in MyInsuRec.
 */
public class MeetingTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Meeting times should be in the format HHMM";
    public static final String VALIDATION_REGEX = "\\d{4}";
    private final LocalTime time;

    /**
     * Constructs a {@code MeetingTime}.
     *
     * @param meetingTime A valid meeting time.
     */
    public MeetingTime(LocalTime meetingTime) {
        requireNonNull(meetingTime);
        time = meetingTime;
    }

    /**
     * Returns true is a given string is a valid meeting time.
     */
    public static boolean isValidMeetingTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isBefore(MeetingTime meetingTime) {
        return this.time.isBefore(meetingTime.time);
    }

    public boolean isAfter(MeetingTime meetingTime) {
        return this.time.isAfter(meetingTime.time);
    }

    public String toString() {
        return time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MeetingTime
                && this.toString().equals(((MeetingTime) other).toString()));
    }
}
