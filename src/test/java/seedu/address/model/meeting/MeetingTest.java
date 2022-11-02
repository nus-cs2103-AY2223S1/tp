package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.PersonBuilder;

public class MeetingTest {

    private final Meeting meetingOne = new MeetingBuilder().build();
    private final Meeting meetingTwo = new MeetingBuilder().build();
    private final Person johann = new PersonBuilder().withName("Johann").build();
    private final Meeting meetingOneDifferentPerson = new MeetingBuilder(johann).build();
    private final Meeting meetingOneDifferentTitle = new MeetingBuilder().withDescription("Play chess").build();
    private final Meeting meetingOneDifferentDateTime = new MeetingBuilder().withDateAndTime("10-10-2022").build();
    private final Meeting meetingOneDifferentLocation = new MeetingBuilder().withLocation("in lounge").build();

    /**
     * A simplified dummy function to substitute the convertNameToPerson(Model, String[]) in CreateMeetingCommand
     */
    private ArrayList<Person> dummyConvertNameToPerson(String [] peopleToMeet) {

        ArrayList<Person> tempPeople = new ArrayList<>();
        Person defaultAmy = new PersonBuilder().build();
        tempPeople.add(defaultAmy);

        if (Objects.equals(peopleToMeet[0], "")) {
            throw new PersonNotFoundException(PersonNotFoundException.NO_PERSON_DETECTED);
        }

        ArrayList<Person> output = new ArrayList<>();
        // Takes in the name of the address book contact, split by words in the name
        for (String personName: peopleToMeet) {
            for (Person person: tempPeople) {
                if (person.getName().equals(personName)) {
                    output.add(person);
                }
            }
        }
        return output;
    }

    /**
     * Testing the stronger notion of equality
     */
    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(meetingOne.equals(meetingOne));

        // same values (content) -> returns true
        assertTrue(meetingOne.equals(meetingTwo));

        // only different people to meet -> returns false
        assertFalse(meetingOne.equals(meetingOneDifferentPerson));

        // only different meeting title -> returns false
        assertFalse(meetingOne.equals(meetingOneDifferentTitle));

        // only different meeting date and time -> returns false
        assertFalse(meetingOne.equals(meetingOneDifferentDateTime));

        // only different meeting location -> returns false
        assertFalse(meetingOne.equals(meetingOneDifferentLocation));
    }

    /**
     * Testing the weaker notion of equality
     */
    @Test
    public void isSameMeeting() {
        // same object -> returns true
        assertTrue(meetingOne.isSameMeeting(meetingOne));

        // same values (content) -> returns true
        assertTrue(meetingOne.isSameMeeting(meetingTwo));

        // only different people to meet -> returns false
        assertFalse(meetingOne.isSameMeeting(meetingOneDifferentPerson));

        // only different meeting title -> returns true
        assertTrue(meetingOne.isSameMeeting(meetingOneDifferentTitle));

        // only different meeting date and time -> returns false
        assertFalse(meetingOne.isSameMeeting(meetingOneDifferentDateTime));

        // only different meeting location -> returns true
        assertTrue(meetingOne.isSameMeeting(meetingOneDifferentLocation));
    }

}
