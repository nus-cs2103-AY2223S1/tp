package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeKey;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    Predicate<TutorialGroup> PREDICATE_SHOW_ALL_TUTORIAL_GROUPS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

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
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

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
     * Find the student based on name
     * @return the student
     */
    Student findStudent(String name);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * address book.
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
     * Updates the filter of the filtered student list to filter by the given {@code tutorialGroup}.
     * @throws NullPointerException if {@code tutorialGroup} is null.
     */
    void updateFilteredStudentListByTg(TutorialGroup tutorialGroup);

    /**
     * Returns true if a tutorial group with the same identity as {@code tutorialGroup} exists in the address book.
     */
    boolean hasTutorialGroup(TutorialGroup tutorialGroup);

    /**
     * Deletes the given tutorial group.
     * The person must exist in the address book.
     */
    void deleteTutorialGroup(TutorialGroup target);

    /**
     * Adds the given tutorial group.
     * {@code tutorialGroup} must not already exist in the address book.
     */
    void addTutorialGroup(TutorialGroup tutorialGroup);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<TutorialGroup> getFilteredTutorialGroupList();


    /**
     * Updates the filter of the filtered tutorial group list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTutorialGroupList(Predicate<TutorialGroup> predicate);


    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     */
    boolean hasTask(Task target);

    /**
     * Deletes the given task.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    void addGrade(GradeKey gradeKey, Grade grade);
    ObservableMap<GradeKey, Grade> getGradeMap();

    /**
     * Updates the grade map by updating the tasks in the map.
     * @param taskToEdit the current task associated with student(s)
     * @param editedTask the task to be associated with taskToEdit's student(s) after the update
     */
    void updateGrades(Task taskToEdit, Task editedTask);
}
