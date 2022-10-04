package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * Represents a Meeting's date in MyInsuRec.
 */
public class MeetingDate {
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

    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MeetingDate
                && date.equals(((MeetingDate) other).date));
    }
}
