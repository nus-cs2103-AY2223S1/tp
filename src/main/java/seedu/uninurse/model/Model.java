package seedu.uninurse.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.uninurse.commons.core.GuiSettings;
import seedu.uninurse.logic.commands.CommandResult;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.DateTime;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Patient> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Patient> PREDICATE_SHOW_PATIENTS_FOR_TODAY = patient -> patient.getTasks().containsTaskToday();

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
     * Returns the user prefs' uninurse book file path.
     */
    Path getUninurseBookFilePath();

    /**
     * Sets the user prefs' uninurse book file path.
     */
    void setUninurseBookFilePath(Path uninurseBookFilePath);

    /**
     * Returns the UninurseBook
     */
    ReadOnlyUninurseBook getUninurseBook();

    /**
     * Replaces uninurse book data with the data in {@code uninurseBook}.
     */
    void setUninurseBook(ReadOnlyUninurseBook uninurseBook);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the uninurse book.
     */
    boolean hasPerson(Patient person);

    /**
     * Deletes the given person.
     * The person must exist in the uninurse book.
     */
    PatientListTracker deletePerson(Patient target);

    /**
     * Deletes the given persons.
     * The persons must exist in the uninurse book.
     */
    PatientListTracker clearPersons(List<Patient> targets);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the uninurse book.
     */
    PatientListTracker addPerson(Patient person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the uninurse book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the uninurse book.
     */
    PatientListTracker setPerson(Patient target, Patient editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Patient> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Patient> predicate);

    /**
     * Updates the patient of interest  with {@code patient} to be accessed by UI components.
     */
    void setPatientOfInterest(Patient patient);

    /**
     * Gets the patient of interest.
     * @return patient of interest.
     */
    Patient getPatientOfInterest();

    /**
     * Updates the day of interest with {@code dateTime} to be accessed by UI components.
     */
    void setDayOfInterest(DateTime dayOfInterest);

    /**
     * Gets the schedule.
     * @return schedule.
     */
    Schedule getSchedule();

    /**
     * Gets the saved PatientListTracker.
     * @return saved patient pair.
     */
    PatientListTracker getSavedPatientListTracker();

    /**
     * Saves the PatientListTracker in the current snapshot.
     */
    void saveCurrentPatientListTracker();

    /**
     * Returns whether you can revert to an earlier version of UninurseBook.
     */
    boolean canUndo();

    /**
     * Returns whether you can revert to a later version of UninurseBook.
     */
    boolean canRedo();

    /**
     * Reverts to an earlier version of UninurseBook.
     */
    CommandResult undo();

    /**
     * Reverts to a later version of UninurseBook.
     */
    CommandResult redo();

    /**
     * Makes a snapshot of the current UninurseBook.
     */
    void makeSnapshot(CommandResult commandResult);

    /**
    * Updates the TaskList of each patient with new RecurringTasks if the existing RecurringTask are past their
    * Task date.
    */
    void updateRecurringTasks();
}
