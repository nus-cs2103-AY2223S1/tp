package seedu.address.logic.commands.client;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.client.EditClientCommand.MESSAGE_SUCCESS;

public class FindClientCommandTest {
    private final Ui stubUi = new StubUiManager();

    @Test
    public void execute_findClient_success() {
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

        CommandResult expectedCommandResultName = new CommandResult(
                String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW, actualModel.getFilteredClientList().size()));

        List<String> name = Arrays.asList("Watson");
        List<String> email = Arrays.asList("rosie@reddit.com-sg");
        List<String> phone = Arrays.asList("12345678");
        List<String> clientId = Arrays.asList("1");
        List<String> empty = new ArrayList<>();

        //by name
        FindClientCommand findName = new FindClientCommand(new ClientContainsKeywordsPredicate(
                name, empty, empty, empty));

        Model expectedNameModel = new ModelManager();
        expectedNameModel.addClient(clientJohn);
        expectedNameModel.addClient(clientRosie);
        expectedNameModel.addClient(clientMary);

        assertCommandSuccess(findName, actualModel, expectedCommandResultName, expectedNameModel, stubUi);

        //by mobile
        FindClientCommand findMobile = new FindClientCommand(new ClientContainsKeywordsPredicate(
                empty, empty, , empty));

        Model expectedModel = new ModelManager();
        expectedModel.addClient(clientJohn);
        expectedModel.addClient(clientRosie);
        expectedModel.addClient(clientMary);

        assertCommandSuccess(findName, actualModel, expectedCommandResultName, expectedModel, stubUi);


        //by name
        FindClientCommand findEmail = new FindClientCommand(new ClientContainsKeywordsPredicate(
                name, empty, empty, empty));

        Model expectedModel = new ModelManager();
        expectedModel.addClient(clientJohn);
        expectedModel.addClient(clientRosie);
        expectedModel.addClient(clientMary);

        assertCommandSuccess(findName, actualModel, expectedCommandResultName, expectedModel, stubUi);

        //by id
        FindClientCommand findId = new FindClientCommand(new ClientContainsKeywordsPredicate(
                name, empty, empty, empty));

        Model expectedModel = new ModelManager();
        expectedModel.addClient(clientJohn);
        expectedModel.addClient(clientRosie);
        expectedModel.addClient(clientMary);

        assertCommandSuccess(findName, actualModel, expectedCommandResultName, expectedModel, stubUi);
    }
}
