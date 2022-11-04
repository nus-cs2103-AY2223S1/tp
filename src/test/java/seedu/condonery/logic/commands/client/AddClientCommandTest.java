package seedu.condonery.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.condonery.commons.core.GuiSettings;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandQueue;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.model.Model;
import seedu.condonery.model.ReadOnlyUserPrefs;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.ClientDirectory;
import seedu.condonery.model.client.ReadOnlyClientDirectory;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;
import seedu.condonery.testutil.ClientBuilder;


public class AddClientCommandTest {

    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddClientCommand(null));
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client validClient = new ClientBuilder().build();
        AddClientCommand addCommand = new AddClientCommand(validClient);
        ModelStub modelStub = new ModelStubWithClient(validClient);

        assertThrows(CommandException.class,
                AddClientCommand.MESSAGE_DUPLICATE_CLIENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Client alice = new ClientBuilder().withName("Alice").build();
        Client bob = new ClientBuilder().withName("Bob").build();
        AddClientCommand addAliceCommand = new AddClientCommand(alice);
        AddClientCommand addBobCommand = new AddClientCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddClientCommand addAliceCommandCopy = new AddClientCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
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
        public Path getPropertyDirectoryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPropertyDirectoryFilePath(Path propertyDirectoryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProperty(Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPropertyDirectory(ReadOnlyPropertyDirectory newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetPropertyDirectory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPropertyDirectory getPropertyDirectory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProperty(Property person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProperty(Property target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProperty(Property target, Property editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Property> getFilteredPropertyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPropertyList(Predicate<Property> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getClientDirectoryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClientDirectoryFilePath(Path propertyDirectoryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClientDirectory(ReadOnlyClientDirectory newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetClientDirectory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClientDirectory getClientDirectory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClient(Client person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteClient(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedPerson) {
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

        @Override
        public void addCommand(Command cmd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CommandQueue getCommandQueue() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClientName(String clientName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasUniqueClientName(String clientName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Client getUniqueClientByName(String clientName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPropertyName(String propertyName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasUniquePropertyName(String propertyName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Property getUniquePropertyByName(String propertyName) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithClient extends ModelStub {
        private final Client client;

        ModelStubWithClient(Client client) {
            requireNonNull(client);
            this.client = client;
        }

        @Override
        public boolean hasClient(Client client) {
            requireNonNull(client);
            return this.client.isSameClient(client);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingClientAdded extends ModelStub {
        final ArrayList<Client> clientsAdded = new ArrayList<>();

        @Override
        public boolean hasClient(Client client) {
            requireNonNull(client);
            return clientsAdded.stream().anyMatch(client::isSameClient);
        }

        @Override
        public void addClient(Client client) {
            requireNonNull(client);
            clientsAdded.add(client);
        }

        @Override
        public ReadOnlyClientDirectory getClientDirectory() {
            return new ClientDirectory();
        }

        @Override
        public UserPrefs getUserPrefs() {
            return new UserPrefs();
        }
    }
}
