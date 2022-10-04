package hobbylist.logic.commands;

import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import hobbylist.model.HobbyList;
import hobbylist.model.Model;
import hobbylist.model.ModelManager;
import hobbylist.model.UserPrefs;
import hobbylist.testutil.TypicalActivities;

public class ClearCommandTest {

    @Test
    public void execute_emptyHobbyList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyHobbyList_success() {
        Model model = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());
        expectedModel.setHobbyList(new HobbyList());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
