package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.getTypicalJeeqTracker;
import static seedu.address.testutil.TypicalCompany.ALICE;
import static seedu.address.testutil.TypicalCompany.AMY;
import static seedu.address.testutil.TypicalCompany.BOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

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



class CreateCommandTest {

    private final Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());

    @Test
    public void constructor_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(INDEX_FIRST_CLIENT, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(null, ALICE));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(INDEX_FIRST_CLIENT, ALICE).execute(null));
    }

    @Test
    public void execute_invalidClientIndex_failure() {
        assertThrows(CommandException.class, () -> new CreateCommand(Index.fromZeroBased(
                model.getFilteredClientList().size() + 10), ALICE).execute(model));

        Client validClient = new ClientBuilder().build();
        Model modelStub = new ModelStub(validClient);

        assertThrows(CommandException.class, () -> new CreateCommand(Index.fromZeroBased(
                modelStub.getFilteredClientList().size() + 10), ALICE).execute(model));
    }

    @Test
    public void execute_duplicateCompany_failure() {
        Client validClient = new ClientBuilder().build();
        validClient.addCompany(AMY);
        Model modelStub = new ModelStub(validClient);

        assertThrows(CommandException.class, () -> new CreateCommand(INDEX_FIRST_CLIENT, AMY).execute(modelStub));
    }

    @Test
    public void execute_addCompany_success() throws Exception {
        Client validClient = new ClientBuilder().build();
        Model modelStub = new ModelStub(validClient);
        CreateCommand createCommand = new CreateCommand(INDEX_FIRST_CLIENT, AMY);
        createCommand.execute(modelStub);
        assertTrue(modelStub.getFilteredClientList().get(0).hasCompany(AMY));
    }


    @Test
    public void equals() {
        CreateCommand createCommand = new CreateCommand(INDEX_FIRST_CLIENT, ALICE);

        // same values -> returns true
        CreateCommand createCommandCopy = new CreateCommand(INDEX_FIRST_CLIENT, ALICE);
        assertTrue(createCommand.equals(createCommandCopy));

        // same object -> returns true
        assertTrue(createCommand.equals(createCommand));

        // null -> returns false
        assertFalse(createCommand.equals(null));

        // different types -> returns false
        assertFalse(createCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(createCommand.equals(new CreateCommand(INDEX_SECOND_CLIENT, ALICE)));

        // different Company -> returns false
        assertFalse(createCommand.equals(new CreateCommand(INDEX_FIRST_CLIENT, BOB)));
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
    }
}
