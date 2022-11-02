package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.NameContainsKeywordsPredicate;

/**
 * Lists all tasks with names matching any of the keywords users input.
 */
public class ListNameCommand extends ListCommand {

    public static final String COMMAND_WORD = "-n";

    public static final String MESSAGE_USAGE = ListCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Lists all tasks whose task names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers. Note"
            + " that it will find tasks with task names that contain any of the keywords\n"
            + "Parameters: KEYWORD*\n"
            + "Example: " + ListCommand.COMMAND_WORD + " " + COMMAND_WORD + " task1 ta sk";

    private final NameContainsKeywordsPredicate predicate;

    public ListNameCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        model.updateFilterStatus(predicate.toString());
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListNameCommand // instanceof handles nulls
                && predicate.equals(((ListNameCommand) other).predicate)); // state check
    }
}
