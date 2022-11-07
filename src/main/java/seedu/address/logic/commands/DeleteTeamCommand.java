package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Team;


/**
 * Deletes a team identified using it's displayed index from the address book.
 */
public class DeleteTeamCommand extends Command {

    public static final String COMMAND_WORD = "delteam";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the team identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TEAM_SUCCESS = "Deleted Team: %1$s";

    private final Index targetIndex;

    public DeleteTeamCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Team> lastShownList = model.getFilteredTeamList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
        }

        Team teamToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTeam(teamToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_TEAM_SUCCESS, teamToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTeamCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTeamCommand) other).targetIndex)); // state check
    }
}
