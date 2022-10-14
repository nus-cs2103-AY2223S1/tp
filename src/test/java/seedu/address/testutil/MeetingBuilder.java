package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {
    public static final ArrayList<Person> DEFAULT_PERSONS = new ArrayList<>(Arrays.asList(ALICE, BENSON));
    public static final String DEFAULT_DESCRIPTION = "CS2040";
    public static final String DEFAULT_DATETIME = "02-12-2021";
    public static final String DEFAULT_LOCATION = "COM3";

    private ArrayList<Person> peopleToMeet;
    private String meetingDescription;
    private String meetingDateAndTime;
    private String meetingLocation;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        this.peopleToMeet = DEFAULT_PERSONS;
        this.meetingDescription = DEFAULT_DESCRIPTION;
        this.meetingDateAndTime = DEFAULT_DATETIME;
        this.meetingLocation = DEFAULT_LOCATION;
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        this.peopleToMeet = meetingToCopy.getArrayListPersonToMeet();
        this.meetingDescription = meetingToCopy.getDescription();
        this.meetingDateAndTime = meetingToCopy.getDateAndTime();
        this.meetingLocation = meetingToCopy.getLocation();
    }

    /**
     * Sets the {@code ArrayList<Person>} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withPersons(Person ... persons) {
        ArrayList<Person> listOfPeople = new ArrayList<>(Arrays.asList(persons));
        this.peopleToMeet = listOfPeople;
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
     * Sets the {@code DateAndTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDateAndTime(String dateAndTime) {
        this.meetingDateAndTime = dateAndTime;
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withLocation(String location) {
        this.meetingLocation = location;
        return this;
    }

    /**
     * Tries to build meeting
     * @return meeting
     */
    public Meeting build() {
        Meeting newMeeting = null;
        try {
            newMeeting = new Meeting(peopleToMeet, meetingDescription, meetingDateAndTime, meetingLocation);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return newMeeting;
    }

}
