package seedu.condonery.logic.commands.client;

import static seedu.condonery.testutil.TypicalClients.getTypicalClientDirectory;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.CommandTestUtil;
import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.client.ClientDirectory;

public class ClearClientCommandTest {

    @Test
    public void execute_emptyClientDirectory_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearClientCommand(), model,
                ClearClientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyClientDirectory_success() {
        Model model = new ModelManager(getTypicalPropertyDirectory(), getTypicalClientDirectory(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPropertyDirectory(),
                getTypicalClientDirectory(), new UserPrefs());
        expectedModel.setClientDirectory(new ClientDirectory());

        CommandTestUtil.assertCommandSuccess(new ClearClientCommand(), model,
                ClearClientCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
