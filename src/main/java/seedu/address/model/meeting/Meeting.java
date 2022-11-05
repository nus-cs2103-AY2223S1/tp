package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.parser.DateKeyword;
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
    private final MeetingTime meetingStartTime;
    private final MeetingTime meetingEndTime;

    /**
     * Constructs a {@code Meeting}. Every field must be present and not null.
     *
     * @param client Client to meet with
     * @param description Description of meeting
     * @param meetingDate Date of meeting
     * @param meetingStartTime Start time of meeting
     * @param meetingEndTime End time of meeting
     */
    public Meeting(Client client, Description description,
                   MeetingDate meetingDate, MeetingTime meetingStartTime, MeetingTime meetingEndTime) {
        requireAllNonNull(client, description, meetingDate, meetingStartTime, meetingEndTime);
        this.client = client;
        this.description = description;
        this.meetingDate = meetingDate;
        this.meetingStartTime = meetingStartTime;
        this.meetingEndTime = meetingEndTime;
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

    public MeetingTime getMeetingStartTime() {
        return meetingStartTime;
    }

    public MeetingTime getMeetingEndTime() {
        return meetingEndTime;
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
        boolean isSameMeetingStartTime = this.getMeetingStartTime().equals(other.getMeetingStartTime());
        boolean isSameMeetingEndTime = this.getMeetingEndTime().equals(other.getMeetingEndTime());

        return isSameClient && isSameDescription && isSameMeetingDate && isSameMeetingStartTime && isSameMeetingEndTime;
    }

    /**
     * Returns true if and only if this meeting has a date at or before the given {@code MeetingDate}.
     */
    public boolean isBeforeDate(MeetingDate other) {
        return meetingDate.compareTo(other) <= 0;
    }

    /**
     * Returns true if and only if this meeting has a date at or after the given {@code MeetingDate}.
     */
    public boolean isAfterDate(MeetingDate other) {
        return meetingDate.compareTo(other) >= 0;
    }

    public boolean isInPeriod(DateKeyword keyword) {
        return meetingDate.isInPeriod(keyword);
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
        boolean isBefore = this.getMeetingStartTime().isBefore(other.getMeetingEndTime());
        boolean isAfter = this.getMeetingEndTime().isAfter(other.getMeetingStartTime());

        return isSameMeetingDate && isBefore && isAfter;
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
                .append("; Start: ")
                .append(getMeetingStartTime())
                .append("; End: ")
                .append(getMeetingEndTime());

        return builder.toString();
    }

}
