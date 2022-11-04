package seedu.hrpro.logic.commands;

import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hrpro.testutil.TypicalHRPro.getTypicalHRPro;

import org.junit.jupiter.api.Test;

import seedu.hrpro.model.Model;
import seedu.hrpro.model.ModelManager;
import seedu.hrpro.model.UserPrefs;

public class SortTaskCommandTest {

    @Test
    public void execute_emptyHRPro_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new SortTaskCommand(), model, SortTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyHRPro_success() {
        Model model = new ModelManager(getTypicalHRPro(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalHRPro(), new UserPrefs());
        expectedModel.sortTasks();

        assertCommandSuccess(new SortTaskCommand(), model, SortTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
