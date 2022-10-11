package jarvis.logic.commands;

import static jarvis.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static java.util.Objects.requireNonNull;

import java.util.List;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Model;
import jarvis.model.Task;

/**
 * Marks a task as not done. The task is identified using its displayed index from the task book.
 */
public class UnmarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "unmarktask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task as not done."
            + "The task is identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Marked task as not done: %1$s";

    private final Index targetIndex;

    public UnmarkTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToUnmark = lastShownList.get(targetIndex.getZeroBased());
        taskToUnmark.markAsNotDone();
        model.setTask(taskToUnmark, taskToUnmark);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, taskToUnmark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkTaskCommand // instanceof handles nulls
                && targetIndex.equals(((UnmarkTaskCommand) other).targetIndex));
        // state check
    }
}

