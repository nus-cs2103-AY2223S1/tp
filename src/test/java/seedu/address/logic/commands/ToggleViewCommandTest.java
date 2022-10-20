package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewAll Command.
 */
class ToggleViewCommandTest {
    private Model model;
    private Model expectedModel;

    /**
     * At initialisation, the actual model will show all students' information.
     * Expected Model hides students' parent details.
     */
    @BeforeEach
    void init() {
        model = new ModelManager(getTypicalStudentRecord(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalStudentRecord(), new UserPrefs());
        expectedModel.toggleStudentListInfoConcise();
    }

    @Test
    public void execute_toggleHide() {
        CommandResult expectedCommandResult = new CommandResult(
                ToggleViewCommand.MESSAGE_SUCCESS_HIDE, false, true, false);
        assertCommandSuccess(new ToggleViewCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_toggleHide_thenToggleShow() {
        CommandResult expectedCommandResult1 = new CommandResult(
                ToggleViewCommand.MESSAGE_SUCCESS_HIDE, false, true, false);
        CommandResult expectedCommandResult2 = new CommandResult(
                ToggleViewCommand.MESSAGE_SUCCESS_SHOW, false, true, false);
        Command toggleViewCommand = new ToggleViewCommand();
        // First toggle. Actual model hides students' parent details
        assertCommandSuccess(toggleViewCommand, model, expectedCommandResult1, expectedModel);
        try {
            // Second toggle. Actual model shows all students' information
            expectedModel.toggleStudentListInfoConcise();
            CommandResult result = toggleViewCommand.execute(model);
            assertEquals(model, expectedModel);
            assertEquals(model.getFilteredStudentList(), expectedModel.getFilteredStudentList());
            assertEquals(result, expectedCommandResult2);
        } catch (CommandException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }
}
