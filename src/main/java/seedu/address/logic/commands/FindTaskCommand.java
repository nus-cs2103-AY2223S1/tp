package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.TaskDescriptionContainsKeywordsPredicate;

/**
 * Find and lists all tasks in HR Pro Max++ whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTaskCommand extends Command {
    public static final String COMMAND_WORD = "findtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose description contain any of "
            + "the exact specified keywords (but can be case-insensitive) and displays them as "
            + "a list with index numbers.\n"
            + "A task with the description work can be found using these keywords: work, Work, WORK\n "
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final TaskDescriptionContainsKeywordsPredicate predicate;

    public FindTaskCommand(TaskDescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return (model.getFilteredTaskList().size() == 1)
                ? new CommandResult(String.format(Messages.MESSAGE_TASKS_LISTED_SINGULAR_OVERVIEW,
                    model.getFilteredTaskList().size()))
                : new CommandResult(String.format(Messages.MESSAGE_TASKS_LISTED_PLURAL_OVERVIEW,
                    model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && predicate.equals(((FindTaskCommand) other).predicate)); // state check
    }
}
