package seedu.codeconnect.logic.commands;

import static seedu.codeconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codeconnect.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.codeconnect.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.codeconnect.model.AddressBook;
import seedu.codeconnect.model.Model;
import seedu.codeconnect.model.ModelManager;
import seedu.codeconnect.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    //should be getTypicalTaskList()
    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
