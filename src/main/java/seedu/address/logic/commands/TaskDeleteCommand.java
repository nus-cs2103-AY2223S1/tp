package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.team.Team;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;

/**
 *  Deletes a task to a team.
 */
public class TaskDeleteCommand extends Command {

    public static final String COMMAND_WORD = "taskdelete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the specified task to the team "
            + "by the index number used in the displayed team list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TASK_NAME + "TASK-NAME]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK_NAME + "Create GUI for AddressBook";
    public static final String MESSAGE_SUCCESS = "Task deleted: %1$s";

    private final Index index;
    private final Task toDelete;

    /**
     * Creates a TaskDeleteCommand to delete the specified {@code Task}
     *
     * @param index of the team in the filtered team list to edit.
     * @param task task to be deleted.
     */
    public TaskDeleteCommand(Index index, Task task) {
        requireNonNull(index);
        requireNonNull(task);

        this.index = index;
        this.toDelete = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Team> lastShownTeamList = model.getFilteredTeamList();

        if (index.getZeroBased() >= lastShownTeamList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
        }

        model.deleteTask(index, toDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeleteCommand // instanceof handles nulls
                && toDelete.equals(((TaskDeleteCommand) other).toDelete));
    }
}
