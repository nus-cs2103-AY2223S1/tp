package seedu.address.testutil;

//import static seedu.address.testutil.TypicalPersons.ALICE;
//import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {
    //    public static final ArrayList<Person> DEFAULT_PERSONS = new ArrayList<>(Arrays.asList(ALICE, BENSON));
    //    public static final String DEFAULT_DESCRIPTION = "CS2040";
    //    public static final String DEFAULT_DATETIME = "02-12-2021";
    //    public static final String DEFAULT_LOCATION = "COM3";

    public static final Person DEFAULT_PERSON_IN_MEETING_BUILDER_TWO = new PersonBuilder()
        .withTags("Classmate", "Dalao").build();
    public static final String DEFAULT_DESCRIPTION_TW0 = "Do CS2103 Project";
    public static final String DEFAULT_DATE_AND_TIME_TWO = "Monday, 20 November 2000 03:30 pm";
    public static final String DEFAULT_LOCATION_TWO = "University Town";

    private ArrayList<Person> personsToMeetArray = new ArrayList<>();

    private String meetingDescription;
    private String meetingDateAndTime;
    private String meetingLocation;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        this.personsToMeetArray.add(MeetingBuilder.DEFAULT_PERSON_IN_MEETING_BUILDER_TWO);
        this.meetingDescription = MeetingBuilder.DEFAULT_DESCRIPTION_TW0;
        this.meetingDateAndTime = MeetingBuilder.DEFAULT_DATE_AND_TIME_TWO;
        this.meetingLocation = MeetingBuilder.DEFAULT_LOCATION_TWO;
    }

    /**
     * Creates a {@code MeetingBuilder} with the data of {@code personToMeet}.
     */
    public MeetingBuilder(Person personToMeet) {
        this.personsToMeetArray.add(personToMeet);
        this.meetingDescription = MeetingBuilder.DEFAULT_DESCRIPTION_TW0;
        this.meetingDateAndTime = MeetingBuilder.DEFAULT_DATE_AND_TIME_TWO;
        this.meetingLocation = MeetingBuilder.DEFAULT_LOCATION_TWO;
    }

    //    /**
    //     * Sets the {@code description} of the {@code Meeting} that we are building.
    //     */
    //    public MeetingBuilder() {
    //        this.peopleToMeet = DEFAULT_PERSONS;
    //        this.meetingDescription = DEFAULT_DESCRIPTION;
    //        this.meetingDateAndTime = DEFAULT_DATETIME;
    //        this.meetingLocation = DEFAULT_LOCATION;
    //    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        this.personsToMeetArray = meetingToCopy.getArrayListPersonToMeet();
        this.meetingDescription = meetingToCopy.getDescription();
        this.meetingDateAndTime = meetingToCopy.getDateAndTime();
        this.meetingLocation = meetingToCopy.getLocation();
    }

    /**
     * Sets the {@code ArrayList<Person>} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withPersons(Person ... persons) {
        ArrayList<Person> listOfPeople = new ArrayList<>(Arrays.asList(persons));
        this.personsToMeetArray = listOfPeople;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDescription(String description) {
        this.meetingDescription = description;
        return this;
    }

    /**
     * Sets the {@code dateAndTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDateAndTime(String dateAndTime) {
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

    /**
     * Builds a new Meeting
     */
    public Meeting build() {
        return new Meeting(this.personsToMeetArray, this.meetingDescription,
            this.meetingDateAndTime, this.meetingLocation);
    }

}
