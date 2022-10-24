package seedu.rc4hdb.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.rc4hdb.commons.core.GuiSettings;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingClashesException;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingNotFoundException;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;
import seedu.rc4hdb.model.venues.exceptions.VenueNotFoundException;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Resident> PREDICATE_SHOW_ALL_RESIDENTS = unused -> true;

    //=================== User prefs methods ============================

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' data file path.
     */
    Path getUserPrefDataFilePath();

    /**
     * Sets the user prefs' data file path.
     */
    void setUserPrefDataFilePath(Path dataFilePath);

    //=================== Resident Book methods ============================

    /**
     * Replaces resident book data with the data in {@code residentBook}.
     */
    void setResidentBook(ReadOnlyResidentBook residentBook);

    /** Returns the ResidentBook */
    ReadOnlyResidentBook getResidentBook();

    /**
     * Returns true if a resident with the same identity as {@code resident} exists in the resident book.
     */
    boolean hasResident(Resident resident);

    /**
     * Deletes the given resident.
     * The resident must exist in the resident book.
     */
    void deleteResident(Resident target);

    /**
     * Adds the given resident.
     * {@code resident} must not already exist in the address book.
     */
    void addResident(Resident resident);

    /**
     * Replaces the given resident {@code target} with {@code editedResident}.
     * {@code target} must exist in the resident book.
     * The resident identity of {@code editedResident} must not be the same as another existing resident in the
     * resident book.
     */
    void setResident(Resident target, Resident editedResident);

    /** Returns an unmodifiable view of the filtered resident list */
    ObservableList<Resident> getFilteredResidentList();

    /**
     * Updates the filter of the filtered resident list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredResidentList(Predicate<Resident> predicate);

    //=================== Venue book methods ============================

    /**
     * Replaces venue book data with the data in {@code venueBook}.
     */
    void setVenueBook(ReadOnlyVenueBook venueBook);

    /** Returns the VenueBook */
    ReadOnlyVenueBook getVenueBook();

    /**
     * Returns true if a venue with the same identity as {@code venue} exists in the venue book.
     */
    boolean hasVenue(Venue venue);

    /**
     * Deletes the given venue.
     * The venue must exist in the venue book.
     */
    void deleteVenue(Venue target);

    /**
     * Adds the given venue.
     * {@code venue} must not already exist in the venue book.
     */
    void addVenue(Venue venue);

    /**
     * Adds a booking to the venue in the list with the name {@code venueName}.
     * @throws VenueNotFoundException if the venue does not exist in the list.
     */
    void addBooking(VenueName venueName, Booking booking) throws VenueNotFoundException, BookingClashesException;

    /**
     * Removes a booking corresponding to {@code bookedPeriod} and {@code bookedDay} from the venue in the list with
     * the name {@code venueName}.
     * @throws VenueNotFoundException if the venue does not exist in the list.
     * @throws BookingNotFoundException if the venue is not booked during the specified period and day.
     */
    void removeBooking(VenueName venueName, HourPeriod bookedPeriod, Day bookedDay)
            throws VenueNotFoundException, BookingNotFoundException;

    //=================== UI population methods ============================

    ObservableList<String> getObservableFields();

    void setObservableFields(List<String> modifiableList);

    ObservableList<Venue> getObservableVenues();

    void setObservableVenues(List<Venue> modifiableVenues);

    ObservableList<Booking> getObservableBookings();

    void setObservableBookings(List<Booking> modifiableBookings);
}
