package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.teammate.Teammate;
import seedu.address.testutil.TeammateBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());
    }

    @Test
    public void execute_newTeammate_success() {
        Teammate validTeammate = new TeammateBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTaskPanel(), new UserPrefs());
        expectedModel.addTeammate(validTeammate);

        assertCommandSuccess(new AddCommand(validTeammate), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validTeammate), expectedModel);
    }

    @Test
    public void execute_duplicateTeammate_throwsCommandException() {
        Teammate teammateInList = model.getAddressBook().getTeammateList().get(0);
        assertCommandFailure(new AddCommand(teammateInList), model, AddCommand.MESSAGE_DUPLICATE_TEAMMATE);
    }

}
