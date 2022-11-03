package seedu.application.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.application.commons.core.GuiSettings;
import seedu.application.logic.commands.CommandResult;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.Model;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.application.Application;

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
     * Returns the ApplicationBook.
     *
     * @see Model#getApplicationBook()
     */
    ReadOnlyApplicationBook getApplicationBook();

    /** Returns an unmodifiable view of the filtered list of applications */
    ObservableList<Application> getFilteredApplicationList();

    /** Returns an unmodifiable view of the filtered list of applications with existed interview*/
    ObservableList<Application> getApplicationListWithInterview();

    /** Returns an unmodifiable view of the filtered list of applications with upcoming interview*/
    ObservableList<Application> getApplicationListWithUpcomingInterview();

    /**
     * Returns the user prefs' application book file path.
     */
    Path getApplicationBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
