package seedu.application.logic.commands;

import static seedu.application.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;
import static seedu.application.testutil.TypicalCommands.ADD_COMMAND;
import static seedu.application.testutil.TypicalCommands.CLEAR_COMMAND;
import static seedu.application.testutil.TypicalCommands.DELETE_COMMAND;
import static seedu.application.testutil.TypicalCommands.UNDO_COMMAND;

import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void execute_singlePreviousState_success() {
        CommandTestUtil.executeCommand(CLEAR_COMMAND, model);

        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_multiplePreviousStates_success() {
        CommandTestUtil.executeCommand(ADD_COMMAND, model);
        CommandTestUtil.executeCommand(DELETE_COMMAND, model);
        CommandTestUtil.executeCommand(UNDO_COMMAND, model);

        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_noPreviousStates_failure() {
        UndoCommand undoCommand = new UndoCommand();
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_NO_PREVIOUS_COMMAND);
    }
}
