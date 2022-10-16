package seedu.address.model.meeting;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.CreateMeetingCommand;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.util.DateTimeProcessor;

/**
 * Class for a new Meeting
 */
public class Meeting {

    private ArrayList<Person> peopleToMeetArray;
    private UniquePersonList peopleToMeetList = new UniquePersonList();
    private String meetingDescription;
    private String meetingDateAndTime;
    private String meetingLocation;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.UK)
        .withResolverStyle(ResolverStyle.SMART);
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm", Locale.UK)
        .withResolverStyle(ResolverStyle.SMART);
    private final DateTimeProcessor validator = new DateTimeProcessor(dateFormatter, timeFormatter);

    /**
     * Constructor for a new Meeting
     *
     * @param peopleToMeetArray the people whom the user is meeting with
     * @param meetingTitle the description/ title of the meeting
     * @param meetingDateAndTime the date and time of meeting
     * @param meetingLocation the location of the meeting
     */
    public Meeting(ArrayList<Person> peopleToMeetArray, String meetingTitle,
        String meetingDateAndTime, String meetingLocation) throws ParseException {
        this.peopleToMeetArray = peopleToMeetArray;
        this.people.setPersons(peopleToMeetArray);
        this.meetingDescription = meetingTitle;
        this.meetingDateAndTime = validator.processDateTime(meetingDateAndTime);
        this.meetingLocation = meetingLocation;
    }

    /**
     * converts array of string to array of person
     *
     * @param peopleToMeet the array of people names
     */
    public static ArrayList<Person> convertNameToPerson(Model model, String[] peopleToMeet) {
        ArrayList<Person> output = new ArrayList<>();
        // Takes in the name of the address book contact, split by words in the name
        for (String personName: peopleToMeet) {
            NameContainsKeywordsPredicate personNamePredicate
                = new NameContainsKeywordsPredicate(Arrays.asList(personName.strip()));

            // updates the list of persons in address book based on predicate
            model.updateFilteredPersonList(personNamePredicate);
            ObservableList<Person> listOfPeople = model.getFilteredPersonList();

            // Am thinking if there's a better way to check if the person exists
            // Since model.hasPerson only takes in a person object as argument
            if (listOfPeople.isEmpty()) {
                throw new PersonNotFoundException();
            } else { // get the first person in the address book whose name matches
                output.add(listOfPeople.get(0));
            }
        }
        return output;
    }

    /**
     * modifies the location of the meeting
     *
     * @param location of the meeting
     */
    public void setMeetingLocation(String location) {
        this.meetingLocation = location;
    }

    /**
     * modifies the description of the meeting
     *
     * @param description of the meeting
     */
    public void editMeetingDescription(String description) {
        this.meetingDescription = description;
    }

    // might want to check for parseException earlier tho
    /**
     * modifies the date and time of the meeting
     *
     * @param dateAndTime of the meeting
     * @throws ParseException when the dateAndTime is in the wrong format
     */
    public void editMeetingDateAndTime(String dateAndTime) throws ParseException {
        this.meetingDateAndTime = validator.processDateTime(dateAndTime);
    }

    /**
     * Adds the array of persons to the unique persons list
     * @param people the array list of people to be added to the meeting
     */
    public void addPersons(ArrayList<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            this.peopleToMeetList.add(people.get(i));
        }
    }

    public ArrayList<Person> getArrayListPersonToMeet() {
        ArrayList<Person> a = new ArrayList<>();
        this.peopleToMeet.iterator().forEachRemaining(a::add);
        return a;
    }

    public UniquePersonList getPersonToMeet() {
        return this.peopleToMeetList;
    }

    public String getDateAndTime() {
        return this.meetingDateAndTime;
    }

    public String getDescription() {
        return this.meetingDescription;
    }

    public String getLocation() {
        return this.meetingLocation;
    }

    public String getPeopleToMeetAsString() {
        String res = "";
        for (Person p : peopleToMeet) {
            res = res + p.getName().fullName + ", ";
        }
        return res.substring(0, res.length() - 2);
    }

    /**
     * Returns true if both meetings include the same person to meet
     * and are at the same time.
     * This defines a weaker notion of equality between two meetings.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
            && (otherMeeting.getPersonToMeet().equals(getPersonToMeet()))
            && (otherMeeting.getDateAndTime().equals(getDateAndTime()));
    }

    /**
     * Returns true if both meetings have the same data fields.
     * This defines a stronger notion of equality between two meetings.
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
        return otherMeeting.getPersonToMeet().equals(getPersonToMeet())
            && otherMeeting.getDescription().equals(getDescription())
            && otherMeeting.getDateAndTime().equals(getDateAndTime())
            && otherMeeting.getLocation().equals(getLocation());
    }

    @Override
    public String toString() {
        return String.format("%1$s", CreateMeetingCommand.peopleToNameAndTagList(this.peopleToMeetArray))
            + "For: " + this.meetingDescription + "\n"
            + "On: " + this.meetingDateAndTime + "\n"
            + "At: " + this.meetingLocation + "\n";
    }

}
