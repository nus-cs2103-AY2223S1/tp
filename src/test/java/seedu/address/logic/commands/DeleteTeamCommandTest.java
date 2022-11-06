package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;
import static seedu.address.testutil.TypicalTasks.TASK_CODE;
import static seedu.address.testutil.TypicalTeams.FIRST;
import static seedu.address.testutil.TypicalTeams.SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.TeamNameConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.TeamName;

class DeleteTeamCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Command commandToBeTested = new DeleteTeamCommand();

    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(TeamName.class, new TeamNameConverter());

    @BeforeEach
    public void setUp() {
        model.addTeam(FIRST);
        expectedModel.addTeam(FIRST);
    }

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_deleteTeamInList_success() {
        commandLine.parseArgs(new String[] {"first"});
        expectedModel.deleteTeam(FIRST);
        CommandResult expectedResult = new CommandResult(
                String.format(DeleteTeamCommand.MESSAGE_DELETE_TEAM_SUCCESS, FIRST));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_deleteTeamNotExist_throwsCommandException() {
        commandLine.parseArgs(new String[] {"second"});
        assertThrows(CommandException.class, DeleteTeamCommand.MESSAGE_TEAM_NOT_EXISTS, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_deleteOnlyTeam_throwsCommandException() {
        model.deleteTeam(FIRST);
        commandLine.parseArgs(new String[] {"default"});
        assertThrows(CommandException.class, DeleteTeamCommand.MESSAGE_AT_LEAST_ONE_TEAM, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void testEquals() {
    }
}
