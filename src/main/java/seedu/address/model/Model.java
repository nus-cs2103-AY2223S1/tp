package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.client.Client;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
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
     * Returns the user prefs' client book file path.
     */
    Path getClientBookFilePath();

    /**
     * Sets the user prefs' client book file path.
     */
    void setClientBookFilePath(Path clientBookFilePath);

    /**
     * Replaces client book data with the data in {@code ClientBook}.
     */
    void setClientBook(ReadOnlyClientBook clientBook);

    /** Returns the ClientBook */
    ReadOnlyClientBook getClientBook();

    /**
     * Returns true if a client with the same identity as {@code client} exists in the client book.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given client.
     * The client must exist in the client book.
     */
    void deleteClient(Client target);

    /**
     * Adds the given client.
     * {@code client} must not already exist in the client book.
     */
    void addClient(Client client);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the client book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the client book.
     */
    void setClient(Client target, Client editedClient);

    /** Returns an unmodifiable view of the filtered client list */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);
}
