package jeryl.fyp.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import jeryl.fyp.commons.core.GuiSettings;
import jeryl.fyp.logic.commands.CommandResult;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.ReadOnlyFypManager;
import jeryl.fyp.model.student.Student;

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
     * @throws RuntimeException If an error occurs during model state change.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException, RuntimeException;

    /**
     * Returns the FypManager.
     *
     * @see jeryl.fyp.model.Model#getFypManager()
     */
    ReadOnlyFypManager getFypManager();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    ObservableList<Student> getSortedByProjectNameUncompletedStudentList();

    ObservableList<Student> getSortedByProjectStatusUncompletedStudentList();

    ObservableList<Student> getSortedCompletedStudentList();

    /** Returns an unmodifiable view of the uncompleted list of students */
    ObservableList<Student> getUncompletedStudentList();

    /** Returns an unmodifiable view of the completed list of students */
    ObservableList<Student> getCompletedStudentList();

    /**
     * Returns the user prefs' FYP manager file path.
     */
    Path getFypManagerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
