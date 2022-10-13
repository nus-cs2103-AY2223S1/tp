package coydir.logic.commands;

import static coydir.logic.commands.CommandTestUtil.assertCommandSuccess;
import static coydir.testutil.TypicalPersons.getTypicalDatabase;

import org.junit.jupiter.api.Test;

import coydir.model.Database;
import coydir.model.Model;
import coydir.model.ModelManager;
import coydir.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel.setDatabase(new Database());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
