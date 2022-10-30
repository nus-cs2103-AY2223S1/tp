// @@author mohamedsaf1
package seedu.address.logic.commands.tasks;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attribute.Progress;
import seedu.address.model.task.Task;

/**
 * Class that represents command for Progress.
 */
public class ProgressCommand extends TaskCommand {
    public static final String SUBCOMMAND_WORD = "progress";

    public static final String MESSAGE_USAGE = TaskCommand.getFullCommand(SUBCOMMAND_WORD)
            + ": Sets the progress of  the task\n"
            + "Parameters: INDEX (must be a positive integer) and LEVEL (25%, 50%, 75% or 100%)\n"
            + "Example: " + COMMAND_WORD + " 1" + " 25%\n";
    public static final String COMPLETE_SUCCESS = " progress for task %s is set.%n";
    public static final String ALREADY_SET = " progess for task %s has already been set!%n";

    private final Index targetIndex;
    private final Progress level;

    /**
     * Constructor for progress command
     * 
     * @param targetIndex
     * @param level
     */
    public ProgressCommand(Index targetIndex, Progress level) {
        this.targetIndex = targetIndex;
        this.level = level;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Task task = lastShownList.get(targetIndex.getZeroBased());
        Task editedTask = task.setProgress(String.valueOf(level));
        if (editedTask == task) {
            throw new CommandException(ALREADY_SET);
        }
        model.setTask(task, task.setProgress(String.valueOf(level)));
        return new CommandResult(String.format(COMPLETE_SUCCESS, task));
    }
}
