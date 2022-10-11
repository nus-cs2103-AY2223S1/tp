package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECORDS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all records for a specific patient to the user.
 */
public class ListRecordCommand extends Command {

    public static final String COMMAND_WORD = "listR";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of all records for the specific patient in the records database.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Listed records for this patient: ";

    private static Person lastCalledPerson;

    private final Index targetIndex;

    public ListRecordCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Checks if listR command is previously called.
     *
     * @return true if listR command is previously called, false otherwise.
     */
    public static boolean isCalled() {
        return ListRecordCommand.lastCalledPerson != null;
    }

    /**
     * Getter for the Person that is last called.
     *
     * @return The Person that is last called.
     */
    public static Person getLastCalledPerson() {
        return ListRecordCommand.lastCalledPerson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToListRecords = lastShownList.get(targetIndex.getZeroBased());

        ListRecordCommand.lastCalledPerson = personToListRecords;

        model.setPersonWithRecords(personToListRecords);
        model.setRecordListDisplayed(true);

        model.setFilteredRecordList(personToListRecords);
        model.updateFilteredRecordList(PREDICATE_SHOW_ALL_RECORDS);

        String feedbackToUser = MESSAGE_SUCCESS + personToListRecords.getName() + "\n"
                + String.format(Messages.MESSAGE_RECORDS_LISTED_OVERVIEW,
                model.getFilteredRecordList().size());

        return new CommandResult(feedbackToUser, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListRecordCommand // instanceof handles nulls
                && targetIndex.equals(((ListRecordCommand) other).targetIndex)); // state check
    }
}
