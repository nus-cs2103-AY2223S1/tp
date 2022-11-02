package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TEAM_NAME_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.stream.Collectors;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

/**
 * Sets the current team to an existing team.
 */
@CommandLine.Command(name = SetTeamCommand.COMMAND_WORD,
        aliases = {SetTeamCommand.ALIAS}, mixinStandardHelpOptions = true)
public class SetTeamCommand extends Command {
    public static final String COMMAND_WORD = "team";
    public static final String ALIAS = "te";
    public static final String FULL_COMMAND = SetCommand.COMMAND_WORD + " " + COMMAND_WORD;


    public static final String MESSAGE_USAGE = FULL_COMMAND
            + ": Sets the current team to an existing team. \n"
            + "Parameters: TEAM_NAME\n"
            + "Example: " + FULL_COMMAND + " project";

    public static final String MESSAGE_SET_TEAM_SUCCESS = "Set current team: %1$s";

    public static final String MESSAGE_TEAM_ALREADY_SET = "You are already on this team!";

    public static final String MESSAGE_TEAM_NOT_EXISTS = "This team you are trying to set does not exist!";

    @CommandLine.Parameters(arity = "1", description = FLAG_TEAM_NAME_DESCRIPTION)
    private TeamName targetTeamName;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public SetTeamCommand() {
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
        assert filteredListWithTargetTeam.size() == 1;
        Team targetTeamInTeamList = filteredListWithTargetTeam.get(0);

        if (currentTeam.equals(targetTeamInTeamList)) {
            throw new CommandException(MESSAGE_TEAM_ALREADY_SET);
        }

        model.setTeam(targetTeamInTeamList);
        model.updateFilteredMembersList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_SET_TEAM_SUCCESS, targetTeamInTeamList));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetTeamCommand // instanceof handles nulls
                && targetTeamName.equals(((SetTeamCommand) other).targetTeamName)); // state check
    }

}
