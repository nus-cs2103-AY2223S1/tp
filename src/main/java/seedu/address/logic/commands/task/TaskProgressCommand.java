package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Shows the percentage of tasks with any of the specified tags that are completed
 */
public class TaskProgressCommand extends Command {

    public static final String COMMAND_WORD = "progressT";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows percentage completion progress for tasks with any of the specified labels.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2101 CS2103T";

    public static final String MESSAGE_SHOW_PROGRESS_SUCCESS = "Task Completion: %1$.1f %%";
    public static final String MESSAGE_SHOW_NO_TASKS = "No tasks under this tag!";

    private final TaskContainsKeywordsPredicate predicate;

    /**
     * Creates an TaskProgressCommand to with the specified {@code TaskContainsKeywordsPredicate}
     * @param predicate
     */
    public TaskProgressCommand(TaskContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        double percentageComplete = model.getPercentageCompletion(predicate);

        if (percentageComplete < 0) {
            throw new CommandException(MESSAGE_SHOW_NO_TASKS);
        }

        return new CommandResult(
                String.format(MESSAGE_SHOW_PROGRESS_SUCCESS,
                        percentageComplete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskProgressCommand // instanceof handles nulls
                && predicate.equals(((TaskProgressCommand) other).predicate));
    }

}
