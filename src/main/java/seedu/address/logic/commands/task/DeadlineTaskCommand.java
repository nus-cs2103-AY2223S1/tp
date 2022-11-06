package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;

/**
 * A command for users to specify the deadline for a task.
 */
public class DeadlineTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "do";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL + ": Sets a deadline for a task.\n"
            + "Parameters: "
            + "TASK_INDEX (must be a positive integer) "
            + PREFIX_DEADLINE + " DATE...\n\n"
            + "Example: " + COMMAND_WORD_FULL + " "
            + "1 "
            + PREFIX_DEADLINE + " tomorrow\n\n"
            + "You can also use a '?' to remove the deadline from a task.\n"
            + "Example: " + COMMAND_WORD_FULL + " "
            + "1 "
            + PREFIX_DEADLINE + " ?\n";

    public static final String MESSAGE_SUCCESS = "Deadline of %1$s was set to the task: %2$s";

    private final Index targetIndex;
    private final Deadline newDeadline;

    /**
     * @param targetIndex of the teammate's task to be updated
     * @param newDeadline the new deadline for the task
     */
    public DeadlineTaskCommand(Index targetIndex, Deadline newDeadline) {
        requireAllNonNull(targetIndex);

        this.targetIndex = targetIndex;
        this.newDeadline = newDeadline;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToUpdate = lastShownList.get(targetIndex.getZeroBased());

        Task updatedTask = new Task(
                        taskToUpdate.getTitle(),
                        taskToUpdate.getCompleted(),
                        newDeadline,
                        taskToUpdate.getProject(),
                        taskToUpdate.getAssignedContacts()
        );

        model.setTask(taskToUpdate, updatedTask);

        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedTask.getDeadline(), updatedTask.getTitle()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeadlineTaskCommand)) {
            return false;
        }

        // state check
        DeadlineTaskCommand e = (DeadlineTaskCommand) other;

        return targetIndex.equals(e.targetIndex) && newDeadline.equals(e.newDeadline);
    }
}
