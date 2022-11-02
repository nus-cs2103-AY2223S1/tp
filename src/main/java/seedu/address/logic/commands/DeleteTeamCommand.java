package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TEAM_NAME_DESCRIPTION;

import java.util.List;
import java.util.stream.Collectors;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

/**
 * Deletes a team from the addressbook.
 */
@CommandLine.Command(name = DeleteTeamCommand.COMMAND_WORD, aliases = {"te"}, mixinStandardHelpOptions = true)
public class DeleteTeamCommand extends Command {
    public static final String COMMAND_WORD = "team";

    public static final String FULL_COMMAND = DeleteCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = FULL_COMMAND
            + ": Delete an existing team \n"
            + "Parameters: TEAM_NAME\n"
            + "Example: " + FULL_COMMAND + " project";

    public static final String MESSAGE_DELETE_TEAM_SUCCESS = "Deleted team: %1$s";
    public static final String MESSAGE_AT_LEAST_ONE_TEAM = "You must have at least one team!";
    public static final String MESSAGE_TEAM_NOT_EXISTS = "This team you are trying to delete does not exist!";

    @CommandLine.Parameters(arity = "1", description = FLAG_TEAM_NAME_DESCRIPTION)
    private TeamName targetTeamName;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public DeleteTeamCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        List<Team> teamList = model.getTeamList();
        Team currentTeam = model.getTeam();
        Team targetTeam = new Team(targetTeamName);

        List<Team> filteredListWithTargetTeam = teamList.stream()
                .filter(targetTeam::isSameTeam).collect(Collectors.toList());

        if (filteredListWithTargetTeam.size() == 0) {
            throw new CommandException(MESSAGE_TEAM_NOT_EXISTS);
        }

        if (teamList.size() == 1) {
            throw new CommandException(MESSAGE_AT_LEAST_ONE_TEAM);
        }
        assert filteredListWithTargetTeam.size() == 1;
        Team targetTeamInTeamList = filteredListWithTargetTeam.get(0);

        model.deleteTeam(targetTeamInTeamList);
        if (currentTeam.equals(targetTeam)) {
            model.setTeam(model.getTeamList().get(0));
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TEAM_SUCCESS, targetTeamInTeamList));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTeamCommand // instanceof handles nulls
                && targetTeamName.equals(((DeleteTeamCommand) other).targetTeamName)); // state check
    }

}
