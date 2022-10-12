package tuthub.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import tuthub.commons.core.GuiSettings;
import tuthub.logic.commands.CommandResult;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.logic.parser.exceptions.ParseException;
import tuthub.model.ReadOnlyTuthub;
import tuthub.model.tutor.Tutor;

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
     * Returns the Tuthub.
     *
     * @see tuthub.model.Model#getTuthub()
     */
    ReadOnlyTuthub getTuthub();

    /** Returns an unmodifiable view of the filtered list of tutors */
    ObservableList<Tutor> getFilteredTutorList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getTuthubFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
