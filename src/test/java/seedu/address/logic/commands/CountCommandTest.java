package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class CountCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_countCommand_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(CountCommand.UI_CONFIRMATION, true);
        assertCommandSuccess(new CountCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        CountCommand countCommand = new CountCommand();
        CountCommand countCommandDifferent = new CountCommand();
        assertEquals(countCommand, countCommandDifferent);
    }
}
