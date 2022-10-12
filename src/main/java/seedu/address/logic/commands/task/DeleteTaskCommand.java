package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the task panel.
 */
public class DeleteTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL
            + ": Deletes the task identified by the task index number used in the displayed task list.\n"
            + "Parameters: \n"
            + "Example: " + COMMAND_WORD_FULL + " 1";

    public static final String MESSAGE_SUCCESS = "Task deleted: %1$s";

    private final Index targetIndex;

    /**
     * Creates a DeleteTaskCommand to delete the specified {@code Task}
     */
    public DeleteTaskCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> taskList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task toDelete = taskList.get(targetIndex.getZeroBased());
        model.deleteTask(toDelete);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteTaskCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteTaskCommand) other).targetIndex));
    }

}
