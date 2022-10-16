package seedu.uninurse.model;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javafx.collections.ObservableList;
import seedu.uninurse.commons.core.GuiSettings;
import seedu.uninurse.model.person.Patient;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' uninurse book file path.
     */
    Path getUninurseBookFilePath();

    /**
     * Sets the user prefs' uninurse book file path.
     */
    void setUninurseBookFilePath(Path uninurseBookFilePath);

    /**
     * Replaces uninurse book data with the data in {@code uninurseBook}.
     */
    void setUninurseBook(ReadOnlyUninurseBook uninurseBook);

    /** Returns the UninurseBook */
    ReadOnlyUninurseBook getUninurseBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the uninurse book.
     */
    boolean hasPerson(Patient person);

    /**
     * Deletes the given person.
     * The person must exist in the uninurse book.
     */
    void deletePerson(Patient target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the uninurse book.
     */
    void addPerson(Patient person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the uninurse book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the uninurse book.
     */
    void setPerson(Patient target, Patient editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Patient> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Patient> predicate);

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}
     * with tasks list flag enabled.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonListWithTasks(Predicate<Patient> predicate);

    /** Returns a supplier to get the flag to display tasks */
    Supplier<Boolean> getTaskListFlagSupplier();
}
