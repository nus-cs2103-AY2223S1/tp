package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTeams.FRONTEND;
import static seedu.address.testutil.TypicalTeams.getTypicalAddressBookWithTeams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.Name;
import seedu.address.model.team.Team;
import seedu.address.testutil.TeamBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class CreateTeamCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());
    }

    @Test
    public void execute_newTeam_success() {
        Team front = new TeamBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addTeam(front);

        assertCommandSuccess(new CreateTeamCommand(front), model,
                String.format(CreateTeamCommand.MESSAGE_SUCCESS, front), expectedModel);
    }

    @Test
    public void execute_duplicateTeam_throwsCommandException() {
        Team teamInList = model.getAddressBook().getTeamList().get(0);
        assertCommandFailure(new CreateTeamCommand(teamInList), model, CreateTeamCommand.MESSAGE_DUPLICATE_TEAM);
    }

}
