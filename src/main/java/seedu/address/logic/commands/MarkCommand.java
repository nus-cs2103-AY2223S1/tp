package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Mark a task identified using it's displayed index from the task list as complete.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String FULL_COMMAND_WORD = "t mark";

    public static final String MESSAGE_USAGE = FULL_COMMAND_WORD
        + ": Indicates the task at the specified INDEX in the displayed task list is not completed.\n"
        + "Parameters: INDEX\n"
        + "Example: " + FULL_COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Successfully Marked Task: %1$s";
    public static final String MESSAGE_TASK_ALREADY_MARKED = "The task is already marked!";


    private final Index targetIndex;

    public MarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                String.format(Messages.MESSAGE_INVALID_TASK_INDEX_TOO_LARGE, lastShownList.size() + 1));
        }

        Task taskToMark = lastShownList.get(targetIndex.getZeroBased());
        if (taskToMark.isComplete()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_MARKED);
        }
        Task markedTask = taskToMark.mark();

        model.replaceTask(taskToMark, markedTask, true);

        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, taskToMark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof MarkCommand // instanceof handles nulls
            && targetIndex.equals(((MarkCommand) other).targetIndex)); // state check
    }
}
