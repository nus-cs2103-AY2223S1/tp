package jarvis.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import jarvis.commons.core.GuiSettings;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    Predicate<Lesson> PREDICATE_SHOW_ALL_LESSONS = unused -> true;

    //=========== UserPrefs ==================================================================================

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
     * Returns the user prefs' student book file path.
     */
    Path getStudentBookFilePath();

    /**
     * Sets the user prefs' student book file path.
     */
    void setStudentBookFilePath(Path studentBookFilePath);

    /**
     * Returns the user prefs' task book file path.
     */
    Path getTaskBookFilePath();

    /**
     * Sets the user prefs' task book file path.
     */
    void setTaskBookFilePath(Path taskBookFilePath);

    /**
     * Returns the user prefs' lesson book file path.
     */
    Path getLessonBookFilePath();

    /**
     * Sets the user prefs' lesson book file path.
     */
    void setLessonBookFilePath(Path lessonBookFilePath);

    //=========== StudentBook ================================================================================

    /**
     * Replaces student book data with the data in {@code studentBook}.
     */
    void setStudentBook(ReadOnlyStudentBook studentBook);

    /** Returns the StudentBook */
    ReadOnlyStudentBook getStudentBook();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the student book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the student book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code targetStudent} with {@code editedStudent}.
     * {@code targetStudent} must exist in the student book.
     * The identity of {@code editedStudent} must not be the same as another existing student in the student book.
     */
    void setStudent(Student targetStudent, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    //=========== TaskBook ================================================================================

    /**
     * Replaces task book data with the data in {@code taskBook}.
     */
    void setTaskBook(ReadOnlyTaskBook taskBook);

    /** Returns the TaskBook */
    ReadOnlyTaskBook getTaskBook();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task book.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the task book.
     */
    void deleteTask(Task targetTask);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task book.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code targetTask} with {@code editedTask}.
     * {@code targetTask} must exist in the task book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task book.
     */
    void setTask(Task targetTask, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    //=========== LessonBook ================================================================================

    /**
     * Replaces lesson book data with the data in {@code lessonBook}.
     */
    void setLessonBook(ReadOnlyLessonBook lessonBook);

    /** Returns the LessonBook */
    ReadOnlyLessonBook getLessonBook();

    /**
     * Returns true if a lesson with the same identity as {@code lesson} exists in the lesson book.
     */
    boolean hasLesson(Lesson lesson);

    /**
     * Deletes the given lesson.
     * The lesson must exist in the lesson book.
     */
    void deleteLesson(Lesson targetLesson);

    /**
     * Adds the given lesson.
     * {@code lesson} must not already exist in the lesson book.
     */
    void addLesson(Lesson lesson);

    /**
     * Replaces the given lesson {@code targetLesson} with {@code editedLesson}.
     * {@code targetLesson} must exist in the lesson book.
     * The lesson identity of {@code editedLesson} must not be the same as another existing lesson in the lesson book.
     */
    void setLesson(Lesson targetLesson, Lesson editedLesson);

    /** Returns an unmodifiable view of the filtered lesson list */
    ObservableList<Lesson> getFilteredLessonList();

    /**
     * Updates the filter of the filtered lesson list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredLessonList(Predicate<Lesson> predicate);

    /**
     * Returns true if {@lesson} has clashing time period with a lesson in the lesson book.
     */
    boolean hasPeriodClash(Lesson lesson);
}
