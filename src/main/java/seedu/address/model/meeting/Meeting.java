package seedu.address.model.meeting;

import java.util.Objects;

/**
 * Represents a meeting with a client in the FinBook.
 * Guarantees: details are immutable.
 */
public class Meeting {
    private final MeetingDate meetingDate;
    private final MeetingLocation meetingLocation;

    /**
     * Every field must be present and not null.
     */
    public Meeting(MeetingDate meetingDate, MeetingLocation meetingLocation) {
        this.meetingDate = meetingDate;
        this.meetingLocation = meetingLocation;
    }

    public MeetingDate getMeetingDate() {
        return meetingDate;
    }

    public MeetingLocation getMeetingLocation() {
        return meetingLocation;
    }

    /**
     * Returns true if both meetings have the same time and location fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getMeetingDate().equals(getMeetingDate())
            && otherMeeting.getMeetingLocation().equals(getMeetingLocation());
    }

    @Override
    public String toString() {
        StringBuilder meeting = new StringBuilder();
        meeting.append("Meeting Date: ")
            .append(meetingDate)
            .append(", Meeting Location: ")
            .append(getMeetingLocation());
        return meeting.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingDate, meetingLocation);
    }
}

