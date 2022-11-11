package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_DESCRIPTION_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_DESCRIPTION_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TEAM_DESCRIPTION_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_TEAM_NAME_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.LABEL_TEAM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.LABEL_TEAM_NAME;

import java.util.List;
import java.util.stream.Collectors;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Description;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

/**
 * Adds a new team to the TruthTable.
 */
@CommandLine.Command(name = AddTeamCommand.COMMAND_WORD,
        aliases = {AddTeamCommand.ALIAS}, mixinStandardHelpOptions = true)
public class AddTeamCommand extends Command {
    public static final String COMMAND_WORD = "team";
    public static final String ALIAS = "te";
    public static final String FULL_COMMAND = AddCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to create a new team in TruthTable.\n";

    public static final String MESSAGE_ADD_TEAM_SUCCESS = "Added team: %1$s";
    public static final String MESSAGE_TEAM_EXISTS = "There is already an existing team with the same name!";

    @CommandLine.Parameters(arity = "1", paramLabel = LABEL_TEAM_NAME,
            description = FLAG_TEAM_NAME_DESCRIPTION)
    private TeamName teamName;

    @CommandLine.Option(names = {FLAG_DESCRIPTION_STR, FLAG_DESCRIPTION_LONG}, defaultValue =
            Description.NO_DESCRIPTION_STRING, paramLabel = LABEL_TEAM_DESCRIPTION,
            description = FLAG_TEAM_DESCRIPTION_DESCRIPTION)
    private Description description;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public AddTeamCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        Team targetTeam = new Team(teamName, description);
        List<Team> teamList = model.getTeamList();
        List<Team> filteredListWithTargetTeam = teamList.stream()
                .filter(targetTeam::isSameTeam).collect(Collectors.toList());

        if (filteredListWithTargetTeam.size() == 1) {
            throw new CommandException(MESSAGE_TEAM_EXISTS);
        }

        model.addTeam(targetTeam);
        return new CommandResult(String.format(MESSAGE_ADD_TEAM_SUCCESS, targetTeam));
    }

}
