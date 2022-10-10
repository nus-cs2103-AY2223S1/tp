package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Team;

import static java.util.Objects.requireNonNull;

/**
 * Add a team to the address book.
 */
public class AddTeamCommand extends Command {

    public static final String COMMAND_WORD = "mkteam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a new team in the current "
            + "team scope if the team name does not currently exist. The team name must not have "
            + "any backslash (\\) or whitespace in the name. \n"
            + "Parameters: team_name/team_within_team_name\n"
            + "Example: " + COMMAND_WORD + " team_1/team_a";

    public static final String MESSAGE_SUCCESS = "New team added: %1$s";
    public static final String MESSAGE_DUPLICATE_TEAM = "This team already exists in the address book";

    private final Team toAdd;

    /**
     * Creates a AddTeamCommand to add the specified {@Code Team}
     */
    public AddTeamCommand(Team team) {
        requireNonNull(team);
        toAdd = team;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //TODO check if there is any team in model. If no, then addTeam(toAdd).

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
