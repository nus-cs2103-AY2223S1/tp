package seedu.address.logic.commands;
/*
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
*/
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
/*
import seedu.address.testutil.CustomerBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_SUCCESS;
*/

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /*
    @Test
    public void execute_undoAfterAddCommand_success() throws CommandException {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        CommandResult commandResult = new AddCommand(new CustomerBuilder().build()).execute(model);

        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false);
        assertCommandSuccess(new UndoCommand(), model, expectedCommandResult, expectedModel);
    }
    */
}
