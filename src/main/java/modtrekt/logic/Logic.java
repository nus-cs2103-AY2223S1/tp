package modtrekt.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import modtrekt.commons.core.GuiSettings;
import modtrekt.logic.commands.CommandResult;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.model.Model;
import modtrekt.model.ReadOnlyModuleList;
import modtrekt.model.ReadOnlyTaskBook;
import modtrekt.model.module.Module;
import modtrekt.model.task.Task;


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
     * Returns the task book.
     *
     * @see Model#getTaskBook()
     */
    ReadOnlyTaskBook getTaskBook();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Returns the module list.
     *
     * @see Model#getModuleList()
     */
    ReadOnlyModuleList getModuleList();

    /** Returns an unmodifiable view of the filtered list of modules */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Returns the user prefs' TaskBook file path.
     */
    Path getTaskBookFilePath();

    /**
     * Returns the user prefs' ModuleList file path.
     */
    Path getModuleListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
