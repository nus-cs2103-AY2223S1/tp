package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class GetIdCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

    @Test
    public void execute_indexFound() {
        String expectedMessage = String.format(GetIdCommand.GET_ID_SUCCESS, 1);
        GetIdCommand command = new GetIdCommand("Alice Pauline");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        command = new GetIdCommand("alice pauline");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexNotFound() {
        String expectedMessage = String.format(GetIdCommand.GET_ID_FAILURE);
        GetIdCommand command = new GetIdCommand("Alice");
        assertCommandFailure(command, model, expectedMessage);
    }

}
