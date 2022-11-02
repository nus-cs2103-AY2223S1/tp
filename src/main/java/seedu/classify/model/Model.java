package seedu.classify.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.classify.commons.core.GuiSettings;
import seedu.classify.model.student.Student;

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
     * Returns the user prefs' student record file path.
     */
    Path getStudentRecordFilePath();

    /**
     * Sets the user prefs' student record file path.
     */
    void setStudentRecordFilePath(Path studentRecordFilePath);

    /**
     * Replaces student record data with the data in {@code studentRecord}.
     */
    void setStudentRecord(ReadOnlyStudentRecord studentRecord);

    /** Returns the StudentRecord */
    ReadOnlyStudentRecord getStudentRecord();

    /** Sorts the StudentRecord. */
    void sortStudentRecord(Comparator<Student> studentComparator);

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student record.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the student record.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the student record.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the student record.
     * The student identity of {@code editedStudent} must not be the same as
     * another existing student in the student record.
     */
    void setStudent(Student target, Student editedStudent);


    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Calculates the mean {@code String exam} score of the filtered student list.
     */
    double calculateExamMean(String exam);

    /**
     * Toggles between showing all students' information and hiding students' parent details.
     */
    void toggleStudentListInfoConcise();

    /**
     * Returns true if the filtered student list should only show the student's name and ID.
     */
    boolean isStudentListInfoConcise();

    /**
     * Returns the filtered student object.
     */
    FilteredStudents getFilteredStudents();

    /**
     * Updates the previous predicate with the current predicate.
     */
    void storePredicate(Predicate<Student> p);

    /**
     * Returns the predicate of the last executed command.
     */
    Predicate<Student> getPrevPredicate();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student record, where the
     * check excludes a particular student.
     * @param studentToExclude the student to be excluded from the check.
     * @param studentToCheck the student to be checked.
     * @return true if there is a matching student identity in the student record
     */
    boolean excludesAndHasStudent(Student studentToExclude, Student studentToCheck);
}
