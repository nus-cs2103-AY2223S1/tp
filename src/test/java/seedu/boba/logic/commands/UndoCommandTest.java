package seedu.boba.logic.commands;

import org.junit.jupiter.api.Test;

import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.logic.commands.UndoCommand.MESSAGE_UNDO_SUCCESS;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;

import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.UserPrefs;

import seedu.boba.testutil.CustomerBuilder;


public class UndoCommandTest {
    private BobaBotModel bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());


    @Test
    public void execute_undoAfterAddCommand_success() throws CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

        CommandResult commandResult = new AddCommand(new CustomerBuilder().build()).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }

    /*@Test
    public void execute_undoAfterEditCommand_success() throws Exception {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        EditCommand.EditPersonDescriptor pd = new EditCommand.EditPersonDescriptor();
        pd.setEmail(new Email("a@b.com"));
        System.out.println(new Email(new CustomerBuilder().build().getEmail().toString()));
        CommandResult commandResult = new EditCommand(new Email(new CustomerBuilder().build().getEmail().toString()), pd).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }*/
}
