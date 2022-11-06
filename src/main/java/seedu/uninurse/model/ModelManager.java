package seedu.uninurse.model;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.uninurse.commons.core.GuiSettings;
import seedu.uninurse.commons.core.LogsCenter;
import seedu.uninurse.logic.commands.CommandResult;
import seedu.uninurse.model.exceptions.PatientOfInterestNotFoundException;
import seedu.uninurse.model.exceptions.ScheduleNotFoundException;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;

/**
 * Represents the in-memory model of the uninurse book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PersistentUninurseBook persistentUninurseBook;

    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    private Optional<Patient> patientOfInterest;
    private Optional<Schedule> schedule;
    private PersonListTracker personListTracker;

    /**
     * Initializes a ModelManager with the given uninurseBook and userPrefs.
     */
    public ModelManager(ReadOnlyUninurseBook uninurseBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(uninurseBook, userPrefs);

        logger.fine("Initializing with uninurse book: " + uninurseBook + " and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.persistentUninurseBook = new PersistentUninurseBook(uninurseBook);
        this.filteredPersons = new FilteredList<>(this.persistentUninurseBook.getWorkingCopy().getPersonList());
        this.patientOfInterest = Optional.empty();
        this.schedule = Optional.empty();
        this.personListTracker = new PersonListTracker();
    }

    public ModelManager() {
        this(new UninurseBook(), new UserPrefs());
    }

    //=========== UserPrefs =================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireAllNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getUninurseBookFilePath() {
        return userPrefs.getUninurseBookFilePath();
    }

    @Override
    public void setUninurseBookFilePath(Path uninurseBookFilePath) {
        requireAllNonNull(uninurseBookFilePath);
        userPrefs.setUninurseBookFilePath(uninurseBookFilePath);
    }

    //=========== UninurseBook ==============================================================================

    @Override
    public ReadOnlyUninurseBook getUninurseBook() {
        return persistentUninurseBook.getWorkingCopy();
    }

    @Override
    public void setUninurseBook(ReadOnlyUninurseBook uninurseBook) {
        persistentUninurseBook.getWorkingCopy().resetData(uninurseBook);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireAllNonNull(person);
        return persistentUninurseBook.getWorkingCopy().hasPerson(person);
    }

    @Override
    public PersonListTracker addPerson(Person person) {
        persistentUninurseBook.getWorkingCopy().addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new PersonListTracker(Optional.of(Arrays.asList(person)), Optional.empty());
    }

    @Override
    public PersonListTracker setPerson(Person person, Person editedPerson) {
        requireAllNonNull(person, editedPerson);
        persistentUninurseBook.getWorkingCopy().setPerson(person, editedPerson);
        return new PersonListTracker(Optional.of(Arrays.asList(editedPerson)), Optional.of(Arrays.asList(person)));
    }

    @Override
    public PersonListTracker addPatient(Patient patient) {
        persistentUninurseBook.getWorkingCopy().addPatient(patient);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new PersonListTracker(Optional.of(Arrays.asList(patient)), Optional.empty());
    }

    @Override
    public PersonListTracker setPatient(Patient patient, Patient editedPatient) {
        requireAllNonNull(patient, editedPatient);
        persistentUninurseBook.getWorkingCopy().setPatient(patient, editedPatient);
        return new PersonListTracker(Optional.of(Arrays.asList(editedPatient)), Optional.of(Arrays.asList(patient)));
    }

    @Override
    public PersonListTracker deletePerson(Person person) {
        persistentUninurseBook.getWorkingCopy().removePerson(person);
        return new PersonListTracker(Optional.empty(), Optional.of(Arrays.asList(person)));
    }

    @Override
    public PersonListTracker clearPersons(List<Person> persons) {
        for (Person person : persons) {
            persistentUninurseBook.getWorkingCopy().removePerson(person);
        }
        return new PersonListTracker(Optional.empty(), Optional.of(persons));
    }

    //=========== Filtered Person List Accessors ============================================================

    /**
     * Returns an unmodifiable view of the list of person backed by the internal list of
     * versionedUninurseBook
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireAllNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireAllNonNull(predicate);
        filteredPersons.setPredicate(person -> predicate.test(getPatient(person)));
    }

    //=========== Filtered Person Accessors =================================================================

    @Override
    public Patient getPatient(Person person) {
        requireAllNonNull(person);
        return persistentUninurseBook.getWorkingCopy().getPatient(person);
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return persistentUninurseBook.getWorkingCopy().getPatientList();
    }

    //=========== Other Accessors ===========================================================================

    @Override
    public void setPatientOfInterest(Patient patient) {
        this.patientOfInterest = Optional.ofNullable(patient);
    }

    @Override
    public Patient getPatientOfInterest() throws PatientOfInterestNotFoundException {
        return patientOfInterest.orElseThrow(() -> new PatientOfInterestNotFoundException());
    }

    @Override
    public void setSchedule(Schedule schedule) {
        this.schedule = Optional.ofNullable(schedule);
    }

    @Override
    public Schedule getSchedule() throws ScheduleNotFoundException {
        return schedule.orElseThrow(() -> new ScheduleNotFoundException());
    }

    @Override
    public void saveCurrentPersonListTracker() {
        personListTracker = persistentUninurseBook.getCurrentPersonListTracker();
    }

    @Override
    public PersonListTracker getPersonListTracker() {
        return personListTracker;
    }

    @Override
    public void updatePersons() {
        persistentUninurseBook.getWorkingCopy().updatePersons();
    }

    //=========== Undo and Redo =============================================================================

    @Override
    public boolean canUndo() {
        return persistentUninurseBook.canUndo();
    }

    @Override
    public boolean canRedo() {
        return persistentUninurseBook.canRedo();
    }

    @Override
    public CommandResult undo() {
        return persistentUninurseBook.undo();
    }

    @Override
    public CommandResult redo() {
        return persistentUninurseBook.redo();
    }

    @Override
    public void makeSnapshot(CommandResult commandResult) {
        persistentUninurseBook.makeSnapshot(commandResult);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return persistentUninurseBook.equals(other.persistentUninurseBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }
}
