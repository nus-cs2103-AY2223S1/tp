package seedu.condonery.logic.commands;

import static seedu.condonery.testutil.TypicalClients.getTypicalClientDirectory;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.property.ClearPropertyCommand;
import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.property.PropertyDirectory;

public class ClearPropertyCommandTest {

    @Test
    public void execute_emptyPropertyDirectory_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearPropertyCommand(), model,
                ClearPropertyCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyPropertyDirectory_success() {
        Model model = new ModelManager(getTypicalPropertyDirectory(), getTypicalClientDirectory(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPropertyDirectory(),
                getTypicalClientDirectory(), new UserPrefs());
        expectedModel.setPropertyDirectory(new PropertyDirectory());

        CommandTestUtil.assertCommandSuccess(new ClearPropertyCommand(), model,
                ClearPropertyCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
