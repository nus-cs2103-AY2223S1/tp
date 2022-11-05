package seedu.realtime.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.realtime.commons.core.GuiSettings;
import seedu.realtime.logic.commands.CommandResult;
import seedu.realtime.logic.commands.exceptions.CommandException;
import seedu.realtime.logic.parser.exceptions.ParseException;
import seedu.realtime.model.ReadOnlyRealTime;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.meeting.Meeting;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.person.Client;
import seedu.realtime.model.person.Person;

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
     * Returns the realTime.
     *
     * @see seedu.realtime.model.Model#getRealTime()
     */
    ReadOnlyRealTime getRealTime();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of clients */
    ObservableList<Client> getFilteredClientList();

    /** Returns an unmodifiable view of the filtered list of offers*/
    ObservableList<Offer> getFilteredOfferList();

    /** Returns an unmodifiable view of the filtered list of listings*/
    ObservableList<Listing> getFilteredListingList();

    /** Returns an unmodifiable view of the filtered list of listings*/
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getRealTimeFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

}
