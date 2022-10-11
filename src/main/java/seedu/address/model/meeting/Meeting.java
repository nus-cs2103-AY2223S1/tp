package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;

/**
 * Represents a Meeting in MyInsuRec.
 */
public class Meeting {
    private final Client client;
    private final Description description;
    private final MeetingDate meetingDate;
    private final MeetingTime meetingTime;

    /**
     * Constructs a {@code Meeting}. Every field must be present and not null.
     *
     * @param client Client to meet with
     * @param description Description of meeting
     * @param meetingDate Date of meeting
     * @param meetingTime Time of meeting
     */
    public Meeting(Client client, Description description, MeetingDate meetingDate, MeetingTime meetingTime) {
        requireAllNonNull(client, description, meetingDate, meetingTime);
        this.client = client;
        this.description = description;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
    }

    public Client getClient() {
        return client;
    }

    public Name getClientName() {
        return client.getName();
    }

    public Phone getClientPhone() {
        return client.getPhone();
    }

    public Description getDescription() {
        return description;
    }

    public MeetingDate getMeetingDate() {
        return meetingDate;
    }

    public MeetingTime getMeetingTime() {
        return meetingTime;
    }

    /**
     * Returns true only if both {@code Meetings} refer to the same {@code Client}, have the same description, time and
     * date. This defines a strong notion of equality between two {@code Meetings}.
     */
    public boolean equals(Meeting other) {
        if (other == this) {
            return true;
        }

        if (other == null) {
            return false;
        }

        boolean isSameClient = this.getClient().equals(other.getClient());
        boolean isSameDescription = this.getDescription().equals(other.getDescription());
        boolean isSameMeetingDate = this.getMeetingDate().equals(other.getMeetingDate());
        boolean isSameMeetingTime = this.getMeetingTime().equals(other.getMeetingTime());

        return isSameClient && isSameDescription && isSameMeetingDate && isSameMeetingTime;
    }

    /**
     * Returns true if both meetings have the same date and time and conflicts.
     * Null inputs are considered to conflict with all meetings.
     *
     * @return Whether both meetings have the same date and time and conflicts
     */
    public boolean willConflict(Meeting other) {
        if (other == null) {
            return true;
        }

        boolean isSameMeetingDate = this.getMeetingDate().equals(other.getMeetingDate());
        boolean isSameMeetingTime = this.getMeetingTime().equals(other.getMeetingTime());

        return isSameMeetingDate && isSameMeetingTime;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Client: ")
                .append(getClient().getName())
                .append("; Description: ")
                .append(getDescription())
                .append("; Date: ")
                .append(getMeetingDate())
                .append("; Time: ")
                .append(getMeetingTime());

        return builder.toString();
    }
}
