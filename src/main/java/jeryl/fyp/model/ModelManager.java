package jeryl.fyp.model;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import jeryl.fyp.commons.core.GuiSettings;
import jeryl.fyp.commons.core.LogsCenter;
import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.DeadlineList;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;

/**
 * Represents the in-memory model of the FYP manager data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FypManager fypManager;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final SortedList<Student> sortedByProjectNameUncompletedStudents;
    private final SortedList<Student> sortedByProjectStatusUncompletedStudents;
    private final SortedList<Student> sortedCompletedStudents;
    private final FilteredList<Student> completedStudents;
    private final FilteredList<Student> uncompletedStudents;

    /**
     * Initializes a ModelManager with the given fypManager and userPrefs.
     */
    public ModelManager(ReadOnlyFypManager fypManager, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(fypManager, userPrefs);

        logger.fine("Initializing with FYP manager: " + fypManager + " and user prefs " + userPrefs);

        this.fypManager = new FypManager(fypManager);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.fypManager.getStudentList());
        completedStudents = new FilteredList<>(this.fypManager.getCompletedStudentList());
        uncompletedStudents = new FilteredList<>(this.fypManager.getUncompletedStudentList());
        sortedByProjectNameUncompletedStudents =
                new SortedList<>(this.fypManager.getSortedByProjectNameUncompletedStudentList());
        sortedByProjectStatusUncompletedStudents =
                new SortedList<>(this.fypManager.getSortedByProjectStatusUncompletedStudentList());
        sortedCompletedStudents =
                new SortedList<>(this.fypManager.getSortedCompletedStudentList());
    }

    public ModelManager() {
        this(new FypManager(), new UserPrefs());
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
    public Path getFypManagerFilePath() {
        return userPrefs.getFypManagerFilePath();
    }

    @Override
    public void setFypManagerFilePath(Path fypManagerFilePath) {
        requireNonNull(fypManagerFilePath);
        userPrefs.setFypManagerFilePath(fypManagerFilePath);
    }

    //=========== FypManager ================================================================================

    @Override
    public void setFypManager(ReadOnlyFypManager fypManager) {
        this.fypManager.resetData(fypManager);
    }

    @Override
    public ReadOnlyFypManager getFypManager() {
        return fypManager;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return fypManager.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        fypManager.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        requireNonNull(student);
        fypManager.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }


    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        fypManager.setStudent(target, editedStudent);
    }

    @Override
    public boolean hasDeadline(Student student, Deadline deadline) {
        requireAllNonNull(student, deadline);
        return fypManager.hasDeadline(student, deadline);
    }

    @Override
    public void deleteDeadline(Student student, Deadline deadline) {
        requireAllNonNull(student, deadline);
        fypManager.removeDeadline(student, deadline);
    }

    @Override
    public void addDeadline(Student student, Deadline deadline) {
        requireAllNonNull(student, deadline);
        fypManager.addDeadline(student, deadline);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setDeadline(Student student, Deadline target, Deadline editedDeadline) {
        requireAllNonNull(student, target, editedDeadline);
        fypManager.setDeadline(student, target, editedDeadline);
    }

    @Override
    public Student getStudentByStudentId(StudentId studentId) {
        requireNonNull(studentId);

        return fypManager.getStudentByStudentId(studentId);
    }

    @Override
    public Index getIndexByStudentId(StudentId studentId) {
        requireNonNull(studentId);

        return fypManager.getIndexByStudentId(studentId);
    }



    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedFypManager}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Student} who have yet to complete
     * their projects, backed by the internal list of {@code versionedFypManager}
     */
    @Override
    public ObservableList<Student> getUncompletedStudentList() {
        return uncompletedStudents;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Student} who have completed their projects,
     * backed by the internal list of {@code versionedFypManager}
     */
    @Override
    public ObservableList<Student> getCompletedStudentList() {
        return completedStudents;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Student} who have yet to complete
     * their projects sorted by their project name,
     * backed by the internal list of {@code versionedFypManager}
     */
    @Override
    public ObservableList<Student> getSortedByProjectNameUncompletedStudentList() {
        return sortedByProjectNameUncompletedStudents;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Student} who have yet to complete
     * their projects sorted by their project status
     * backed by the internal list of {@code versionedFypManager}
     */
    @Override
    public ObservableList<Student> getSortedByProjectStatusUncompletedStudentList() {
        return sortedByProjectStatusUncompletedStudents;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Student} who have completed
     * their projects sorted by their project name (or equivalently alphabetical order)
     * backed by the internal list of {@code versionedFypManager}
     */
    @Override
    public ObservableList<Student> getSortedCompletedStudentList() {
        return sortedCompletedStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
        completedStudents.setPredicate(predicate);
        uncompletedStudents.setPredicate(predicate);
    }

    @Override
    public DeadlineList listDeadlineUnderStudent(Student student) {
        return fypManager.getDeadlineList(student);
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
        return fypManager.equals(other.fypManager)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
