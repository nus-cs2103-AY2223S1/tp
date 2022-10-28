package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.condonery.commons.core.GuiSettings;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandQueue;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.model.Model;
import seedu.condonery.model.ReadOnlyUserPrefs;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.ReadOnlyClientDirectory;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.PropertyDirectory;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;
import seedu.condonery.testutil.PropertyBuilder;

public class AddPropertyCommandTest {

    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPropertyCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPropertyAdded modelStub = new ModelStubAcceptingPropertyAdded();
        Property validProperty = new PropertyBuilder().build();

        CommandResult commandResult = new AddPropertyCommand(validProperty).execute(modelStub);

        assertEquals(
            String.format(AddPropertyCommand.MESSAGE_SUCCESS + ". No rejected client names.", validProperty),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProperty), modelStub.propertiesAdded);
    }

    @Test
    public void execute_duplicateProperty_throwsCommandException() {
        Property validProperty = new PropertyBuilder().build();
        AddPropertyCommand addCommand = new AddPropertyCommand(validProperty);
        ModelStub modelStub = new ModelStubWithProperty(validProperty);

        assertThrows(CommandException.class,
                AddPropertyCommand.MESSAGE_DUPLICATE_PROPERTY, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Property alice = new PropertyBuilder().withName("Alice").build();
        Property bob = new PropertyBuilder().withName("Bob").build();
        AddPropertyCommand addAliceCommand = new AddPropertyCommand(alice);
        AddPropertyCommand addBobCommand = new AddPropertyCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddPropertyCommand addAliceCommandCopy = new AddPropertyCommand(alice);
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
    private class ModelStubWithProperty extends ModelStub {
        private final Property property;

        ModelStubWithProperty(Property property) {
            requireNonNull(property);
            this.property = property;
        }

        @Override
        public boolean hasProperty(Property property) {
            requireNonNull(property);
            return this.property.isSameProperty(property);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPropertyAdded extends ModelStub {
        final ArrayList<Property> propertiesAdded = new ArrayList<>();

        @Override
        public boolean hasProperty(Property property) {
            requireNonNull(property);
            return propertiesAdded.stream().anyMatch(property::isSameProperty);
        }

        @Override
        public void addProperty(Property property) {
            requireNonNull(property);
            propertiesAdded.add(property);
        }

        @Override
        public ReadOnlyPropertyDirectory getPropertyDirectory() {
            return new PropertyDirectory();
        }

        @Override
        public UserPrefs getUserPrefs() {
            return new UserPrefs();
        }
    }

}
