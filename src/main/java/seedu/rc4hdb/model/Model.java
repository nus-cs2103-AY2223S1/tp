package seedu.rc4hdb.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.rc4hdb.commons.core.GuiSettings;
import seedu.rc4hdb.model.resident.Resident;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Resident> PREDICATE_SHOW_ALL_RESIDENTS = unused -> true;

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
     * Returns the user prefs' resident book file path.
     */
    Path getResidentBookFilePath();

    /**
     * Sets the user prefs' resident book file path.
     */
    void setResidentBookFilePath(Path residentBookFilePath);

    /**
     * Replaces resident book data with the data in {@code residentBook}.
     */
    void setResidentBook(ReadOnlyResidentBook residentBook);

    /** Returns the ResidentBook */
    ReadOnlyResidentBook getResidentBook();

    /**
     * Returns true if a resident with the same identity as {@code resident} exists in the resident book.
     */
    boolean hasResident(Resident resident);

    /**
     * Deletes the given resident.
     * The resident must exist in the resident book.
     */
    void deleteResident(Resident target);

    /**
     * Adds the given resident.
     * {@code resident} must not already exist in the address book.
     */
    void addResident(Resident resident);

    /**
     * Replaces the given resident {@code target} with {@code editedResident}.
     * {@code target} must exist in the resident book.
     * The resident identity of {@code editedResident} must not be the same as another existing resident in the
     * resident book.
     */
    void setResident(Resident target, Resident editedResident);

    /** Returns an unmodifiable view of the filtered resident list */
    ObservableList<Resident> getFilteredResidentList();

    /**
     * Updates the filter of the filtered resident list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredResidentList(Predicate<Resident> predicate);

    ObservableList<String> getObservableFields();

    void setObservableFields(List<String> modifiableList);
}
