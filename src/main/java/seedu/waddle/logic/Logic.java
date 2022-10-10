package seedu.waddle.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.waddle.commons.core.GuiSettings;
import seedu.waddle.logic.commands.CommandResult;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.ReadOnlyWaddle;
import seedu.waddle.model.itinerary.Itinerary;

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
     * Returns the AddressBook.
     *
     * @see seedu.waddle.model.Model#getWaddle()
     */
    ReadOnlyWaddle getWaddle();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Itinerary> getFilteredItineraryList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getWaddleFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
