package seedu.condonery.logic.commands;

import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

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

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyPropertyDirectory_success() {
        Model model = new ModelManager(getTypicalPropertyDirectory(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPropertyDirectory(), new UserPrefs());
        expectedModel.setPropertyDirectory(new PropertyDirectory());

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
