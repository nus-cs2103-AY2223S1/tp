package seedu.guest.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.guest.commons.core.GuiSettings;
import seedu.guest.logic.commands.CommandResult;
import seedu.guest.logic.commands.exceptions.CommandException;
import seedu.guest.logic.parser.exceptions.ParseException;
import seedu.guest.model.ReadOnlyGuestBook;
import seedu.guest.model.guest.Guest;

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
     * Returns the GuestBook.
     *
     * @see seedu.guest.model.Model#getGuestBook()
     */
    ReadOnlyGuestBook getGuestBook();

    /** Returns an unmodifiable view of the filtered list of guests */
    ObservableList<Guest> getFilteredGuestList();

    /**
     * Returns the user prefs' guest list file path.
     */
    Path getGuestBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
