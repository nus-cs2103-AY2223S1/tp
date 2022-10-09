package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Team;
/**
 * Deletes a team from the addressbook.
 */
public class DeleteTeamCommand extends Command {
    public static final String COMMAND_WORD = "delete_team";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete an existing team \n"
            + "Parameters: TEAM_NAME\n"
            + "Example: " + COMMAND_WORD + " project";

    public static final String MESSAGE_DELETE_TEAM_SUCCESS = "Deleted team: %1$s";
    public static final String MESSAGE_AT_LEAST_ONE_TEAM = "You must have at least one team!";
    public static final String MESSAGE_TEAM_NOT_EXISTS = "This team you are trying to delete does not exist!";

    private final Team targetTeam;

    public DeleteTeamCommand(Team targetTeam) {
        this.targetTeam = targetTeam;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Team> teamList = model.getTeamList();
        Team currentTeam = model.getTeam();
        int teamIndex = teamList.indexOf(targetTeam);
        if (teamIndex == -1) {
            throw new CommandException(MESSAGE_TEAM_NOT_EXISTS);
        }

        if (teamList.size() == 1) {
            throw new CommandException(MESSAGE_AT_LEAST_ONE_TEAM);
        }

        model.deleteTeam(teamList.get(teamIndex));
        if (currentTeam.equals(targetTeam)) {
            model.setTeam(model.getTeamList().get(0));
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TEAM_SUCCESS, targetTeam));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTeamCommand // instanceof handles nulls
                && targetTeam.equals(((DeleteTeamCommand) other).targetTeam)); // state check
    }
}
