package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {
    public static final String DEFAULT_DESCRIPTION = "meeting";
    public static final LocalDate DEFAULT_MEETING_DATE = LocalDate.of(2020, 1, 8);
    public static final LocalTime DEFAULT_MEETING_TIME = LocalTime.of(7, 20, 45, 342123342);
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    private static final Set<Tag> DEFAULT_TAGS = new HashSet<>();

    private Description description;
    private Client client;
    private MeetingDate meetingDate;
    private MeetingTime meetingTime;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        meetingDate = new MeetingDate(DEFAULT_MEETING_DATE);
        meetingTime = new MeetingTime(DEFAULT_MEETING_TIME);
        client = new Client(new Name(DEFAULT_NAME), new Phone(DEFAULT_PHONE),
                new Email(DEFAULT_EMAIL), new Address(DEFAULT_ADDRESS), DEFAULT_TAGS);
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        description = meetingToCopy.getDescription();
        meetingDate = meetingToCopy.getMeetingDate();
        meetingTime = meetingToCopy.getMeetingTime();
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
    public MeetingBuilder withMeetingTime(LocalTime time) {
        this.meetingTime = new MeetingTime(time);
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
        return new Meeting(client, description, meetingDate, meetingTime);
    }
}
