package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.TeamPredicate;
import seedu.address.model.team.Team;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListMembersCommand.
 */
public class ListMembersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listMembers_success() {
        List<Team> teams = new ArrayList<>();
        teams.add(expectedModel.getTeam());
        expectedModel.updateFilteredPersonList(new TeamPredicate(teams));

        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
            model.getTeam().getTeamMembers().size());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);

        assertCommandSuccess(new ListMembersCommand(), model, expectedCommandResult, expectedModel);
    }
}
