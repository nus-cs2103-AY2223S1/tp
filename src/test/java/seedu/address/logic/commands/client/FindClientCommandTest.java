package seedu.address.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.predicates.ClientContainsKeywordsPredicate;
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




public class FindClientCommandTest {
    private final Ui stubUi = new StubUiManager();

    @Test
    public void execute_findClientName_success() {
        Model actualModel = new ModelManager();
        Client clientJohn = new Client(new Name("John Watson"), new ClientMobile("12345678"),
                new ClientEmail("john@nus.com"), new ArrayList<>(), new ClientId(1), new Pin(false));
        actualModel.addClient(clientJohn);

        Client clientRosie = new Client(new Name("Rosie Watson"), new ClientMobile("12345678"),
                new ClientEmail("rosie@nus.com"), new ArrayList<>(), new ClientId(2), new Pin(false));
        actualModel.addClient(clientRosie);

        Client clientMary = new Client(new Name("Mary Watson"), new ClientMobile("7261912"),
                new ClientEmail("mary@nus.com"), new ArrayList<>(), new ClientId(3), new Pin(false));
        actualModel.addClient(clientMary);

        List<String> name = Arrays.asList("Watson");
        List<String> empty = new ArrayList<>();

        Model expectedNameModel = new ModelManager();
        expectedNameModel.addClient(clientJohn);
        expectedNameModel.addClient(clientRosie);
        expectedNameModel.addClient(clientMary);

        CommandResult expectedCommandResultName = new CommandResult(
                String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW,
                        actualModel.getFilteredClientList().size()));

        FindClientCommand findName = new FindClientCommand(new ClientContainsKeywordsPredicate(
                name, empty, empty, empty));

        assertCommandSuccess(findName, actualModel, expectedCommandResultName, expectedNameModel, stubUi);

    }

    @Test
    public void testEquals() {

        List<String> name = Arrays.asList("Harry");
        List<String> email = Arrays.asList("potter@reddit.com-sg");
        List<String> phone = Arrays.asList("103835180");
        List<String> clientId = Arrays.asList("1");
        List<String> empty = new ArrayList<>();

        FindClientCommand findClientCommandOne = new FindClientCommand(
                new ClientContainsKeywordsPredicate(name, email, phone, clientId));
        FindClientCommand findClientCommandTwo = new FindClientCommand(
                new ClientContainsKeywordsPredicate(name, empty, empty, clientId));
        FindClientCommand findClientCommandOneCopy = new FindClientCommand(
                new ClientContainsKeywordsPredicate(name, email, phone, clientId));

        // same object -> returns true
        assertTrue(findClientCommandOne.equals(findClientCommandOne));

        // same values -> returns true
        assertTrue(findClientCommandOne.equals(findClientCommandOneCopy));

        // different types -> returns false
        assertFalse(findClientCommandOne.equals(1));

        // null -> returns false
        assertFalse(findClientCommandOne.equals(null));

        // different person -> returns false
        assertFalse(findClientCommandOne.equals(findClientCommandTwo));
    }


}
