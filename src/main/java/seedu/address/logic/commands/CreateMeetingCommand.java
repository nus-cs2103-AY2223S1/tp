package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Creates a meeting with a person in the address book
 */
public class CreateMeetingCommand extends Command {

    public static final String COMMAND_WORD = "meet";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Schedules a new meeting between you and another user.\n"
        + "Parameters: NAMES OF PEOPLE (from address book) YOU ARE MEETING, (split names by }} ) ;;;\n"
        + "Title of meeting;;;\n"
        + "Date and time of meeting (in dd-MM-yyyy HHmm) format;;;\n"
        + "location of meeting\n"
        + "Example: " + COMMAND_WORD + "Alex Yeoh ;;; Study Session ;;; 06-10-2022 2015 ;;; UTown";

    public static final String MESSAGE_CREATE_MEETING_SUCCESS = "Created meeting with: %1$s";

    private final String meetingInfo;

    public CreateMeetingCommand(String meetingInfo) {
        this.meetingInfo = meetingInfo;
    }

    private ArrayList<Person> convertNameToPerson(Model model, String[] peopleToMeet) {
        ArrayList<Person> output = new ArrayList<>();
        // Takes in the name of the address book contact, split by words in the name
        for (String personName: peopleToMeet) {

            String[] nameKeywords = personName.split("\\s+");

            NameContainsKeywordsPredicate personNamePredicate =
                new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));

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

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String[] newMeetingInformation = this.meetingInfo.split(";;;");
        String[] peopleToMeet = newMeetingInformation[0].strip().split("}}");
        String meetingTitle = newMeetingInformation[1].strip();
        String meetingDateAndTime = newMeetingInformation[2].strip();
        String meetingLocation = newMeetingInformation[3].strip();

        ArrayList<Person> arrayOfPeopleToMeet = convertNameToPerson(model, peopleToMeet);

        Meeting newMeeting = model.createNewMeeting(arrayOfPeopleToMeet, meetingTitle,
            meetingDateAndTime, meetingLocation);
        model.addMeeting(newMeeting);

        return new CommandResult(String.format(MESSAGE_CREATE_MEETING_SUCCESS, arrayOfPeopleToMeet.get(0)));
    }

}
