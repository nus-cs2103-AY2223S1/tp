package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalJeeqTracker;

import org.junit.jupiter.api.Test;

import seedu.address.model.JeeqTracker;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyJeeqTracker_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyJeeqTracker_success() {
        Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());
        expectedModel.setJeeqTracker(new JeeqTracker());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
