package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.DeleteTransactionCommand.MESSAGE_DELETE_TRANSACTION_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalTransaction.BUY_ORANGE;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyJeeqTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.UniqueClientList;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.ClientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTransactionCommand}.
 */
public class DeleteTransactionCommandTest {

    private static final Transaction transactionToAdd = BUY_ORANGE;

    @Test
    public void execute_validIndex_success() throws Exception {
        Model modelStub = new ModelStub(new ClientBuilder().build());

        DeleteTransactionCommand command = new DeleteTransactionCommand(Index.fromOneBased(1));
        CommandResult result = command.execute(modelStub);

        String expectedMessage = String.format(MESSAGE_DELETE_TRANSACTION_SUCCESS, transactionToAdd);

        assertEquals(result.getFeedbackToUser(), expectedMessage);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model modelStub = new ModelStub(new ClientBuilder().build());

        assertThrows(CommandException.class, () ->
                new DeleteTransactionCommand(Index.fromOneBased(2)).execute(modelStub));
    }

    @Test
    public void execute_moreThanOneInFilteredList_throwsCommandException() {
        Model modelStub = new ModelStub(new ClientBuilder().build());
        modelStub.addClient(ALICE);

        assertThrows(CommandException.class, () ->
                new DeleteTransactionCommand(Index.fromOneBased(1)).execute(modelStub));
    }

    @Test
    public void equals() {
        DeleteTransactionCommand firstCommand = new DeleteTransactionCommand(Index.fromOneBased(1));
        DeleteTransactionCommand secondCommand = new DeleteTransactionCommand(Index.fromOneBased(1));

        // same values -> returns true
        assertTrue(firstCommand.equals(secondCommand));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different types -> returns false
        assertFalse(firstCommand.equals(new ListCommand()));

        // different index -> returns false
        assertFalse(firstCommand.equals(new DeleteTransactionCommand(Index.fromOneBased(2))));
    }

    /**
     * A default model stub that have some methods failing, and when initialized, has a client with one transaction,
     * specified by class field transactionToAdd.
     */
    private class ModelStub implements Model {
        private Client client;
        private final UniqueClientList clientList = new UniqueClientList();

        public ModelStub(Client client) {
            this.client = client;
            this.client.addTransaction(transactionToAdd);
            clientList.add(client);
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getJeeqTrackerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJeeqTrackerFilePath(Path jeeqTrackerFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client client) {
            this.clientList.add(client);
        }

        @Override
        public void setJeeqTracker(ReadOnlyJeeqTracker newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyJeeqTracker getJeeqTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteClient(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedClient) {
            // method body is left empty intentionally
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            return clientList.asUnmodifiableObservableList();
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
            // method body is left empty intentionally
        }

        @Override
        public double calculateTotalTransaction(ObservableList<Client> filteredClientList) {
            throw new AssertionError("This methods should not be called.");
        }
    }
}
