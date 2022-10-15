package seedu.address.logic.commands;

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

    private final Index targetIndex;

    public ListRecordCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = CommandUtil.prepareFilteredList(model, targetIndex);

        Person personToListRecords = lastShownList.get(targetIndex.getZeroBased());

        // Set Model record list displayed flag to true
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
