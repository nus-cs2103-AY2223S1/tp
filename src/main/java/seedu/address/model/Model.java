package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.student.Class;
import seedu.address.model.student.Student;
import seedu.address.model.timerange.TimeRange;

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
     * Returns the user prefs' address book file path.
     */
    Path getTeachersPetFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setTeachersPetFilePath(Path teachersPetFilePath);

    /**
     * Replaces teacher's pet data with the data in {@code teachersPet}.
     */
    void setTeachersPet(ReadOnlyTeachersPet teachersPet);

    /** Returns the TeachersPet */
    ReadOnlyTeachersPet getTeachersPet();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in Teacher's Pet.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Returns the first available class within the range specified by {@code TimeRange}
     * @return the first available {@code Class}
     */
    Class findAvailableClass(TimeRange timeRange);

    /**
     * Sorts the current filtered student list with the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortStudents(Comparator<Student> comparator);
    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /** Returns an unmodifiable view of the schedule list for that day*/
    ObservableList<Student> getFilteredScheduleList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredScheduleList(Predicate<Student> predicate);

    /**
     * Updates previous state of the address book.
     */
    void updateTeachersPetHistory();

    /**
     * Undo last change made to state of teacher's pet.
     */
    void undo() throws CommandException;

    /**
     * Deletes the latest addition in the ArrayList of Teachers Pet.
     */
    void deleteTeachersPetHistory();

}
