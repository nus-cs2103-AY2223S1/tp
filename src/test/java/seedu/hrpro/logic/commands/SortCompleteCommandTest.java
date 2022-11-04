package seedu.hrpro.logic.commands;

import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hrpro.testutil.TypicalHrPro.getTypicalHrPro;

import org.junit.jupiter.api.Test;

import seedu.hrpro.model.Model;
import seedu.hrpro.model.ModelManager;
import seedu.hrpro.model.UserPrefs;

public class SortCompleteCommandTest {
    @Test
    public void execute_emptyHrPro_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new SortCompleteCommand(), model,
                SortCompleteCommand.MESSAGE_FILTER_TASK_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyHrPro_success() {
        Model model = new ModelManager(getTypicalHrPro(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalHrPro(), new UserPrefs());
        expectedModel.sortComplete();

        assertCommandSuccess(new SortCompleteCommand(), model,
                SortCompleteCommand.MESSAGE_FILTER_TASK_SUCCESS, expectedModel);
    }
}
