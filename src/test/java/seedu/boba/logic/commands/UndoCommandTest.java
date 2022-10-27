package seedu.boba.logic.commands;
/*
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
*/
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;

import seedu.boba.model.Model;
import seedu.boba.model.ModelManager;
import seedu.boba.model.UserPrefs;
/*
import seedu.address.testutil.CustomerBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_SUCCESS;
*/

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalBobaBot(), new UserPrefs());

    /*
    @Test
    public void execute_undoAfterAddCommand_success() throws CommandException {
        Model expectedModel = new ModelManager(getTypicalBobaBot(), new UserPrefs());

        CommandResult commandResult = new AddCommand(new CustomerBuilder().build()).execute(model);

        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false);
        assertCommandSuccess(new UndoCommand(), model, expectedCommandResult, expectedModel);
    }
    */
}
