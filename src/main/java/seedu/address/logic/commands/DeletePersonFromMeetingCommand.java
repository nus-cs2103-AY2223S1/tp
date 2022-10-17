package seedu.address.logic.commands;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Creates a meeting with a person in the address book
 */
public class DeletePersonFromMeetingCommand extends Command {

    public static final String COMMAND_WORD = "deletepersonfrommeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ":Deletes the list of people from the specified meeting based on the index from the displayed meetings.\n"
            + "Parameters: Meeting index ; NAMES OF PEOPLE (from address book) YOU WANT TO DELETE, (split names by ,)\n"
            + "Example: " + COMMAND_WORD + "1 ; Alex Yeoh, Anna Lim";

    public static final String MESSAGE_ADD_PEOPLE_TO_MEETING_SUCCESS = "Deleted the list of persons";

    private final String info;

    public DeletePersonFromMeetingCommand(String info) {
        this.info = info;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String[] newPeopleInformation = this.info.split(";");
        String[] newPeople = newPeopleInformation[1].strip().split(",");
        int meetingIndex = parseInt(newPeopleInformation[0].strip());

        if (meetingIndex < 0 || meetingIndex >= model.getFilteredMeetingList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        ArrayList<Person> arrayOfPeopleToDelete = Meeting.convertNameToPerson(model, newPeople);
        Meeting meetingToUpdate = model.getFilteredMeetingList().get(meetingIndex);
        if (meetingToUpdate.getNumPersons() == 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_ONLY_ONE_LEFT);
        }
        model.deleteMeeting(meetingToUpdate);
        meetingToUpdate.deletePersons(arrayOfPeopleToDelete);
        model.addMeeting(meetingToUpdate);

        return new CommandResult(String.format(MESSAGE_ADD_PEOPLE_TO_MEETING_SUCCESS));
    }

}
