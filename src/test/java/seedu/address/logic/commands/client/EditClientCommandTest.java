package seedu.address.logic.commands.client;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.client.EditClientCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientMobile;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;

class EditClientCommandTest {

    private final Ui stubUi = new StubUiManager();

    @Test
    public void execute_editClient_success() {
        Model actualModel = new ModelManager();
        Client beforeEditClient = new Client(new Name("John"), new ClientMobile("12345678"),
                new ClientEmail("john@nus"), new ArrayList<>(), new ClientId(1), new Pin(false));
        actualModel.addClient(beforeEditClient);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_SUCCESS, beforeEditClient));

        Model expectedModel = new ModelManager();
        Client afterEditClient = new Client(new Name("Tom"), new ClientMobile("12345678"),
                new ClientEmail("tom@nus"), new ArrayList<>(), new ClientId(1), new Pin(false));
        expectedModel.addClient(afterEditClient);

        ClientId stubClientId = afterEditClient.getClientId();
        ClientEmail stubClientEmail = afterEditClient.getClientEmail();

        assertCommandSuccess(new EditClientCommand(stubClientId, null, stubClientEmail, null), actualModel,
                expectedCommandResult, actualModel, stubUi);
    }

    @Test
    public void execute_editNonExistingClient_throwsCommandException() {
        Model model = new ModelManager();
        String noClientIdOneInProjectBook = "Client id 1 does not exist in the project book";
        EditClientCommand editClientCommand = new EditClientCommand(new ClientId(1), null, null, null);
        assertThrows(CommandException.class, noClientIdOneInProjectBook, () ->
                editClientCommand.execute(model, stubUi));
    }

}
