package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Finds and lists all tasks whose tags contain any of the argument keywords.
 * Keyword matching is case-sensitive.
 */
public class FilterTaskCommand extends Command {

    public static final String COMMAND_WORD = "filterT";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all tasks whose label(s) contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2101 CS2103T";

    private final TaskContainsKeywordsPredicate predicate;

    public FilterTaskCommand(TaskContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW,
                        model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterTaskCommand // instanceof handles nulls
                && predicate.equals(((FilterTaskCommand) other).predicate)); // state check
    }
}
