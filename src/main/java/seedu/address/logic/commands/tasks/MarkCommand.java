package seedu.address.logic.commands.tasks;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

// @@author connlim
/**
 * Marks a task as complete
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task as completed\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    public static final String COMPLETE_SUCESS = " task %s is marked as complete%n";
    public static final String ALREADY_MARKED = " task %s is already completed%n";

    private final Index targetIndex;

    public MarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Task task = lastShownList.get(targetIndex.getZeroBased());
        Task newTask = task.mark();
        if (newTask == task) {
            throw new CommandException(ALREADY_MARKED);
        }
        model.setTask(task, task.mark());
        return new CommandResult(String.format(COMPLETE_SUCESS, task));
    }
}
