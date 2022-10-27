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
 * Adds one or multiple meeting times to a person
 */
public class AddMeetingCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds one or multiple meeting times to the person "
            + "identified by the index number used in the last person listing. "
            + "Existing MeetingTime will not be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MEETING_TIME + "[MeetingTime]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MEETING_TIME + "27-10-2002-11:30";

    public static final String MESSAGE_ADD_MEETING_SUCCESS = "Added meeting time(s) to Person: %1$s";
    public static final String MESSAGE_UNDO = "Removed previously added meeting time(s) from Person: %1$s";
    public static final String MESSAGE_REDO = "Re-added meeting time(s) to Person: %1$s";
    private final Index index;
    private final Set<MeetingTime> meetingTimes;

    private Person personToEdit;
    private Person editedPerson;

    /**
     * @param index of the person in the filtered person list to add the MeetingTime
     * @param meetingTimes all meeting times to be added to person
     */
    public AddMeetingCommand(Index index, Set<MeetingTime> meetingTimes) {
        requireAllNonNull(index, meetingTimes);

        this.index = index;
        this.meetingTimes = meetingTimes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        personToEdit = lastShownList.get(index.getZeroBased());
        Set<MeetingTime> meetingTimesToEdit = personToEdit.getMeetingTimes();

        Set<MeetingTime> editedTimes = new HashSet<>();
        editedTimes.addAll(meetingTimesToEdit);
        editedTimes.addAll(meetingTimes);

        editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getDescription(), personToEdit.getNetWorth(), editedTimes,
                personToEdit.getFilePath(), personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_MEETING_SUCCESS, editedPerson));
    }

    @Override
    public CommandResult undo(Model model) {
        model.setPerson(editedPerson, personToEdit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_UNDO, personToEdit));
    }

    @Override
    public CommandResult redo(Model model) {
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REDO, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMeetingCommand)) {
            return false;
        }

        // state check
        AddMeetingCommand e = (AddMeetingCommand) other;
        return index.equals(e.index)
                && meetingTimes.equals(e.meetingTimes);
    }
}
