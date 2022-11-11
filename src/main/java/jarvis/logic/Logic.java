package jarvis.logic;

import java.nio.file.Path;

import jarvis.commons.core.GuiSettings;
import jarvis.logic.commands.CommandResult;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.Lesson;
import jarvis.model.ReadOnlyLessonBook;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.ReadOnlyTaskBook;
import jarvis.model.Student;
import jarvis.model.Task;
import javafx.collections.ObservableList;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the StudentBook.
     *
     * @see jarvis.model.Model#getStudentBook()
     */
    ReadOnlyStudentBook getStudentBook();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns the user prefs' student book file path.
     */
    Path getStudentBookFilePath();

    /**
     * Returns the TaskBook.
     *
     * @see jarvis.model.Model#getTaskBook()
     */
    ReadOnlyTaskBook getTaskBook();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Returns the user prefs' task book file path.
     */
    Path getTaskBookFilePath();

    /**
     * Returns the LessonBook.
     *
     * @see jarvis.model.Model#getLessonBook()
     */
    ReadOnlyLessonBook getLessonBook();

    /** Returns an unmodifiable view of the filtered list of lessons */
    ObservableList<Lesson> getFilteredLessonList();

    /**
     * Returns the user prefs' lesson book file path.
     */
    Path getLessonBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
