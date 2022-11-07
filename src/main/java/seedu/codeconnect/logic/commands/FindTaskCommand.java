package seedu.codeconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.codeconnect.commons.core.Messages;
import seedu.codeconnect.model.Model;
import seedu.codeconnect.model.task.Task;
import seedu.codeconnect.model.task.TaskContainsKeywordsPredicate;
import seedu.codeconnect.model.task.TaskContainsModulesPredicate;

/**
 * Finds and lists all tasks in the TaskList whose description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks in the TaskList whose description "
            + "contains the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: {field_prefix + keyword} ...\n"
            + "Example: " + COMMAND_WORD + " n/ lecture 20";

    private final Predicate<Task> predicate;

    public FindTaskCommand(TaskContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public FindTaskCommand(TaskContainsModulesPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getSortedTaskList().size()), true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && predicate.equals(((FindTaskCommand) other).predicate)); // state check
    }
}
