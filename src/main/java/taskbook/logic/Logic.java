package taskbook.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import taskbook.commons.core.GuiSettings;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.model.Model;
import taskbook.model.ReadOnlyTaskBook;
import taskbook.model.person.Person;
import taskbook.model.task.Task;

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
     * Returns the TaskBook.
     *
     * @see Model#getTaskBook()
     */
    ReadOnlyTaskBook getTaskBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the sorted list of tasks */
    ObservableList<Person> getSortedPersonList();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the sorted list of tasks */
    ObservableList<Task> getSortedTaskList();

    /**
     * Returns the user prefs' task book file path.
     */
    Path getTaskBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Gets the previous command in the history.
     */
    String getPreviousCommand();

    /**
     * Gets the next command in the history.
     */
    String getNextCommand();
}
