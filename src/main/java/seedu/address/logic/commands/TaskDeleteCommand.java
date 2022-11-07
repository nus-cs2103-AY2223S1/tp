package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.team.Team;

/**
 *  Deletes a task to a team.
 */
public class TaskDeleteCommand extends Command {

    public static final String COMMAND_WORD = "taskdelete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the specified task to the team "
            + "by the index number used in the displayed team list.\n"
            + "Parameters: " + PREFIX_TEAM_INDEX + "TEAM-INDEX (must be a positive integer), "
            + PREFIX_TASK_INDEX + "TASK-INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TEAM_INDEX + "1 "
            + PREFIX_TASK_INDEX + "3";
    public static final String MESSAGE_SUCCESS = "Task deleted: %1$s";

    private final Index taskIndex;
    private final Index teamIndex;

    /**
     * Creates a TaskDeleteCommand to delete the specified {@code Task}
     *
     * @param teamIndex of the team in the filtered team list to edit.
     * @param taskIndex index of the task to be deleted.
     */
    public TaskDeleteCommand(Index teamIndex, Index taskIndex) {
        requireNonNull(taskIndex);
        requireNonNull(teamIndex);

        this.teamIndex = teamIndex;
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Team> lastShownTeamList = model.getFilteredTeamList();

        if (teamIndex.getZeroBased() >= lastShownTeamList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
        }

        if (lastShownTeamList.get(teamIndex.getZeroBased()).getTasks().getSize() == 0) {
            throw new CommandException(Messages.MESSAGE_EMPTY_TASK_LIST);
        }

        if (taskIndex.getZeroBased() >= lastShownTeamList.get(teamIndex.getZeroBased()).getTasks().getSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = lastShownTeamList.get(teamIndex.getZeroBased()).getTask(taskIndex.getZeroBased());
        model.deleteTask(teamIndex, taskIndex);
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeleteCommand // instanceof handles nulls
                && taskIndex.equals(((TaskDeleteCommand) other).taskIndex)
                && teamIndex.equals(((TaskDeleteCommand) other).teamIndex));
    }
}
