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

import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;

public class RedoCommandTest {
    private Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @BeforeEach
    public void setUp() throws CommandException {
        ADD_COMMAND.execute(model);
        ADD_COMMAND.execute(expectedModel);
        DELETE_COMMAND.execute(model);
        DELETE_COMMAND.execute(expectedModel);
    }

    @Test
    public void execute_singlePreviousState_success() throws CommandException {
        UNDO_COMMAND.execute(model);

        assertCommandSuccess(REDO_COMMAND, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_multiplePreviousStates_success() throws CommandException {
        UNDO_COMMAND.execute(model);
        UNDO_COMMAND.execute(model);
        REDO_COMMAND.execute(model);

        assertCommandSuccess(REDO_COMMAND, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_noPreviousStates_failure() {
        assertCommandFailure(REDO_COMMAND, model, RedoCommand.MESSAGE_NO_PREVIOUSLY_UNDONE);
    }
}
