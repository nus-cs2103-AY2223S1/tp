package seedu.pennyWise.logic.commands;

import static seedu.pennyWise.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pennyWise.testutil.TypicalEntry.getTypicalPennyWise;

import org.junit.jupiter.api.Test;

import seedu.pennyWise.model.Model;
import seedu.pennyWise.model.ModelManager;
import seedu.pennyWise.model.PennyWise;
import seedu.pennyWise.model.UserPrefs;

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
