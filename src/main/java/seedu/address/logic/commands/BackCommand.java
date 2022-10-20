package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECORDS;

import seedu.address.model.Model;

/**
 * Displays all persons or records after a find or rfind command is executed
 * Keyword matching is case insensitive.
 */
public class BackCommand extends Command {

    public static final String COMMAND_WORD = "back";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Showing full list";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (model.isRecordListDisplayed()) {
            model.updateFilteredRecordList(PREDICATE_SHOW_ALL_RECORDS);
            return new CommandResult(MESSAGE_SUCCESS, false, false, true);
        } else {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
