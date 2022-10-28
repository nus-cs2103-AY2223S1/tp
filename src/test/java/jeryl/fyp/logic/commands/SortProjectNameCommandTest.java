package jeryl.fyp.logic.commands;

import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jeryl.fyp.logic.commands.SortProjectNameCommand.MESSAGE_SUCCESS;
import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortProjectNameCommand.
 */
public class SortProjectNameCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFypManager(), new UserPrefs());
        expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
    }

    @Test
    public void execute_sortProjectName_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false,
                true, false);
        assertCommandSuccess(new SortProjectNameCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_sortedListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new SortProjectNameCommand(), model,
                SortProjectNameCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
