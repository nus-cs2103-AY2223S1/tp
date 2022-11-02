package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.exceptions.ImpreciseMatchException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Creates a meeting with a person in the address book
 */
public class DeletePersonFromMeetingCommand extends Command {

    public static final String COMMAND_WORD = "deletepersonfrommeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ":Deletes the list of people from the specified meeting based on the index from the displayed meetings.\n"
            + "Parameters: Meeting index ; NAMES OF PEOPLE (from address book) YOU WANT TO DELETE, (split names by ,)\n"
            + "Example: " + COMMAND_WORD + " 1 ; Alex Yeoh, Anna Lim";

    public static final String MESSAGE_DELETE_PEOPLE_TO_MEETING_SUCCESS = "Deleted the list of persons";

    private final String info;

    private Index meetingIndex;

    public DeletePersonFromMeetingCommand(String info) {
        this.info = info;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        requireNonNull(model);
        String[] newPeopleInformation = this.info.split(";");

        if (newPeopleInformation.length != 2) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        String[] newPeople = newPeopleInformation[1].strip().split(",");
        meetingIndex = ParserUtil.parseIndex(newPeopleInformation[0].strip());

        if (meetingIndex.getZeroBased() >= model.getFilteredMeetingList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        try {
            ArrayList<Person> arrayOfPeopleToDelete = Meeting.convertNameToPerson(model, newPeople);
            Meeting meetingToUpdate = model.getFilteredMeetingList().get(meetingIndex.getZeroBased());
            if (meetingToUpdate.getNumPersons() <= arrayOfPeopleToDelete.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEETING_ONLY_ONE_LEFT);
            }
            meetingToUpdate.deletePersons(arrayOfPeopleToDelete);
            model.deleteMeeting(meetingToUpdate);
            model.addMeeting(meetingToUpdate, meetingIndex.getZeroBased());
        } catch (PersonNotFoundException e) {
            throw new CommandException(e.getMessage() + "\n" + CreateMeetingCommand.PERSON_NOT_FOUND);
        } catch (DuplicatePersonException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_DUPLICATE_NAMES);
        } catch (ImpreciseMatchException e) {
            throw new CommandException(CreateMeetingCommand.IMPRECISE_NAME_PREDICATE);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_PEOPLE_TO_MEETING_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonFromMeetingCommand // instanceof handles nulls
                && this.info.equals(((DeletePersonFromMeetingCommand) other).info)); // state check
    }

}
