package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

public class DeleteTaskCommand extends TaskCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL
            + ": Deletes the task that index number refers to.\n"
            + "Parameters: \n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Task deleted: %1$s";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private final Index targetIndex;

    public DeleteTaskCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        UniqueTaskList taskList = model.getTaskList();

        if (targetIndex.getZeroBased() >= taskList.getTasks().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task deletedTask = model.getTaskAtIndex(targetIndex.getZeroBased());
        model.deleteTask(deletedTask);

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, deletedTask));

    }

}
