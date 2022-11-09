package seedu.address.model.meeting;

import java.time.LocalDate;

import seedu.address.model.util.Date;

/**
 * Represents a Meeting's date in MyInsuRec.
 */
public class MeetingDate extends Date {

    public static final String MESSAGE_CONSTRAINTS =
            "Meeting dates should be in the format DDMMYYYY and should be a valid day of the year";
    public static final String MESSAGE_INVALID_DATE = "Date should not be in the past!";
    public static final String VALIDATION_REGEX = "\\d{8}";

    /**
     * Constructs a {@code MeetingDate}.
     *
     * @param meetingDate A valid meeting date.
     */
    public MeetingDate(LocalDate meetingDate) {
        super(meetingDate);
    }

    /**
     * Returns true is a given string is a valid meeting date.
     */
    public static boolean isValidMeetingDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MeetingDate
                && date.equals(((MeetingDate) other).date));
    }
}
