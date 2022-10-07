package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.team.Team;

/**
 * Adds a task to a team.
 */
public class TaskMarkCommand extends Command {

    public static final String COMMAND_WORD = "taskmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the specified task to the team as done "
            + "by the index number used in the displayed team list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TASK_NAME + "TASK-NAME]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK_NAME + "Create GUI for AddressBook";
    public static final String MESSAGE_SUCCESS = "New task marked as done: %1$s";

    private final Index index;
    private final Task toAdd;

    /**
     * Creates a TaskMarkCommand to mark the specified {@code Task} as done
     *
     * @param index of the team in the filtered team list to edit.
     * @param task task to be added.
     */
    public TaskMarkCommand(Index index, Task task) {
        requireNonNull(index);
        requireNonNull(task);

        this.index = index;
        this.toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Team> lastShownTeamList = model.getFilteredTeamList();

        if (index.getZeroBased() >= lastShownTeamList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
        }

        model.markTask(index, toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskMarkCommand // instanceof handles nulls
                && toAdd.equals(((TaskMarkCommand) other).toAdd));
    }
}
