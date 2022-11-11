package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddTeamCommand.MESSAGE_ADD_TEAM_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DescriptionConverter;
import seedu.address.logic.parser.TeamNameConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.Description;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;
import seedu.address.testutil.TeamUtil;
import seedu.address.testutil.TypicalTeams;

class AddTeamCommandTest {

    private final Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Command commandToBeTested = new AddTeamCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(TeamName.class, new TeamNameConverter())
            .registerConverter(Description.class, new DescriptionConverter());
    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(
                AddTeamCommand.HELP_MESSAGE + commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_teamAcceptedByModel_addSuccessful() throws Exception {
        Team validTeam = TypicalTeams.FIRST_TEAM;
        expectedModel.addTeam(validTeam);
        commandLine.parseArgs(TeamUtil.convertTeamToArgs(validTeam));
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_ADD_TEAM_SUCCESS, validTeam));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    void execute_teamAlreadyExist_throwsCommandException() {
        Team defaultTeam = TypicalTeams.DEFAULT_TEAM;
        commandLine.parseArgs(TeamUtil.convertTeamToArgs(defaultTeam));
        assertThrows(CommandException.class, AddTeamCommand.MESSAGE_TEAM_EXISTS, ()
                -> commandToBeTested.execute(model));
    }
}
