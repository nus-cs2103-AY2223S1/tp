package seedu.pennywise.logic.commands;

import static seedu.pennywise.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pennywise.testutil.TypicalEntry.getTypicalPennyWise;

import org.junit.jupiter.api.Test;

import seedu.pennywise.model.Model;
import seedu.pennywise.model.ModelManager;
import seedu.pennywise.model.PennyWise;
import seedu.pennywise.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyPennyWise_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyPennyWise_success() {
        Model model = new ModelManager(getTypicalPennyWise(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPennyWise(), new UserPrefs());
        expectedModel.setPennyWise(new PennyWise());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
