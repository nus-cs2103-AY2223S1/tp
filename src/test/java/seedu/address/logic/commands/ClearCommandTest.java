package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_throwsCommandException() throws CommandException{
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandFailure(new ClearCommand(), model, ClearCommand.MESSAGE_EMPTY_ADDRESSBOOK);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void undo_commandExecuted_undoSuccessful() throws CommandException{
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        ClearCommand clearCommand = new ClearCommand();
        clearCommand.execute(model);
        CommandResult commandResult = clearCommand.undo(model);

        assertEquals(ClearCommand.MESSAGE_UNDO, commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    @Test
    public void redo_commandUndone_redoSuccessful() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        ClearCommand clearCommand = new ClearCommand();
        clearCommand.execute(model);
        clearCommand.undo(model);
        CommandResult commandResult = clearCommand.redo(model);

        assertEquals(ClearCommand.MESSAGE_REDO, commandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }
}
