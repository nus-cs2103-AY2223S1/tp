package friday.logic.commands;

import static friday.logic.commands.CommandTestUtil.assertCommandSuccess;
import static friday.testutil.TypicalStudents.getTypicalFriday;

import org.junit.jupiter.api.Test;

import friday.model.Friday;
import friday.model.Model;
import friday.model.ModelManager;
import friday.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFriday_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFriday_success() {
        Model model = new ModelManager(getTypicalFriday(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFriday(), new UserPrefs());
        expectedModel.setFriday(new Friday());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
