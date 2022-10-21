package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalNuScheduler.getTypicalNuScheduler;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NuScheduler;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyNuScheduler_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyNuScheduler_success() {
        Model model = new ModelManager(getTypicalNuScheduler(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalNuScheduler(), new UserPrefs());
        expectedModel.setNuScheduler(new NuScheduler());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
