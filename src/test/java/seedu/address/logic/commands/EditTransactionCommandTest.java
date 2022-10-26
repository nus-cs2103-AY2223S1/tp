package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
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
import seedu.address.testutil.EditTransactionDescriptorBuilder;
import seedu.address.testutil.TransactionBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditTransactionCommandTest {
    private final Transaction editedTransaction = new TransactionBuilder().build();

    @Test
    public void execute_allFieldsValidIndex_success() throws CommandException {
        Model modelStub = new EditTransactionCommandTest.ModelStub(new ClientBuilder().build());
        Model expectedModel = new EditTransactionCommandTest.ModelStub(new ClientBuilder().build());


        EditTransactionCommand.EditTransactionDescriptor descriptor =
            new EditTransactionDescriptorBuilder(editedTransaction).build();

        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(INDEX_FIRST_CLIENT, descriptor);

        String expectedMessage =
            String.format(EditTransactionCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedTransaction);

        Client expectedClient = expectedModel.getFilteredClientList().get(0);
        expectedClient.getTransactions().setTransaction(0, editedTransaction);
        modelStub.setClient(expectedClient, expectedClient);

        CommandResult result = editTransactionCommand.execute(modelStub);

        assertEquals(result.getFeedbackToUser(), expectedMessage);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model modelStub = new EditTransactionCommandTest.ModelStub(new ClientBuilder().build());

        EditTransactionCommand.EditTransactionDescriptor descriptor =
                new EditTransactionDescriptorBuilder(editedTransaction).build();

        assertThrows(CommandException.class, () ->
                new EditTransactionCommand(INDEX_SECOND_CLIENT, descriptor).execute(modelStub));
    }

    @Test
    public void execute_moreThanOneInFilteredList_throwsCommandException() {
        Model modelStub = new EditTransactionCommandTest.ModelStub(new ClientBuilder().build());
        modelStub.addClient(ALICE);

        EditTransactionCommand.EditTransactionDescriptor descriptor =
                new EditTransactionDescriptorBuilder(editedTransaction).build();

        assertThrows(CommandException.class, () ->
                new EditTransactionCommand(INDEX_SECOND_CLIENT, descriptor).execute(modelStub));
    }

    @Test
    public void equals() {
        EditTransactionCommand.EditTransactionDescriptor descriptor =
                new EditTransactionDescriptorBuilder(editedTransaction).build();

        EditTransactionCommand firstCommand = new EditTransactionCommand(Index.fromOneBased(1), descriptor);
        EditTransactionCommand secondCommand = new EditTransactionCommand(Index.fromOneBased(1), descriptor);

        // same values -> returns true
        assertTrue(firstCommand.equals(secondCommand));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different types -> returns false
        assertFalse(firstCommand.equals(new ListCommand()));

        // different index -> returns false
        assertFalse(firstCommand.equals(new EditTransactionCommand(Index.fromOneBased(3), descriptor)));
    }

    /**
     * A default model stub that have some methods failing, and when initialized, has a client with one transaction,
     * specified by class field transactionToEdit.
     */
    private class ModelStub implements Model {
        private Client client;
        private final UniqueClientList clientList = new UniqueClientList();
        private final Transaction transactionToEdit = BUY_ORANGE;

        public ModelStub(Client client) {
            this.client = client;
            this.client.addTransaction(transactionToEdit);
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
