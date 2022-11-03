package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_CLIENT;
import static seedu.address.testutil.TypicalRemark.BAD_BUYER;
import static seedu.address.testutil.TypicalRemark.GOOD_BUYER;

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
import seedu.address.model.remark.Remark;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.RemarkBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditRemarkCommandTest {
    private final Remark editedRemark = new RemarkBuilder().withText("TestRemark").build();

    @Test
    public void execute_allFieldsValidIndex_success() throws CommandException {
        Model modelStub = new EditRemarkCommandTest.ModelStub(new ClientBuilder().build());

        EditRemarkCommand editRemarkCommand = new EditRemarkCommand(INDEX_FIRST_CLIENT, editedRemark);

        String expectedMessage =
                String.format(EditRemarkCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedRemark);

        CommandResult result = editRemarkCommand.execute(modelStub);
        assertEquals(result.getFeedbackToUser(), expectedMessage);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model modelStub = new EditRemarkCommandTest.ModelStub(new ClientBuilder().build());

        assertThrows(CommandException.class, () ->
                new EditRemarkCommand(INDEX_THIRD_CLIENT, editedRemark).execute(modelStub));
    }

    @Test
    public void execute_moreThanOneInFilteredList_throwsCommandException() {
        Model modelStub = new EditRemarkCommandTest.ModelStub(new ClientBuilder().build());
        modelStub.addClient(ALICE);

        assertThrows(CommandException.class, () ->
                new EditRemarkCommand(INDEX_SECOND_CLIENT, editedRemark).execute(modelStub));
    }

    @Test
    public void execute_duplicateRemark_throwsCommandException() {
        Model modelStub = new EditRemarkCommandTest.ModelStub(new ClientBuilder().build());

        assertThrows(CommandException.class, () ->
                new EditRemarkCommand(INDEX_FIRST_CLIENT, BAD_BUYER).execute(modelStub));
    }

    @Test
    public void equals() {
        EditRemarkCommand firstCommand = new EditRemarkCommand(Index.fromOneBased(1), editedRemark);
        EditRemarkCommand secondCommand = new EditRemarkCommand(Index.fromOneBased(1), editedRemark);

        // same values -> returns true
        assertTrue(firstCommand.equals(secondCommand));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different types -> returns false
        assertFalse(firstCommand.equals(new ListCommand()));

        // different index -> returns false
        assertFalse(firstCommand.equals(new EditRemarkCommand(Index.fromOneBased(3), editedRemark)));
    }

    /**
     * A default model stub that have some methods failing, and when initialized, has a client with one remark,
     * specified by class field remarkToEdit.
     */
    private class ModelStub implements Model {
        private Client client;
        private final UniqueClientList clientList = new UniqueClientList();
        private final Remark remarkToEdit = GOOD_BUYER;
        private final Remark anotherRemark = BAD_BUYER;

        public ModelStub(Client client) {
            this.client = client;
            this.client.addRemark(remarkToEdit);
            this.client.addRemark(anotherRemark);
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
