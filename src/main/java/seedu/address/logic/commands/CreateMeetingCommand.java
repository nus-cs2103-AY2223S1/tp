package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Creates a meeting with a person in the address book
 */
public class CreateMeetingCommand extends Command {

    public static final String COMMAND_WORD = "meet";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Schedules a new meeting between you and another user.\n"
        + "Parameters: NAME OF PERSON YOU ARE MEETING (from address book);;;\n"
        + "Title of meeting;;;\n"
        + "Date and time of meeting (in dd-MM-yyyy HHmm) format;;;\n"
        + "location of meeting\n"
        + "Example: " + COMMAND_WORD + "Alex Yeoh ;;; Study Session ;;; 06-10-2022 2015 ;;; UTown";

    public static final String MESSAGE_CREATE_MEETING_SUCCESS = "Created meeting with: %1$s";

    private final String meetingDetails;

    public CreateMeetingCommand(String meetingDetails) {
        this.meetingDetails = meetingDetails;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String[] newMeetingInformation = this.meetingDetails.split(";;;");
        String personToMeet = newMeetingInformation[0].strip();
        String meetingTitle = newMeetingInformation[1].strip();
        String meetingDateAndTime = newMeetingInformation[2].strip();
        String meetingLocation = newMeetingInformation[3].strip();

        // Takes in the name of the address book contact, split by words in the name
        NameContainsKeywordsPredicate personNameArray =
            new NameContainsKeywordsPredicate(Arrays.asList(personToMeet));

        // updates the list of persons in address book based on predicate
        model.updateFilteredPersonList(personNameArray);
        ObservableList<Person> listOfPeople = model.getFilteredPersonList();

        // Am thinking if there's a better way to check if the person exists
        // Since model.hasPerson only takes in a person object as argument
        if (listOfPeople.isEmpty()) {
            throw new RuntimeException(); // placeholder
        }
        Person otherPerson = listOfPeople.get(0);

        Meeting newMeeting = model.createNewMeeting(otherPerson, meetingTitle, meetingDateAndTime, meetingLocation);
        model.addMeeting(newMeeting);

        return new CommandResult(String.format(MESSAGE_CREATE_MEETING_SUCCESS, personToMeet));
    }

}
