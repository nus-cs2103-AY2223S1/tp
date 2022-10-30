package seedu.boba.logic.commands;
/*
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
*/

import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.UserPrefs;

import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;
/*
import seedu.address.testutil.CustomerBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_SUCCESS;
*/

public class UndoCommandTest {
    private final BobaBotModel bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

    /*
    @Test
    public void execute_undoAfterAddCommand_success() throws CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

        CommandResult commandResult = new AddCommand(new CustomerBuilder().build()).execute(bobaBotModel);

        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false);
        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }
    */
}
