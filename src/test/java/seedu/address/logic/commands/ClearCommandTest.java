package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplications.getTypicalApplicationBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.ApplicationBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyApplicationBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyApplicationBook_success() {
        Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
        expectedModel.setApplicationBook(new ApplicationBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
