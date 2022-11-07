package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        Ui stubUi = new StubUiManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel, stubUi);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());
        Ui stubUi = new StubUiManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel, stubUi);
    }

}
