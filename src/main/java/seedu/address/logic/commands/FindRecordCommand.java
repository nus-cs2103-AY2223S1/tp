package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.RecordContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindRecordCommand extends Command {

    public static final String COMMAND_WORD = "findR";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all records whose content contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " suffers from common cold";

    private final RecordContainsKeywordsPredicate predicate;

    public FindRecordCommand(RecordContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isRecordListDisplayed()) {
            throw new CommandException(MESSAGE_RECORD_COMMAND_PREREQUISITE);
        }

        model.updateFilteredRecordList(predicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_RECORDS_LISTED_OVERVIEW, model.getFilteredRecordList().size()),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindRecordCommand // instanceof handles nulls
                && predicate.equals(((FindRecordCommand) other).predicate)); // state check
    }
}
