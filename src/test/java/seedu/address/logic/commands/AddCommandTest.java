package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ClientBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyClientBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.client.Client;
import seedu.address.testutil.ClientBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_ClientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingClientAdded modelStub = new ModelStubAcceptingClientAdded();
        Client validClient = new ClientBuilder().build();

        CommandResult commandResult = new AddCommand(validClient).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validClient), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validClient), modelStub.ClientsAdded);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client validClient = new ClientBuilder().build();
        AddCommand addCommand = new AddCommand(validClient);
        ModelStub modelStub = new ModelStubWithClient(validClient);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_Client, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Client alice = new ClientBuilder().withName("Alice").build();
        Client bob = new ClientBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different Client -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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
        public Path getClientBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClientBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client Client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClientBook(ReadOnlyClientBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClientBook getClientBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClient(Client Client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteClient(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedClient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single Client.
     */
    private class ModelStubWithClient extends ModelStub {
        private final Client Client;

        ModelStubWithClient(Client Client) {
            requireNonNull(Client);
            this.Client = Client;
        }

        @Override
        public boolean hasClient(Client Client) {
            requireNonNull(Client);
            return this.Client.isSameClient(Client);
        }
    }

    /**
     * A Model stub that always accept the Client being added.
     */
    private class ModelStubAcceptingClientAdded extends ModelStub {
        final ArrayList<Client> ClientsAdded = new ArrayList<>();

        @Override
        public boolean hasClient(Client Client) {
            requireNonNull(Client);
            return ClientsAdded.stream().anyMatch(Client::isSameClient);
        }

        @Override
        public void addClient(Client Client) {
            requireNonNull(Client);
            ClientsAdded.add(Client);
        }

        @Override
        public ReadOnlyClientBook getClientBook() {
            return new ClientBook();
        }
    }

}
