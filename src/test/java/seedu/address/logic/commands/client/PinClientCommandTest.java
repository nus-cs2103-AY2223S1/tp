package seedu.address.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.client.PinClientCommand.MESSAGE_CLIENT_NOT_FOUND;
import static seedu.address.logic.commands.client.PinClientCommand.MESSAGE_PIN_SUCCESS;
import static seedu.address.logic.commands.client.PinClientCommand.MESSAGE_UNPIN_SUCCESS;
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

public class PinClientCommandTest {

    private Ui stubUi = new StubUiManager();

    @Test
    public void constructor_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PinClientCommand(null));
    }

    @Test
    public void execute_pinClient_success() {
        Model actualModel = new ModelManager();
        Client stubClientUnpinned = new Client(new Name("Sammy"), new ClientMobile("98765432"),
                new ClientEmail("sammy@gmail.com"), new ArrayList<>(),
                new ClientId(1), new Pin(false));
        actualModel.addClient(stubClientUnpinned);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_PIN_SUCCESS, stubClientUnpinned));

        Model expectedModel = new ModelManager();
        Client stubClientPinned = new Client(new Name("Sammy"), new ClientMobile("98765432"),
                new ClientEmail("sammy@gmail.com"), new ArrayList<>(),
                new ClientId(1), new Pin(true));
        expectedModel.addClient(stubClientPinned);
        ClientId stubClientId = stubClientPinned.getClientId();

        assertCommandSuccess(new PinClientCommand(stubClientId), actualModel,
                expectedCommandResult, expectedModel, stubUi);
    }

    @Test
    public void execute_unpinClient_success() {
        Model actualModel = new ModelManager();
        Client stubClientPinned = new Client(new Name("Sammy"), new ClientMobile("98765432"),
                new ClientEmail("sammy@gmail.com"), new ArrayList<>(),
                new ClientId(1), new Pin(true));
        actualModel.addClient(stubClientPinned);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_UNPIN_SUCCESS, stubClientPinned));

        Model expectedModel = new ModelManager();
        Client stubClientUnpinned = new Client(new Name("Sammy"), new ClientMobile("98765432"),
                new ClientEmail("sammy@gmail.com"), new ArrayList<>(),
                new ClientId(1), new Pin(false));
        expectedModel.addClient(stubClientUnpinned);
        ClientId stubClientId = stubClientUnpinned.getClientId();

        assertCommandSuccess(new PinClientCommand(stubClientId), actualModel,
                expectedCommandResult, expectedModel, stubUi);
    }

    @Test
    public void execute_pinNonExistingClient_throwsCommandException() {
        Model model = new ModelManager();
        PinClientCommand pinClientCommand = new PinClientCommand(new ClientId(1));
        assertThrows(CommandException.class, MESSAGE_CLIENT_NOT_FOUND, () ->
                pinClientCommand.execute(model, stubUi));
    }

    @Test
    public void testEquals() {
        PinClientCommand pinClientCommandIdOne = new PinClientCommand(new ClientId(1));
        PinClientCommand pinClientCommandIdTwo = new PinClientCommand(new ClientId(2));
        PinClientCommand pinClientCommandIdOneCopy = new PinClientCommand(new ClientId(1));

        // same object -> returns true
        assertTrue(pinClientCommandIdOne.equals(pinClientCommandIdOne));

        // same values -> returns true
        assertTrue(pinClientCommandIdOne.equals(pinClientCommandIdOneCopy));

        // different types -> returns false
        assertFalse(pinClientCommandIdOne.equals(1));

        // null -> returns false
        assertFalse(pinClientCommandIdOne.equals(null));

        // different person -> returns false
        assertFalse(pinClientCommandIdOne.equals(pinClientCommandIdTwo));
    }
}
