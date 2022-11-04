package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandUtil.createEditedTask;
import static seedu.address.model.task.Task.PREDICATE_SHOW_ARCHIVED_TASKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Archive a task in the task list.
 */
public class ArchiveTaskCommand extends Command {

    public static final String COMMAND_WORD = "archiveT";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Archive the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Archived Task: %1$s";
    public static final String MESSAGE_ALREADY_ARCHIVED = "This task is already archived.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Creates an ArchiveTaskCommand object.
     * @param index of the task in the filtered task list to edit.
     * @param editTaskDescriptor details to edit the task with.
     */
    public ArchiveTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = editTaskDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToArchive = lastShownList.get(index.getZeroBased());
        Task archivedTask = createEditedTask(taskToArchive, editTaskDescriptor);

        if (taskToArchive.equals(archivedTask)) {
            throw new CommandException(MESSAGE_ALREADY_ARCHIVED);
        }

        model.setTask(taskToArchive, archivedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ARCHIVED_TASKS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, archivedTask));
    }

    @Override
    public int hashCode() {
        return editTaskDescriptor.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ArchiveTaskCommand)) {
            return false;
        }

        // state check
        ArchiveTaskCommand m = (ArchiveTaskCommand) other;
        return index.equals(m.index)
                && editTaskDescriptor.equals(m.editTaskDescriptor);
    }
}
