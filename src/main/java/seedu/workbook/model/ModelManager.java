package seedu.workbook.model;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.workbook.commons.core.GuiSettings;
import seedu.workbook.commons.core.LogsCenter;
import seedu.workbook.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final WorkBook workBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given workBook and userPrefs.
     */
    public ModelManager(ReadOnlyWorkBook workBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(workBook, userPrefs);

        logger.fine("Initializing with work book: " + workBook + " and user prefs " + userPrefs);

        this.workBook = new WorkBook(workBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.workBook.getPersonList());
    }

    public ModelManager() {
        this(new WorkBook(), new UserPrefs());
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
    public Path getWorkBookFilePath() {
        return userPrefs.getWorkBookFilePath();
    }

    @Override
    public void setWorkBookFilePath(Path workBookFilePath) {
        requireNonNull(workBookFilePath);
        userPrefs.setWorkBookFilePath(workBookFilePath);
    }

    //=========== WorkBook ================================================================================

    @Override
    public void setWorkBook(ReadOnlyWorkBook workBook) {
        this.workBook.resetData(workBook);
    }

    @Override
    public ReadOnlyWorkBook getWorkBook() {
        return workBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return workBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        workBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        workBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        workBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedWorkBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
        return workBook.equals(other.workBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
