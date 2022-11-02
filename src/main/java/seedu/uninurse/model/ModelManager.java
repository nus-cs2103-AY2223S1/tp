package seedu.uninurse.model;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.uninurse.commons.core.GuiSettings;
import seedu.uninurse.commons.core.LogsCenter;
import seedu.uninurse.model.exceptions.PatientOfInterestNotFoundException;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.DateTime;

/**
 * Represents the in-memory model of the uninurse book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PersistentUninurseBook persistentUninurseBook;

    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPersons;

    private Optional<Patient> patientOfInterest;
    private Optional<DateTime> dayOfInterest;
    private PatientListTracker savedPair;

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
        this.savedPair = new PatientListTracker();
    }

    public ModelManager() {
        this(new UninurseBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getUninurseBookFilePath() {
        return userPrefs.getUninurseBookFilePath();
    }

    @Override
    public void setUninurseBookFilePath(Path uninurseBookFilePath) {
        requireNonNull(uninurseBookFilePath);
        userPrefs.setUninurseBookFilePath(uninurseBookFilePath);
    }

    //=========== UninurseBook ================================================================================

    @Override
    public ReadOnlyUninurseBook getUninurseBook() {
        return persistentUninurseBook.getWorkingCopy();
    }

    @Override
    public void setUninurseBook(ReadOnlyUninurseBook uninurseBook) {
        this.persistentUninurseBook.getWorkingCopy().resetData(uninurseBook);
    }

    @Override
    public boolean hasPerson(Patient person) {
        requireNonNull(person);
        return persistentUninurseBook.getWorkingCopy().hasPerson(person);
    }

    @Override
    public void deletePerson(Patient target) {
        persistentUninurseBook.getWorkingCopy().removePerson(target);
        makeSnapshot(new PatientListTracker(null, target));
    }

    @Override
    public void clearPersons(List<Patient> targets) {
        for (Patient target : targets) {
            persistentUninurseBook.getWorkingCopy().removePerson(target);
        }
        makeSnapshot(new PatientListTracker(null, targets));
    }

    @Override
    public void addPerson(Patient person) {
        persistentUninurseBook.getWorkingCopy().addPerson(person);
        makeSnapshot(new PatientListTracker(person, null));
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Patient target, Patient editedPerson) {
        requireAllNonNull(target, editedPerson);
        persistentUninurseBook.getWorkingCopy().setPerson(target, editedPerson);
        makeSnapshot(new PatientListTracker(editedPerson, target));
    }

    //=========== Filtered Patient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedUninurseBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Other Accessors =============================================================

    @Override
    public void setPatientOfInterest(Patient patient) {
        this.patientOfInterest = Optional.ofNullable(patient);
    }

    @Override
    public Patient getPatientOfInterest() throws PatientOfInterestNotFoundException {
        return this.patientOfInterest.orElseThrow(() -> new PatientOfInterestNotFoundException());
    }

    @Override
    public void setDayOfInterest(DateTime dayOfInterest) {
        this.dayOfInterest = Optional.ofNullable(dayOfInterest);
    }

    @Override
    public Schedule getSchedule() {
        return new Schedule(persistentUninurseBook.getWorkingCopy().getPersonList(), dayOfInterest.get());
    }

    @Override
    public void saveCurrentPatientListTracker() {
        this.savedPair = persistentUninurseBook.getCurrentPair();
    }

    @Override
    public PatientListTracker getSavedPatientListTracker() {
        return this.savedPair;
    }

    //=========== Undo and Redo =============================================================

    @Override
    public boolean canUndo() {
        return persistentUninurseBook.canUndo();
    }

    @Override
    public boolean canRedo() {
        return persistentUninurseBook.canRedo();
    }

    @Override
    public void undo() {
        persistentUninurseBook.undo();
    }

    @Override
    public void redo() {
        persistentUninurseBook.redo();
    }

    @Override
    public void makeSnapshot(PatientListTracker patientListTracker) {
        persistentUninurseBook.makeSnapshot(patientListTracker);
    }

    //=========== Other Accessors =============================================================

    @Override
    public void updateRecurringTasks() {
        filteredPersons.forEach(p -> p.getTasks().updateTasks());
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
