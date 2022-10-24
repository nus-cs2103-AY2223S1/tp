package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Team;

/**
 * Adds a new team to the address book.
 */
@CommandLine.Command(name = "team")
public class AddTeamCommand extends Command {
    public static final String COMMAND_WORD = "add team";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a new team \n"
            + "Parameters: TEAM_NAME\n"
            + "Example: " + COMMAND_WORD + " project";

    public static final String MESSAGE_ADD_TEAM_SUCCESS = "Added team: %1$s";

    public static final String MESSAGE_TEAM_EXISTS = "There is already an existing team with the same name!";

    @CommandLine.Parameters(arity = "1")
    private String teamName;

    public AddTeamCommand() {
    }

    public AddTeamCommand(Team targetTeam) {
        this.teamName = targetTeam.getTeamName();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Team team = new Team(teamName);
        List<Team> teamList = model.getTeamList();
        if (teamList.contains(team)) {
            throw new CommandException(MESSAGE_TEAM_EXISTS);
        }

        model.addTeam(team);
        return new CommandResult(String.format(MESSAGE_ADD_TEAM_SUCCESS, team));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTeamCommand // instanceof handles nulls
                && teamName.equals(((AddTeamCommand) other).teamName)); // state check
    }

}
