package seedu.address.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

// @@author connlim

/**
 * Unmarks a task as complete.
 */
public class UnmarkTaskCommand extends TaskCommand {
    public static final String SUBCOMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = TaskCommand.getFullCommand(SUBCOMMAND_WORD)
        + ": Marks the task as incomplete\n"
        + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1\n";

    public static final String ALREADY_UNMARKED = " task %s is already incomplete%n";
    public static final String UNMARK_SUCCESS = " task %s is marked as incomplete%n";

    private final Index targetIndex;

    public UnmarkTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex == null && task == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
        if (task == null) {
            task = model.getFromFilteredTasks(targetIndex);
        }

        Task newTask = task.unmark();
        if (newTask == task) {
            throw new CommandException(ALREADY_UNMARKED);
        }
        model.setTask(task, task.unmark());
        return new CommandResult(String.format(UNMARK_SUCCESS, task));
    }
}
