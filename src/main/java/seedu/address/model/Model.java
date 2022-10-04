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
     * Returns the user prefs' address book file path.
     */
    Path getClientBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setClientBookFilePath(Path clientBookFilePath);

    /**
     * Replaces address book data with the data in {@code ClientBook}.
     */
    void setClientBook(ReadOnlyClientBook clientBook);

    /** Returns the ClientBook */
    ReadOnlyClientBook getClientBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasClient(Client person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteClient(Client target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addClient(Client person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setClient(Client target, Client editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);
}
