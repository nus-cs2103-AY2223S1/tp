package seedu.studmap.logic.commands;

import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import org.junit.jupiter.api.Test;

import seedu.studmap.model.Model;
import seedu.studmap.model.ModelManager;
import seedu.studmap.model.StudMap;
import seedu.studmap.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyStudMap_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyStudMap_success() {
        Model model = new ModelManager(getTypicalStudMap(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalStudMap(), new UserPrefs());
        expectedModel.setStudMap(new StudMap());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
