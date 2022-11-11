package swift.logic.commands;

import static swift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swift.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import swift.model.AddressBook;
import swift.model.Model;
import swift.model.ModelManager;
import swift.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS,
                CommandType.CLEAR, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS,
                CommandType.CLEAR, expectedModel);
    }

}
