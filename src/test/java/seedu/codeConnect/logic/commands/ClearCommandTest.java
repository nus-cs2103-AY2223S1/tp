package seedu.codeConnect.logic.commands;

import static seedu.codeConnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codeConnect.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.codeConnect.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.codeConnect.model.AddressBook;
import seedu.codeConnect.model.Model;
import seedu.codeConnect.model.ModelManager;
import seedu.codeConnect.model.UserPrefs;

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
