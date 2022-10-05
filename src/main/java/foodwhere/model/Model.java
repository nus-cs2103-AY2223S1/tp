package foodwhere.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import foodwhere.commons.core.GuiSettings;
import foodwhere.model.stall.Stall;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Stall> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a stall with the same identity as {@code stall} exists in the address book.
     */
    boolean hasPerson(Stall stall);

    /**
     * Deletes the given stall.
     * The stall must exist in the address book.
     */
    void deletePerson(Stall target);

    /**
     * Adds the given stall.
     * {@code stall} must not already exist in the address book.
     */
    void addPerson(Stall stall);

    /**
     * Replaces the given stall {@code target} with {@code editedStall}.
     * {@code target} must exist in the address book.
     * The stall identity of {@code editedStall} must not be the same as another existing stall in the address book.
     */
    void setPerson(Stall target, Stall editedStall);

    /** Returns an unmodifiable view of the filtered stall list */
    ObservableList<Stall> getFilteredPersonList();

    /**
     * Updates the filter of the filtered stall list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Stall> predicate);
}
