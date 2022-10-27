package seedu.condonery.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.condonery.commons.core.GuiSettings;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandQueue;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.ReadOnlyClientDirectory;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Property> PREDICATE_SHOW_ALL_PROPERTIES = unused -> true;
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getPropertyDirectoryFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setPropertyDirectoryFilePath(Path propertyDirectoryFilePath);

    /**
     * Replaces address book data with the data in {@code propertyDirectory}.
     */
    void setPropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory);

    /**
     * Resets the property directory data to be the initial data on startup.
     */
    void resetPropertyDirectory();

    /** Returns the PropertyDirectory */
    ReadOnlyPropertyDirectory getPropertyDirectory();

    /**
     * Returns true if a property with the same identity as {@code property} exists in the address book.
     */
    boolean hasProperty(Property property);

    /**
     * Deletes the given property.
     * The property must exist in the address book.
     */
    void deleteProperty(Property target);

    /**
     * Adds the given property.
     * {@code property} must not already exist in the address book.
     */
    void addProperty(Property property);

    /**
     * Replaces the given property {@code target} with {@code editedProperty}.
     * {@code target} must exist in the address book.
     * The property identity of {@code editedProperty}
     * must not be the same as another existing property in the address book.
     */
    void setProperty(Property target, Property editedProperty);

    /** Returns an unmodifiable view of the filtered property list */
    ObservableList<Property> getFilteredPropertyList();

    /**
     * Updates the filter of the filtered property list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPropertyList(Predicate<Property> predicate);

    /**
     * Returns the user prefs' client directory file path.
     */
    Path getClientDirectoryFilePath();

    /**
     * Sets the user prefs' client directory file path.
     */
    void setClientDirectoryFilePath(Path propertyDirectoryFilePath);

    /**
     * Replaces client directory data with the data in {@code clientDirectory}.
     */
    void setClientDirectory(ReadOnlyClientDirectory clientDirectory);

    /**
     * Resets the client directory data to be the initial data on startup.
     */
    void resetClientDirectory();

    /** Returns the ClientDirectory */
    ReadOnlyClientDirectory getClientDirectory();

    /**
     * Returns true if a client with the same identity as {@code client} exists in the client directory.
     */
    boolean hasClient(Client client);

    /**
     * Returns true if a client whos name contains the given String exists in the client directory.
     */
    boolean hasClientName(String substring);

    /**
     * Returns true if only one unique client whos name contains the given String exists in the client directory.
     */
    boolean hasUniqueClientName(String substring);

    /**
     * Returns a unique client whos name contains the given string.
     */
    Client getUniqueClientByName(String substring);

    /**
     * Returns true if a property whos name contains the given String exists in the property directory.
     */
    boolean hasPropertyName(String substring);

    /**
     * Returns true if only one unique property whos name contains the given String exists in the property directory.
     */
    boolean hasUniquePropertyName(String substring);

    /**
     * Returns a unique property whos name contains the given string.
     */
    Property getUniquePropertyByName(String substring);

    /**
     * Deletes the given client.
     * The client must exist in the address book.
     */
    void deleteClient(Client target);

    /**
     * Adds the given client.
     * {@code client} must not already exist in the client directory.
     */
    void addClient(Client client);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the client directory.
     * The client identity of {@code editedClient}
     * must not be the same as another existing client in the address book.
     */
    void setClient(Client target, Client editedClient);

    /** Returns an unmodifiable view of the filtered client list */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

    //=========== CommandQueue =============================================================
    void addCommand(Command cmd);

    CommandQueue getCommandQueue();
}
