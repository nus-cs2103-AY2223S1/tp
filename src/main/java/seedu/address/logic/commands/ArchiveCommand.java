package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Archives a task identified using it's displayed index from the task list.
 */
public class ArchiveCommand extends Command {

    public static final String COMMAND_WORD = "archive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": archives the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: archive 1";

    public static final String MESSAGE_ARCHIVED_TASK_SUCCESS = "Task archived!\n" + "Task: %1$s";

    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the archives. Removing task.";

    private final Index index;

    /**
     * @param index displayed index from the task list.
     */
    public ArchiveCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }



    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Task taskToArchive = lastShownList.get(index.getZeroBased());

        if (model.hasTaskInArchives(taskToArchive)) {
            model.deletePerson(taskToArchive);
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.archivedTask(taskToArchive);
        return new CommandResult(String.format(MESSAGE_ARCHIVED_TASK_SUCCESS, taskToArchive));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveCommand // instanceof handles nulls
                && index.equals(((ArchiveCommand) other).index)); // state check
    }

}
