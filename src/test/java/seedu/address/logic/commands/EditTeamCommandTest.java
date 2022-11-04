package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;
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

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

// TODO: Add implementation for tests
class EditTeamCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = model;
    private final Command commandToBeTested = new EditTeamCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(TeamName.class, new TeamNameConverter())
            .registerConverter(Description.class, new DescriptionConverter());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Team validTeam = TypicalTeams.FIRST;
        commandLine.parseArgs(TeamUtil.convertEditTeamToArgs(validTeam));
        CommandResult expectedResult = new CommandResult(String.format(EditTeamCommand.MESSAGE_EDIT_TEAM_SUCCESS, validTeam));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
}
