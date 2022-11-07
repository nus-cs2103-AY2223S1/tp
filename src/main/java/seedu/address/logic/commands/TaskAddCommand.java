package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_INDEX;

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
public class TaskAddCommand extends Command {

    public static final String COMMAND_WORD = "taskadd";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the specified task to the team "
            + "by the index number used in the displayed team list.\n"
            + "Parameters: "
            + PREFIX_TEAM_INDEX + "INDEX (must be a positive integer) "
            + PREFIX_TASK_NAME + "TASK-NAME"
            + " [" + PREFIX_TASK_DEADLINE + "DD-MM-YYYY]"
            + " (It's optional to include deadline for a task!) \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TEAM_INDEX + "1 "
            + PREFIX_TASK_NAME + "Create GUI for AddressBook "
            + PREFIX_TASK_DEADLINE + "12-12-2023";
    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task is already assigned to the team";

    private final Index index;
    private final Task toAdd;

    /**
     * Creates a TaskAddCommand to add the specified {@code Task}
     *
     * @param index of the team in the filtered team list to edit.
     * @param task task to be added.
     */
    public TaskAddCommand(Index index, Task task) {
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

        if (model.teamHasTask(index, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(index, toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskAddCommand // instanceof handles nulls
                && toAdd.equals(((TaskAddCommand) other).toAdd));
    }
}
