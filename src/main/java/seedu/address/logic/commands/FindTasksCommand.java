package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in the task list whose description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTasksCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "t " + COMMAND_WORD + ": Finds all tasks whose task description "
            + "contain partially or fully any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD \n"
            + "Example: t " + COMMAND_WORD + " watch lecture rec";

    private final DescriptionContainsKeywordsPredicate predicate;

    public FindTasksCommand(DescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTasksCommand // instanceof handles nulls
                && predicate.equals(((FindTasksCommand) other).predicate)); // state check
    }
}
