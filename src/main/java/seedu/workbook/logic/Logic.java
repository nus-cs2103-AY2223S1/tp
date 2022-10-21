package seedu.workbook.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.workbook.commons.core.GuiSettings;
import seedu.workbook.logic.commands.CommandResult;
import seedu.workbook.logic.commands.exceptions.CommandException;
import seedu.workbook.logic.parser.exceptions.ParseException;
import seedu.workbook.model.ReadOnlyWorkBook;
import seedu.workbook.model.internship.Internship;

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
     * Returns the WorkBook.
     *
     * @see seedu.workbook.model.Model#getWorkBook()
     */
    ReadOnlyWorkBook getWorkBook();

    /** Returns an unmodifiable view of the filtered list of internships */
    ObservableList<Internship> getFilteredInternshipList();

    /** Returns the user prefs' work book file path. */
    Path getWorkBookFilePath();

    /** Returns the user prefs' GUI settings. */
    GuiSettings getGuiSettings();

    /** Set the user prefs' GUI settings. */
    void setGuiSettings(GuiSettings guiSettings);
}
