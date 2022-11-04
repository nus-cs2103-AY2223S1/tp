package seedu.hrpro.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.hrpro.commons.core.GuiSettings;
import seedu.hrpro.logic.commands.CommandResult;
import seedu.hrpro.logic.commands.exceptions.CommandException;
import seedu.hrpro.logic.parser.exceptions.ParseException;
import seedu.hrpro.model.ReadOnlyHrPro;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.task.Task;

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
     * Returns the HrPro.
     *
     * @see seedu.hrpro.model.Model#getHrPro()
     */
    ReadOnlyHrPro getHrPro();

    /** Returns an unmodifiable view of the filtered list of projects */
    ObservableList<Project> getFilteredProjectList();

    /** Returns an unmodifiable view of the filtered list of staff */
    ObservableList<Staff> getFilteredStaffList();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Returns the user prefs' hr pro file path.
     */
    Path getHrProFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

}
