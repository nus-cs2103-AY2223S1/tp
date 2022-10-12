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

public class RmTaskCommand extends Command {
    public static final String COMMAND_WORD = "rmTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the selected task\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    public static final String DELETE_SUCCESS = " task %s is deleted%n";

    private final Index targetIndex;

    public RmTaskCommand(Index targetIndex) {
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
        model.deleteTask(task);
        return new CommandResult(String.format(DELETE_SUCCESS, task));
    }
}
