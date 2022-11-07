package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_LAST_PERSON_IN_MEETING = "Meeting Index: %s has only this person left, "
            + "please delete the meeting before deleting this person.";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        deletePersonFromAllMeetings(personToDelete, model.getFilteredMeetingList());
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }

    /**
     * deletes the person from all meetings that contains the person
     * @param p person to be deleted
     * @param meetings meeting list to iterate through
     * @throws CommandException exception thrown when person deleted is the last person in a meeting
     */
    private void deletePersonFromAllMeetings(Person p, ObservableList<Meeting> meetings) throws CommandException {
        ArrayList<Person> singlePersonList = new ArrayList<>();
        singlePersonList.add(p);
        int index = 1;
        for (Meeting m : meetings) {
            if (m.containsPerson(p) && m.getArrayListPersonToMeet().size() == 1) {
                throw new CommandException(String.format(MESSAGE_LAST_PERSON_IN_MEETING, index));
            }
            m.deletePersons(singlePersonList);
            index++;
        }
    }
}
