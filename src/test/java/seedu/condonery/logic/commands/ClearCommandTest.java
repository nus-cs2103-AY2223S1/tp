package seedu.condonery.logic.commands;

import static seedu.condonery.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.condonery.testutil.TypicalPersons.getTypicalPropertyDirectory;

import org.junit.jupiter.api.Test;

import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.PropertyDirectory;
import seedu.condonery.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyPropertyDirectory_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyPropertyDirectory_success() {
        Model model = new ModelManager(getTypicalPropertyDirectory(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPropertyDirectory(), new UserPrefs());
        expectedModel.setPropertyDirectory(new PropertyDirectory());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
