package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Person;

/**
 * Deletes a meeting time from a person
 */
public class DeleteMeetingCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "deletemeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a meeting time from the person identified "
            + "by the index number used in the last person listing.\n "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MEETING_TIME + "[MeetingTime]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MEETING_TIME + "27-10-2002-11:30";

    public static final String MESSAGE_DELETE_MEETING_SUCCESS = "Deleted meeting time %1$s from Person: %2$s";
    public static final String MESSAGE_DELETE_MEETING_FAILURE_NOTFOUND = "Couldn't find meeting time %1$s in %2$s";
    public static final String MESSAGE_UNDO = "Restored meeting time %1$s to Person: %2$s";
    public static final String MESSAGE_REDO = "Re-deleted meeting time %1$s from Person: %2$s";
    private final Index index;
    private final MeetingTime meetingTime;

    private Person personToEdit;
    private Person editedPerson;

    /**
     * @param index of the person in the filtered person list to delete the MeetingTime from
     * @param meetingTime to be deleted from person
     */
    public DeleteMeetingCommand(Index index, MeetingTime meetingTime) {
        requireAllNonNull(index, meetingTime);

        this.index = index;
        this.meetingTime = meetingTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        String result;

        personToEdit = lastShownList.get(index.getZeroBased());
        Set<MeetingTime> meetingTimeToEdit = personToEdit.getMeetingTimes();

        Set<MeetingTime> editedTimes = new HashSet<>();
        editedTimes.addAll(meetingTimeToEdit);
        if (editedTimes.contains(meetingTime)) {
            editedTimes.remove(meetingTime);
            result = MESSAGE_DELETE_MEETING_SUCCESS;
        } else {
            throw new CommandException(String.format(MESSAGE_DELETE_MEETING_FAILURE_NOTFOUND, meetingTime, personToEdit));
        }

        editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getDescription(), personToEdit.getNetWorth(), editedTimes,
                personToEdit.getFilePath(), personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(result, meetingTime, editedPerson));
    }

    @Override
    public CommandResult undo(Model model) {
        model.setPerson(editedPerson, personToEdit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_UNDO, meetingTime, personToEdit));
    }

    @Override
    public CommandResult redo(Model model) {
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REDO, meetingTime, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMeetingCommand)) {
            return false;
        }

        // state check
        DeleteMeetingCommand e = (DeleteMeetingCommand) other;
        return index.equals(e.index)
                && meetingTime.equals(e.meetingTime);
    }
}
