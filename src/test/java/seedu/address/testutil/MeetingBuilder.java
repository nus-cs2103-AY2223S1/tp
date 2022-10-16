package seedu.address.testutil;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Locale;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.util.DateTimeProcessor;

/**
 * A utility class to help with building Person objects.
 */
public class MeetingBuilder {

    public static final Person DEFAULT_PERSON_IN_MEETING_BUILDER = new PersonBuilder()
        .withTags("Classmate", "Dalao").build();
    public static final String DEFAULT_DESCRIPTION = "Do CS2103 Project";
    public static final String DEFAULT_DATE_AND_TIME = "16-10-2022 1530";
    public static final String DEFAULT_LOCATION = "University Town";

    private final ArrayList<Person> personsToMeetArray = new ArrayList<>();
    private String meetingDescription;
    private String meetingDateAndTime;
    private String meetingLocation;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() throws ParseException {
        this.personsToMeetArray.add(MeetingBuilder.DEFAULT_PERSON_IN_MEETING_BUILDER);
        this.meetingDescription = MeetingBuilder.DEFAULT_DESCRIPTION;
        this.meetingDateAndTime = MeetingBuilder.DEFAULT_DATE_AND_TIME;
        this.meetingLocation = MeetingBuilder.DEFAULT_LOCATION;
    }

    /**
     * Creates a {@code MeetingBuilder} with the data of {@code personToMeet}.
     */
    public MeetingBuilder(Person personToMeet) throws ParseException {
        this.personsToMeetArray.add(personToMeet);
        this.meetingDescription = MeetingBuilder.DEFAULT_DESCRIPTION;
        this.meetingDateAndTime = MeetingBuilder.DEFAULT_DATE_AND_TIME;
        this.meetingLocation = MeetingBuilder.DEFAULT_LOCATION;
    }

    /**
     * Sets the {@code description} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDescription(String description) {
        this.meetingDescription = description;
        return this;
    }

    /**
     * Sets the {@code date and time} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDateAndTime(String dateAndTime) throws ParseException {
        this.meetingDateAndTime = dateAndTime;
        return this;
    }

    /**
     * Sets the {@code location} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withLocation(String location) {
        this.meetingLocation = location;
        return this;
    }

    public Meeting build() throws ParseException {
        return new Meeting(this.personsToMeetArray, this.meetingDescription,
            this.meetingDateAndTime, this.meetingLocation);
    }
}
