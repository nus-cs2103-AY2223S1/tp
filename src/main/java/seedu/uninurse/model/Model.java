package seedu.uninurse.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.uninurse.commons.core.GuiSettings;
import seedu.uninurse.logic.commands.CommandResult;
import seedu.uninurse.model.exceptions.PatientNotFoundException;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * Predicate for the model to use.
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Patient> PREDICATE_SHOW_PATIENTS_FOR_TODAY = patient -> patient.getTasks().containsTaskToday();
    Predicate<Patient> PREDICATE_SHOW_PATIENTS_TASK = patient -> !(patient.getTasks().isEmpty());

    //=========== UserPrefs =================================================================================

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in userPrefs.
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

    //=========== UninurseBook ==============================================================================

    /**
     * Returns the UninurseBook
     */
    ReadOnlyUninurseBook getUninurseBook();

    /**
     * Replaces uninurse book data with the data in uninurseBook.
     */
    void setUninurseBook(ReadOnlyUninurseBook uninurseBook);

    /**
     * Returns true if a person with the same identity as person exists in the uninurse book.
     */
    boolean hasPerson(Person person);

    /**
     * Adds the given person.
     * person must not already exist in the uninurse book.
     */
    PersonListTracker addPerson(Person person);

    /**
     * Replaces the given person with editedPerson.
     * person must exist in the uninurse book.
     * The person identity of editedPerson must not be the same as another existing person in the uninurse book.
     */
    PersonListTracker setPerson(Person person, Person editedPerson);

    /**
     * Adds the given patient.
     * patient must not already exist in the uninurse book.
     */
    PersonListTracker addPatient(Patient patient);

    /**
     * Replaces the given patient with editedPatient.
     * patient must exist in the uninurse book.
     * The patient identity of editedPatient must not be the same as another existing patient in the uninurse book.
     */
    PersonListTracker setPatient(Patient patient, Patient editedPatient);

    /**
     * Deletes the given person.
     * The person must exist in the uninurse book.
     */
    PersonListTracker deletePerson(Person person);

    /**
     * Deletes the given persons.
     * The persons must exist in the uninurse book.
     */
    PersonListTracker clearPersons(List<Person> targets);

    //=========== Filtered Person List Accessors ============================================================

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given predicate.
     *
     * @throws NullPointerException if predicate is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered person list to filter by the given predicate.
     *
     * @throws NullPointerException if predicate is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    //=========== Filtered Person Accessors =================================================================

    /**
     * Gets the patient from UninurseBook if the given person is a patient.
     *
     * @throws PatientNotFoundException if the given person is not a patient.
     */
    Patient getPatient(Person person) throws PatientNotFoundException;

    /**
     * Returns an unmodifiable view of the patient list
     */
    ObservableList<Patient> getPatientList();

    //=========== Other Accessors ===========================================================================

    /**
     * Updates the patient of interest with patient to be accessed by UI components.
     */
    void setPatientOfInterest(Patient patient);

    /**
     * Gets the patient of interest.
     *
     * @return patient of interest.
     */
    Patient getPatientOfInterest();

    /**
     * Updates the schedule to be accessed by UI components.
     */
    void setSchedule(Schedule schedule);

    /**
     * Gets the schedule.
     *
     * @return schedule.
     */
    Schedule getSchedule();

    /**
     * Saves the PatientListTracker in the current snapshot.
     */
    void saveCurrentPersonListTracker();

    /**
     * Gets the saved PatientListTracker.
     * @return saved patient pair.
     */
    PersonListTracker getPersonListTracker();

    /**
    * Updates each person
    */
    void updatePersons();

    //=========== Undo and Redo =============================================================================

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
}
