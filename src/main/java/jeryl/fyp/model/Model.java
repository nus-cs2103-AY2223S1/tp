package jeryl.fyp.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import jeryl.fyp.commons.core.GuiSettings;
import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.DeadlineList;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

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
     * Returns the user prefs' FYP manager file path.
     */
    Path getFypManagerFilePath();

    /**
     * Sets the user prefs' FYP manager file path.
     */
    void setFypManagerFilePath(Path fypManagerFilePath);

    /**
     * Replaces FYP manager data with the data in {@code fypManager}.
     */
    void setFypManager(ReadOnlyFypManager fypManager);

    /** Returns the FypManager */
    ReadOnlyFypManager getFypManager();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the FYP manager.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the FYP manager.
     */
    void deleteStudent(Student target);


    /**
     * Adds the given student.
     * {@code student} must not already exist in the FYP manager.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the FYP manager.
     * The student identity of {@code editedStudent} must not be the same as another existing student
     * in the FYP manager.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Returns true if a deadline with the same identity as {@code deadline} exists in the student's DeadlineList.
     */
    boolean hasDeadline(Student student, Deadline deadline);

    /**
     * Deletes the given deadline for the given student.
     * The deadline must exist in the DeadlineList of the given student.
     */
    void deleteDeadline(Student student, Deadline deadline);

    /**
     * Adds the given deadline to the DeadlineList of the given student.
     * {@code deadline} must not already exist in the DeadlineList of the given student.
     */
    void addDeadline(Student student, Deadline deadline);

    /**
     * Replaces the given deadline {@code target} with {@code editedDeadline}.
     * {@code target} must not already exist in the DeadlineList of the given student.
     * The deadline identity of {@code editedDeadline} must not be the same as another existing deadline
     * for the given student {@code student}.
     */
    void setDeadline(Student student, Deadline target, Deadline editedDeadline);

    /**
     * Gets the unique student with the specified StudentId
     * {@code studentId} is unique so it should output 1 student only
     */
    Student getStudentByStudentId(StudentId studentId);

    /**
     * Gets the index position in UniqueStudentList with the specified StudentId
     * {@code studentId} is unique so it should output 1 student only
     */
    Index getIndexByStudentId(StudentId studentId);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the sorted Uncompleted student list by project name */
    ObservableList<Student> getSortedByProjectNameUncompletedStudentList();

    /**
     * Returns an unmodifiable view of the sorted Uncompleted student list by project status
     * followed by alphabetical order
     */
    ObservableList<Student> getSortedByProjectStatusUncompletedStudentList();

    /**
     * Returns an unmodifiable view of the sorted Completed student list by project name
     * which is equivalent to sorting by  alphabetical order
     */
    ObservableList<Student> getSortedCompletedStudentList();

    /** Returns an unmodifiable view of the uncompleted list of students */
    ObservableList<Student> getUncompletedStudentList();

    /** Returns an unmodifiable view of the completed list of students */
    ObservableList<Student> getCompletedStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    DeadlineList listDeadlineUnderStudent(Student student);
}
