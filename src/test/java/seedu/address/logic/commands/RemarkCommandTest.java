package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.getTypicalJeeqTracker;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalRemark.BAD_BUYER;
import static seedu.address.testutil.TypicalRemark.BAD_SELLER;
import static seedu.address.testutil.TypicalRemark.LONG_REMARK;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyJeeqTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.UniqueClientList;
import seedu.address.testutil.ClientBuilder;

class RemarkCommandTest {

    private final Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());

    @Test
    public void constructor_nullRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemarkCommand(INDEX_FIRST_CLIENT, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemarkCommand(null, BAD_BUYER));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new RemarkCommand(INDEX_FIRST_CLIENT, BAD_BUYER).execute(null));
    }

    @Test
    public void execute_invalidClientIndex_failure() {
        assertThrows(CommandException.class, () -> new RemarkCommand(Index.fromZeroBased(
                model.getFilteredClientList().size() + 10), BAD_BUYER).execute(model));

        Client validClient = new ClientBuilder().build();
        Model modelStub = new ModelStub(validClient);

        assertThrows(CommandException.class, () -> new RemarkCommand(Index.fromZeroBased(
                modelStub.getFilteredClientList().size() + 10), BAD_BUYER).execute(model));
    }

    @Test
    public void execute_duplicateRemark_failure() {
        Client validClient = new ClientBuilder().build();
        validClient.addRemark(BAD_SELLER);
        Model modelStub = new ModelStub(validClient);

        assertThrows(CommandException.class, () ->
                new RemarkCommand(INDEX_FIRST_CLIENT, BAD_SELLER).execute(modelStub));
    }

    @Test
    public void execute_addRemark_success() throws Exception {
        Client validClient = new ClientBuilder().build();
        Model modelStub = new ModelStub(validClient);
        RemarkCommand createCommand = new RemarkCommand(INDEX_FIRST_CLIENT, BAD_SELLER);
        createCommand.execute(modelStub);
        assertTrue(modelStub.getFilteredClientList().get(0).hasRemark(BAD_SELLER));
    }


    @Test
    public void equals() {
        RemarkCommand createCommand = new RemarkCommand(INDEX_FIRST_CLIENT, LONG_REMARK);

        // same values -> returns true
        RemarkCommand createCommandCopy = new RemarkCommand(INDEX_FIRST_CLIENT, LONG_REMARK);
        assertTrue(createCommand.equals(createCommandCopy));

        // same object -> returns true
        assertTrue(createCommand.equals(createCommand));

        // null -> returns false
        assertFalse(createCommand.equals(null));

        // different types -> returns false
        assertFalse(createCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(createCommand.equals(new RemarkCommand(INDEX_SECOND_CLIENT, LONG_REMARK)));

        // different Remark -> returns false
        assertFalse(createCommand.equals(new RemarkCommand(INDEX_FIRST_CLIENT, BAD_SELLER)));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        private Client client;
        private final UniqueClientList clientList = new UniqueClientList();

        public ModelStub(Client client) {
            this.client = client;
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
            throw new AssertionError("This method should not be called.");
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

        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            return clientList.asUnmodifiableObservableList();
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {

        }
        @Override
        public double calculateTotalTransaction(ObservableList<Client> filteredClientList) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
