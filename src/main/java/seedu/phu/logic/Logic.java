package seedu.phu.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.phu.commons.core.GuiSettings;
import seedu.phu.logic.commands.CommandResult;
import seedu.phu.logic.commands.exceptions.CommandException;
import seedu.phu.logic.parser.exceptions.ParseException;
import seedu.phu.model.ReadOnlyInternshipBook;
import seedu.phu.model.internship.Internship;

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
     * Returns the InternshipBook.
     *
     * @see seedu.phu.model.Model#getInternshipBook()
     */
    ReadOnlyInternshipBook getInternshipBook();

    /** Returns an unmodifiable view of the filtered list of internships */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Returns the user prefs' internship book file path.
     */
    Path getInternshipBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
