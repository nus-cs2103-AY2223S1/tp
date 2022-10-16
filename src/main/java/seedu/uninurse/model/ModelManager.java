package seedu.uninurse.model;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.uninurse.commons.core.GuiSettings;
import seedu.uninurse.commons.core.LogsCenter;
import seedu.uninurse.model.person.Patient;

/**
 * Represents the in-memory model of the uninurse book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UninurseBook uninurseBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPersons;

    private boolean taskListFlag;

    /**
     * Initializes a ModelManager with the given uninurseBook and userPrefs.
     */
    public ModelManager(ReadOnlyUninurseBook uninurseBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(uninurseBook, userPrefs);

        logger.fine("Initializing with uninurse book: " + uninurseBook + " and user prefs " + userPrefs);

        this.uninurseBook = new UninurseBook(uninurseBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.uninurseBook.getPersonList());

        taskListFlag = false;
    }

    public ModelManager() {
        this(new UninurseBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
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
    public void setUninurseBook(ReadOnlyUninurseBook uninurseBook) {
        this.uninurseBook.resetData(uninurseBook);
    }

    @Override
    public ReadOnlyUninurseBook getUninurseBook() {
        return uninurseBook;
    }

    @Override
    public boolean hasPerson(Patient person) {
        requireNonNull(person);
        return uninurseBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Patient target) {
        uninurseBook.removePerson(target);
    }

    @Override
    public void addPerson(Patient person) {
        uninurseBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Patient target, Patient editedPerson) {
        requireAllNonNull(target, editedPerson);

        uninurseBook.setPerson(target, editedPerson);
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
        taskListFlag = false;
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPersonListWithTasks(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        taskListFlag = true;
        filteredPersons.setPredicate(predicate);
    }

    //=========== Other Accessors =============================================================

    @Override
    public Supplier<Boolean> getTaskListFlagSupplier() {
        return (() -> taskListFlag);
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
        return uninurseBook.equals(other.uninurseBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
