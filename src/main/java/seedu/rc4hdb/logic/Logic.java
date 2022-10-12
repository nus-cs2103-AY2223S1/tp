package seedu.rc4hdb.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.rc4hdb.commons.core.GuiSettings;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.resident.Resident;

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
     * Returns the ResidentBook.
     *
     * @see seedu.rc4hdb.model.Model#getResidentBook()
     */
    ReadOnlyResidentBook getResidentBook();

    /** Returns an unmodifiable view of the filtered list of residents */
    ObservableList<Resident> getFilteredResidentList();

    /**
     * Returns the user prefs' resident book file path.
     */
    Path getResidentBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    ObservableList<String> getObservableFields();
}
