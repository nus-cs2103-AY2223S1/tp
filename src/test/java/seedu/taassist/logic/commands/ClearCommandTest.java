package seedu.taassist.logic.commands;

import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.testutil.TypicalStudents.getTypicalTaAssist;

import org.junit.jupiter.api.Test;

import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyTaAssist_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTaAssist_success() {
        Model model = new ModelManager(getTypicalTaAssist(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTaAssist(), new UserPrefs());
        expectedModel.setTaAssist(new TaAssist());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
