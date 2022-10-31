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
 * Marks a task as complete
 */
public class MarkTaskCommand extends TaskCommand {
    public static final String SUBCOMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = TaskCommand.getFullCommand(SUBCOMMAND_WORD)
        + ": Marks the task as completed\n"
        + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1\n";

    public static final String COMPLETE_SUCESS = " task %s is marked as complete%n";
    public static final String ALREADY_MARKED = " task %s is already completed%n";

    private final Index targetIndex;

    public MarkTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (task == null && targetIndex == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        if (task == null) {
            task = model.getFromFilteredTasks(targetIndex);
        }

        Task newTask = task.mark();
        if (newTask == task) {
            throw new CommandException(ALREADY_MARKED);
        }
        model.setTask(task, task.mark());
        model.refresh();
        return new CommandResult(String.format(COMPLETE_SUCESS, task));
    }
}
