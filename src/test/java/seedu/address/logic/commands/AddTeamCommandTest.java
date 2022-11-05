package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddTeamCommand.MESSAGE_ADD_TEAM_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
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

// TODO: Add implementation for tests
class AddTeamCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = model;
    private final Command commandToBeTested = new AddTeamCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(TeamName.class, new TeamNameConverter())
            .registerConverter(Description.class, new DescriptionConverter());

    @Test
    public void execute_teamAcceptedByModel_addSuccessful() throws Exception {
        Team validTeam = TypicalTeams.FIRST;
        commandLine.parseArgs(TeamUtil.convertTeamToArgs(validTeam));
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_ADD_TEAM_SUCCESS, validTeam));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    void execute_teamAlreadyExist_throwsCommandException() {
        Team defaultTeam = TypicalTeams.DEFAULT;
        commandLine.parseArgs(TeamUtil.convertTeamToArgs(defaultTeam));
        assertThrows(CommandException.class, AddTeamCommand.MESSAGE_TEAM_EXISTS,
                () -> commandToBeTested.execute(model));
    }
}
