package seedu.application.logic.commands;

import static seedu.application.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;
import static seedu.application.testutil.TypicalCommands.ADD_COMMAND;
import static seedu.application.testutil.TypicalCommands.DELETE_COMMAND;
import static seedu.application.testutil.TypicalCommands.REDO_COMMAND;
import static seedu.application.testutil.TypicalCommands.UNDO_COMMAND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;

public class RedoCommandTest {
    private Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        CommandTestUtil.executeCommand(ADD_COMMAND, model);
        CommandTestUtil.executeCommand(ADD_COMMAND, expectedModel);
        CommandTestUtil.executeCommand(DELETE_COMMAND, model);
        CommandTestUtil.executeCommand(DELETE_COMMAND, expectedModel);
    }

    @Test
    public void execute_singlePreviousState_success() {
        CommandTestUtil.executeCommand(UNDO_COMMAND, model);

        RedoCommand redoCommand = new RedoCommand();
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_multiplePreviousStates_success() {
        CommandTestUtil.executeCommand(UNDO_COMMAND, model);
        CommandTestUtil.executeCommand(UNDO_COMMAND, model);
        CommandTestUtil.executeCommand(REDO_COMMAND, model);

        RedoCommand redoCommand = new RedoCommand();
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_noPreviousStates_failure() {
        RedoCommand redoCommand = new RedoCommand();
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_NO_PREVIOUSLY_UNDONE);
    }
}
