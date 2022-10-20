package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.internship.Internship;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Internship> PREDICATE_SHOW_ALL_INTERNSHIPS = unused -> true;

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
     * Returns true if an internship with the same identity as {@code internship} exists in the address book.
     */
    boolean hasInternship(Internship internship);

    /**
     * Deletes the given internship.
     * The internship must exist in the address book.
     */
    void deleteInternship(Internship target);

    /**
     * Adds the given internship.
     * {@code internship} must not already exist in the address book.
     */
    void addInternship(Internship internship);

    /**
     * Replaces the given internship {@code target} with {@code editedInternship}.
     * {@code target} must exist in the address book.
     * The internship identity of {@code editedInternship} must not be the same as
     * another existing internship in the address book.
     */
    void setInternship(Internship target, Internship editedInternship);

    /**
     * Replaces the given internship {@code target} with {@code markedInternship}.
     * {@code target} must exist in the address book.
     * The internship identity of {@code markInternship} must not be the same as
     * another existing internship in the address book.
     */
    void markInternship(Internship target, Internship markedInternship);

    /** Returns an unmodifiable view of the filtered internship list */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Updates the filter of the filtered internship list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipList(Predicate<Internship> predicate);
}
