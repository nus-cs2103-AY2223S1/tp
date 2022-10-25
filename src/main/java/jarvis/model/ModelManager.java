package jarvis.model;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import jarvis.commons.core.GuiSettings;
import jarvis.commons.core.LogsCenter;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of JARVIS data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StudentBook studentBook;
    private final TaskBook taskBook;
    private final LessonBook lessonBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Lesson> filteredLessons;

    /**
     * Initializes a ModelManager with the given studentBook, taskBook, lessonBook and userPrefs.
     */
    public ModelManager(ReadOnlyStudentBook studentBook, ReadOnlyTaskBook taskBook,
                        ReadOnlyLessonBook lessonBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(studentBook, taskBook, lessonBook, userPrefs);

        logger.fine("Initializing with student book: " + studentBook + "and task book:" + taskBook
                + "and lesson book:" + lessonBook + " and user prefs " + userPrefs);

        this.studentBook = new StudentBook(studentBook);
        this.taskBook = new TaskBook(taskBook);
        this.lessonBook = new LessonBook(lessonBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.studentBook.getStudentList());
        filteredTasks = new FilteredList<>(this.taskBook.getTaskList());
        filteredLessons = new FilteredList<>(this.lessonBook.getLessonList());
    }

    /**
     * Initializes a ModelManager with the given studentBook and userPrefs.
     */
    public ModelManager(ReadOnlyStudentBook studentBook, ReadOnlyUserPrefs userPrefs) {
        this(studentBook, new TaskBook(), new LessonBook(), userPrefs);
    }

    /**
     * Initializes a ModelManager with the given taskBook and userPrefs.
     */
    public ModelManager(ReadOnlyTaskBook taskBook, ReadOnlyUserPrefs userPrefs) {
        this(new StudentBook(), taskBook, new LessonBook(), userPrefs);
    }

    /**
     * Initializes a ModelManager with the given LessonBook and userPrefs.
     */
    public ModelManager(ReadOnlyLessonBook lessonBook, ReadOnlyUserPrefs userPrefs) {
        this(new StudentBook(), new TaskBook(), lessonBook, userPrefs);
    }

    public ModelManager() {
        this(new StudentBook(), new TaskBook(), new LessonBook(), new UserPrefs());
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
        return userPrefs.getStudentBookFilePath();
    }

    @Override
    public void setStudentBookFilePath(Path studentBookFilePath) {
        requireNonNull(studentBookFilePath);
        userPrefs.setStudentBookFilePath(studentBookFilePath);
    }

    @Override
    public Path getTaskBookFilePath() {
        return userPrefs.getTaskBookFilePath();
    }

    @Override
    public void setTaskBookFilePath(Path taskBookFilePath) {
        requireNonNull(taskBookFilePath);
        userPrefs.setTaskBookFilePath(taskBookFilePath);
    }

    @Override
    public Path getLessonBookFilePath() {
        return userPrefs.getLessonBookFilePath();
    }

    @Override
    public void setLessonBookFilePath(Path lessonBookFilePath) {
        requireNonNull(lessonBookFilePath);
        userPrefs.setTaskBookFilePath(lessonBookFilePath);
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
    public void deleteStudent(Student targetStudent) {
        studentBook.removeStudent(targetStudent);
    }

    @Override
    public void addStudent(Student student) {
        studentBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student targetStudent, Student editedStudent) {
        requireAllNonNull(targetStudent, editedStudent);
        studentBook.setStudent(targetStudent, editedStudent);
        lessonBook.setStudent(targetStudent, editedStudent);
    }

    //=========== TaskBook ================================================================================

    @Override
    public void setTaskBook(ReadOnlyTaskBook taskBook) {
        this.taskBook.resetData(taskBook);
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        return taskBook;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskBook.hasTask(task);
    }

    @Override
    public void deleteTask(Task targetTask) {
        taskBook.removeTask(targetTask);
    }

    @Override
    public void addTask(Task task) {
        taskBook.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task targetTask, Task editedTask) {
        requireAllNonNull(targetTask, editedTask);

        taskBook.setTask(targetTask, editedTask);
    }

    //=========== LessonBook ================================================================================

    @Override
    public void setLessonBook(ReadOnlyLessonBook lessonBook) {
        this.lessonBook.resetData(lessonBook);
    }

    @Override
    public ReadOnlyLessonBook getLessonBook() {
        return lessonBook;
    }

    @Override
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessonBook.hasLesson(lesson);
    }

    @Override
    public void deleteLesson(Lesson targetLesson) {
        lessonBook.removeLesson(targetLesson);
    }

    @Override
    public void addLesson(Lesson lesson) {
        lessonBook.addLesson(lesson);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
    }

    @Override
    public void setLesson(Lesson targetLesson, Lesson editedLesson) {
        requireAllNonNull(targetLesson, editedLesson);

        lessonBook.setLesson(targetLesson, editedLesson);
    }

    @Override
    public boolean hasPeriodClash(Lesson lesson) {
        requireNonNull(lesson);
        return lessonBook.hasPeriodClash(lesson);
    }
    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedStudentBook}
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
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public ObservableList<Lesson> getFilteredLessonList() {
        return filteredLessons;
    }

    @Override
    public void updateFilteredLessonList(Predicate<Lesson> predicate) {
        requireNonNull(predicate);
        filteredLessons.setPredicate(predicate);
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
                && filteredStudents.equals(other.filteredStudents)
                && filteredTasks.equals(other.filteredTasks)
                && filteredLessons.equals(other.filteredLessons);
    }

}
