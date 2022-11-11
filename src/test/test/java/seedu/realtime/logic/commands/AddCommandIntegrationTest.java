package seedu.realtime.logic.commands;

import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.realtime.testutil.TypicalClients.getTypicalRealTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.realtime.model.Model;
import seedu.realtime.model.ModelManager;
import seedu.realtime.model.UserPrefs;
import seedu.realtime.model.person.Client;
import seedu.realtime.testutil.ClientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddClientCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRealTime(), new UserPrefs());
    }

    @Test
    public void execute_newClient_success() {
        Client validClient = new ClientBuilder().build();

        Model expectedModel = new ModelManager(model.getRealTime(), new UserPrefs());
        expectedModel.addClient(validClient);

        assertCommandSuccess(new AddCommand(validClient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validClient), expectedModel);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client clientInList = model.getRealTime().getClientList().get(0);
        assertCommandFailure(new AddCommand(clientInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
