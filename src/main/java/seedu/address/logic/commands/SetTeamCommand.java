package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Team;

/**
 * Sets the current team to an existing team.
 */
public class SetTeamCommand extends Command {
    public static final String COMMAND_WORD = "set_team";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets the current team to an existing team. \n"
            + "Parameters: TEAM_NAME\n"
            + "Example: " + COMMAND_WORD + " project";

    public static final String MESSAGE_SET_TEAM_SUCCESS = "Set current team: %1$s";

    public static final String MESSAGE_TEAM_ALREADY_SET = "You are already on this team!";

    public static final String MESSAGE_TEAM_NOT_EXISTS = "This team you are trying to set does not exist!";

    private final Team targetTeam;

    public SetTeamCommand(Team targetTeam) {
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

        if (currentTeam.equals(targetTeam)) {
            throw new CommandException(MESSAGE_TEAM_ALREADY_SET);
        }
        model.setTeam(teamList.get(teamIndex));
        return new CommandResult(String.format(MESSAGE_SET_TEAM_SUCCESS, targetTeam));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetTeamCommand // instanceof handles nulls
                && targetTeam.equals(((SetTeamCommand) other).targetTeam)); // state check
    }
}
