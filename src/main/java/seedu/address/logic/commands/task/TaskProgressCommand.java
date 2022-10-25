package seedu.address.logic.commands.task;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

public class TaskProgressCommand extends Command {

    public static final String COMMAND_WORD = "progressT";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows percentage completion progress for tasks with the specified tags.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " grocery shopping friends";

    public static final String MESSAGE_SHOW_PROGRESS_SUCCESS = "Task Completion: %1$s";

    private final TaskContainsKeywordsPredicate predicate;

    public TaskProgressCommand(TaskContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW,
                        model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskProgressCommand // instanceof handles nulls
                && predicate.equals(((TaskProgressCommand) other).predicate));
    }

}