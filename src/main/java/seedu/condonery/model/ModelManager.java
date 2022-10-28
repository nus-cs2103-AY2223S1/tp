package seedu.condonery.model;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.condonery.commons.core.GuiSettings;
import seedu.condonery.commons.core.LogsCenter;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandQueue;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.ClientDirectory;
import seedu.condonery.model.client.ReadOnlyClientDirectory;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.PropertyDirectory;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;

/**
 * Represents the in-memory model of the Condonery data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final PropertyDirectory propertyDirectory;
    private final FilteredList<Property> filteredProperties;
    private final ClientDirectory clientDirectory;
    private final FilteredList<Client> filteredClients;

    private final ReadOnlyPropertyDirectory initialPropertyDirectory;
    private final ReadOnlyClientDirectory initialClientDirectory;

    private final CommandQueue commandQueue = new CommandQueue();
    /**
     * Initializes a ModelManager with the given propertyDirectory and userPrefs.
     */
    public ModelManager(ReadOnlyPropertyDirectory propertyDirectory, ReadOnlyClientDirectory clientDirectory,
                        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(propertyDirectory, clientDirectory, userPrefs);

        logger.fine("Initializing with address book: " + propertyDirectory + " and user prefs " + userPrefs);

        this.propertyDirectory = new PropertyDirectory(propertyDirectory, userPrefs.getUserImageDirectoryPath());
        this.initialPropertyDirectory = propertyDirectory;
        filteredProperties = new FilteredList<>(this.propertyDirectory.getPropertyList());

        this.clientDirectory = new ClientDirectory(clientDirectory, userPrefs.getUserImageDirectoryPath());
        this.initialClientDirectory = clientDirectory;
        filteredClients = new FilteredList<>(this.clientDirectory.getClientList());

        this.userPrefs = new UserPrefs(userPrefs);
    }

    public ModelManager() {
        this(new PropertyDirectory(), new ClientDirectory(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPropertyDirectoryFilePath() {
        return userPrefs.getPropertyDirectoryFilePath();
    }

    @Override
    public void setPropertyDirectoryFilePath(Path propertyDirectoryFilePath) {
        requireNonNull(propertyDirectoryFilePath);
        userPrefs.setPropertyDirectoryFilePath(propertyDirectoryFilePath);
    }

    @Override
    public Path getClientDirectoryFilePath() {
        return userPrefs.getClientDirectoryFilePath();
    }

    @Override
    public void setClientDirectoryFilePath(Path clientDirectoryFilePath) {
        requireNonNull(clientDirectoryFilePath);
        userPrefs.setClientDirectoryFilePath(clientDirectoryFilePath);
    }

    //=========== PropertyDirectory ================================================================================

    @Override
    public void setPropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory) {
        this.propertyDirectory.resetData(propertyDirectory);
    }

    @Override
    public void resetPropertyDirectory() {
        this.propertyDirectory.resetData(initialPropertyDirectory);
    }

    @Override
    public ReadOnlyPropertyDirectory getPropertyDirectory() {
        return propertyDirectory;
    }

    //=========== ClientDirectory ================================================================================

    @Override
    public void setClientDirectory(ReadOnlyClientDirectory clientDirectory) {
        this.clientDirectory.resetData(clientDirectory);
    }

    @Override
    public void resetClientDirectory() {
        this.clientDirectory.resetData(initialClientDirectory);
    }

    @Override
    public ReadOnlyClientDirectory getClientDirectory() {
        return clientDirectory;
    }

    //=========== Property =======================================================================================

    @Override
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return propertyDirectory.hasProperty(property);
    }

    @Override
    public void deleteProperty(Property target) {
        propertyDirectory.removeProperty(target);
    }

    @Override
    public void addProperty(Property property) {
        propertyDirectory.addProperty(property);
        updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
    }

    @Override
    public void setProperty(Property target, Property editedProperty) {
        requireAllNonNull(target, editedProperty);

        propertyDirectory.setProperty(target, editedProperty);
    }

    @Override
    public boolean hasPropertyName(String substring) {
        return propertyDirectory.hasPropertyName(substring);
    }

    @Override
    public boolean hasUniquePropertyName(String substring) {
        return propertyDirectory.hasUniquePropertyName(substring);
    }

    @Override
    public Property getUniquePropertyByName(String substring) {
        return propertyDirectory.getUniquePropertyByName(substring);
    }

    //=========== Filtered Property List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Property} backed by the internal list of
     * {@code versionedPropertyDirectory}
     */
    @Override
    public ObservableList<Property> getFilteredPropertyList() {
        return filteredProperties;
    }

    @Override
    public void updateFilteredPropertyList(Predicate<Property> predicate) {
        requireNonNull(predicate);
        filteredProperties.setPredicate(predicate);
    }

    //=========== Client ==========================================================================================

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clientDirectory.hasClient(client);
    }

    @Override
    public void deleteClient(Client target) {
        clientDirectory.removeClient(target);
    }

    @Override
    public void addClient(Client client) {
        clientDirectory.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);
        clientDirectory.setClient(target, editedClient);
    }

    @Override
    public boolean hasClientName(String substring) {
        return clientDirectory.hasClientName(substring);
    }

    @Override
    public boolean hasUniqueClientName(String substring) {
        return clientDirectory.hasUniqueClientName(substring);
    }

    @Override
    public Client getUniqueClientByName(String substring) {
        return clientDirectory.getUniqueClientByName(substring);
    }


    //=========== Filtered Client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedClientDirectory}
     */
    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return propertyDirectory.equals(other.propertyDirectory)
                && userPrefs.equals(other.userPrefs)
                && filteredProperties.equals(other.filteredProperties)
                && clientDirectory.equals(other.clientDirectory)
                && filteredClients.equals(other.filteredClients);
    }


    //=========== CommandQueue =============================================================
    @Override
    public void addCommand(Command cmd) {
        this.commandQueue.addCommand(cmd);
    }

    @Override
    public CommandQueue getCommandQueue() {
        return this.commandQueue;
    }
}
