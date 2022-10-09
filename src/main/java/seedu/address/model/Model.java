package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.item.Item;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Item> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

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
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasItem(Item person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteItem(Item target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addItem(Item person);

    /**
     * Replaces the given person {@code target} with {@code editedItem}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedItem} must not be the same as another existing person in the address book.
     */
    void setItem(Item target, Item editedItem);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Item> getFilteredItemList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItemList(Predicate<Item> predicate);
}
