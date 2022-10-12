package seedu.address.logic.commands;

import static seedu.address.logic.commands.OldCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.OldModel;
import seedu.address.model.OldModelManager;
import seedu.address.model.UserPrefs;

public class OldClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        OldModel model = new OldModelManager();
        OldModel expectedModel = new OldModelManager();

        assertCommandSuccess(new OldClearCommand(), model, OldClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        OldModel model = new OldModelManager(getTypicalAddressBook(), new UserPrefs());
        OldModel expectedModel = new OldModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new OldClearCommand(), model, OldClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
