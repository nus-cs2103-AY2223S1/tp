package jarvis.model;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import jarvis.commons.core.GuiSettings;
import jarvis.commons.core.LogsCenter;
import jarvis.model.student.Student;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of JARVIS data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StudentBook studentBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyStudentBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.studentBook = new StudentBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.studentBook.getStudentList());
    }

    public ModelManager() {
        this(new StudentBook(), new UserPrefs());
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
    public Path getStudentBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setStudentBookFilePath(Path studentBookFilePath) {
        requireNonNull(studentBookFilePath);
        userPrefs.setAddressBookFilePath(studentBookFilePath);
    }

    //=========== StudentBook ================================================================================

    @Override
    public void setStudentBook(ReadOnlyStudentBook studentBook) {
        this.studentBook.resetData(studentBook);
    }

    @Override
    public ReadOnlyStudentBook getStudentBook() {
        return studentBook;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return studentBook.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        studentBook.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        studentBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        studentBook.setStudent(target, editedStudent);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
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
        return studentBook.equals(other.studentBook)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
