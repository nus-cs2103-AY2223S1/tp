package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalFindMyIntern;

import org.junit.jupiter.api.Test;

import seedu.address.model.FindMyIntern;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFindMyIntern_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFindMyIntern_success() {
        Model model = new ModelManager(getTypicalFindMyIntern(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFindMyIntern(), new UserPrefs());
        expectedModel.setFindMyIntern(new FindMyIntern());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
