package seedu.watson.model;

import static java.util.Objects.requireNonNull;
import static seedu.watson.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.watson.commons.core.GuiSettings;
import seedu.watson.commons.core.LogsCenter;
import seedu.watson.model.student.Name;
import seedu.watson.model.student.Student;

/**
 * Represents the in-memory model of the watson book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Database database;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given database and userPrefs.
     */
    public ModelManager(ReadOnlyDatabase addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with watson book: " + addressBook + " and user prefs " + userPrefs);

        this.database = new Database(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.database.getPersonList());
    }

    public ModelManager() {
        this(new Database(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getDatabaseFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setDatabaseFilePath(addressBookFilePath);
    }

    //=========== Database ================================================================================

    @Override
    public ReadOnlyDatabase getDatabase() {
        return database;
    }

    @Override
    public void setAddressBook(ReadOnlyDatabase database) {
        this.database.resetData(database);
    }

    @Override
    public boolean hasPerson(Student student) {
        requireNonNull(student);
        return database.hasPerson(student);
    }

    @Override
    public Student getStudentByName(Name name) {
        requireNonNull(name);
        ObservableList<Student> studentList = database.getPersonList();
        for (Student student : studentList) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void deletePerson(Student target) {
        database.removePerson(target);
    }

    @Override
    public void addPerson(Student student) {
        database.addPerson(student);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        database.setPerson(target, editedStudent);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredPersonList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    /**
     * sorts the student list by grade
     * @param isInAscending if true the list is set to ascending order, else descending
     */
    @Override
    public void sortListByGrade(boolean isInAscending, String subjectName) {
        this.database.sortByGrade(isInAscending, subjectName);
    }

    @Override
    public Predicate<Student> createPersonIsInClassPredicate(String studentClass) {
        return person -> person.getStudentClass().isSameClass(studentClass);
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
        return database.equals(other.database)
               && userPrefs.equals(other.userPrefs)
               && filteredStudents.equals(other.filteredStudents);
    }

}
