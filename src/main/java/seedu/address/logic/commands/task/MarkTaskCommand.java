package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

import java.util.List;


/**
 * Marks a task of a person as complete or incomplete.
 */
public class MarkTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "mark";

    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark the task complete by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Task %1$d is marked as complete";

    private final Index task_index;

    /**
     * @param task_index of the person's task to be updated
     */
    public MarkTaskCommand(Index task_index) {
        requireAllNonNull(task_index);

        this.task_index = task_index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (task_index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Task taskToMark = lastShownList.get(task_index.getZeroBased());
        model.markTask(taskToMark);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task_index.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkTaskCommand)) {
            return false;
        }

        // state check
        MarkTaskCommand e = (MarkTaskCommand) other;
        return task_index.equals(e.task_index);
    }
}
