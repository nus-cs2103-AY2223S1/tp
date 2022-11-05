package seedu.travelr.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.travelr.commons.core.GuiSettings;
import seedu.travelr.logic.commands.CommandResult;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.Model;
import seedu.travelr.model.ReadOnlyTravelr;
import seedu.travelr.model.SummaryVariables;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.ObservableTrip;
import seedu.travelr.model.trip.Trip;

/**
 * API of the Logic component
 */
public interface Logic {
    ObservableTrip getSelectedTrip();

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
     * @see Model#getTravelr()
     */
    ReadOnlyTravelr getTravelr();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Trip> getFilteredTripList();

    /** Returns an unmodifiable view of the filtered list of events */
    ObservableList<Event> getFilteredEventList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getTravelrFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    SummaryVariables getTravelrSummary();

    void refreshTravelrSummary();
}
