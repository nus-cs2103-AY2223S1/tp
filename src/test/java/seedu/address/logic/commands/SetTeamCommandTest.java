package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;
import static seedu.address.testutil.TypicalTeams.FIRST_TEAM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.TeamNameConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.TeamName;

class SetTeamCommandTest {
    private final Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Command commandToBeTested = new SetTeamCommand();

    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(TeamName.class, new TeamNameConverter());
    @BeforeEach
    public void setUp() {
        model.addTeam(FIRST_TEAM);
        expectedModel.addTeam(FIRST_TEAM);
    }

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(
                SetTeamCommand.HELP_MESSAGE + commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_setTeamToNewTeam_success() {
        commandLine.parseArgs("first");
        expectedModel.setTeam(FIRST_TEAM);
        CommandResult expectedResult = new CommandResult(
                String.format(SetTeamCommand.MESSAGE_SET_TEAM_SUCCESS, FIRST_TEAM));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_setToTeamNotExist_throwsCommandException() {
        commandLine.parseArgs("second");
        assertThrows(CommandException.class, SetTeamCommand.MESSAGE_TEAM_NOT_EXISTS, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_setTeamAlreadySet_throwsCommandException() {
        commandLine.parseArgs("default");
        assertThrows(CommandException.class, SetTeamCommand.MESSAGE_TEAM_ALREADY_SET, ()
                -> commandToBeTested.execute(model));
    }

}
