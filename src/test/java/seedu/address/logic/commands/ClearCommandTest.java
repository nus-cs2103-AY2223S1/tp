package seedu.uninurse.logic.commands;

import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import org.junit.jupiter.api.Test;

import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyUninurseBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyUninurseBook_success() {
        Model model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalUninurseBook(), new UserPrefs());
        expectedModel.setUninurseBook(new UninurseBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
