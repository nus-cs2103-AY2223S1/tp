package seedu.address.testutil;

import static seedu.address.testutil.TypicalClients.ALICE;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.client.Client;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;


/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {
    public static final String DEFAULT_DESCRIPTION = "meeting";
    public static final LocalDate DEFAULT_MEETING_DATE = LocalDate.of(2023, 1, 8);
    public static final LocalTime DEFAULT_MEETING_START_TIME = LocalTime.of(7, 20, 45, 342123342);
    public static final LocalTime DEFAULT_MEETING_END_TIME = LocalTime.of(8, 20, 45, 342123342);
    public static final Client DEFAULT_CLIENT = ALICE;

    private Description description;
    private Client client;
    private MeetingDate meetingDate;
    private MeetingTime meetingStartTime;
    private MeetingTime meetingEndTime;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        meetingDate = new MeetingDate(DEFAULT_MEETING_DATE);
        meetingStartTime = new MeetingTime(DEFAULT_MEETING_START_TIME);
        meetingEndTime = new MeetingTime(DEFAULT_MEETING_END_TIME);
        client = DEFAULT_CLIENT;
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        description = meetingToCopy.getDescription();
        meetingDate = meetingToCopy.getMeetingDate();
        meetingStartTime = meetingToCopy.getMeetingStartTime();
        meetingEndTime = meetingToCopy.getMeetingEndTime();
        client = meetingToCopy.getClient();
    }

    /**
     * Sets the {@code Description} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code MeetingDate} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withMeetingDate(LocalDate date) {
        this.meetingDate = new MeetingDate(date);
        return this;
    }

    /**
     * Sets the {@code MeetingTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withMeetingStartTime(LocalTime time) {
        this.meetingStartTime = new MeetingTime(time);
        return this;
    }

    /**
     * Sets the {@code MeetingTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withMeetingEndTime(LocalTime time) {
        this.meetingEndTime = new MeetingTime(time);
        return this;
    }

    /**
     * Sets the {@code Client} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withClient(Client client) {
        this.client = client;
        return this;
    }

    public Meeting build() {
        return new Meeting(client, description, meetingDate, meetingStartTime, meetingEndTime);
    }
}
