package seedu.rc4hdb.model;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.rc4hdb.commons.core.GuiSettings;
import seedu.rc4hdb.commons.core.LogsCenter;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.exceptions.DuplicateResidentException;
import seedu.rc4hdb.model.resident.fields.ResidentField;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingClashesException;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingNotFoundException;
import seedu.rc4hdb.model.venues.exceptions.DuplicateVenueException;
import seedu.rc4hdb.model.venues.exceptions.VenueNotFoundException;
import seedu.rc4hdb.ui.ObservableItem;

/**
 * Represents the in-memory model of the resident book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ResidentBook residentBook;
    private final VenueBook venueBook;
    private final UserPrefs userPrefs;

    /* A filterable list of residents to be displayed on the ResidentTableView */
    private final FilteredList<Resident> filteredResidents;

    /* A venue whose bookings are to be displayed in the BookingTableView */
    private final ObservableItem<Venue> currentlyDisplayedVenue;

    /* A list of strings that corresponds to the fields of Resident to be displayed on the ResidentTableView */
    private final ObservableList<String> visibleFields = FXCollections
            .observableArrayList(ResidentField.LOWERCASE_FIELDS);

    /* A list of strings that corresponds to the fields of Resident to be hidden on the ResidentTableView */
    private final ObservableList<String> hiddenFields = FXCollections.observableArrayList();

    private final ObservableList<String> unmodifiableVisibleFields = FXCollections
            .unmodifiableObservableList(visibleFields);

    private final ObservableList<String> unmodifiableHiddenFields = FXCollections
            .unmodifiableObservableList(hiddenFields);

    /**
     * Initializes a ModelManager with the given residentBook and userPrefs.
     */
    public ModelManager(ReadOnlyResidentBook residentBook, ReadOnlyVenueBook venueBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(residentBook, venueBook, userPrefs);

        logger.fine("Initializing with resident book: " + residentBook + ", user prefs " + userPrefs
                + ", venue book: " + venueBook);

        // Initialising ModelManager with resident, venue and user prefs data
        this.residentBook = new ResidentBook(residentBook);
        this.venueBook = new VenueBook(venueBook);
        this.userPrefs = new UserPrefs(userPrefs);

        // Set up observable instances
        filteredResidents = new FilteredList<>(this.residentBook.getResidentList());

        List<Venue> venueList = this.venueBook.getVenueList();
        if (venueList.isEmpty()) {
            logger.info("No venues found in venue list.");
            currentlyDisplayedVenue = new ObservableItem<>(null);
        } else {
            // Set first venue in list to be currently displayed
            currentlyDisplayedVenue = new ObservableItem<>(venueList.get(0));
        }
    }

    public ModelManager() {
        this(new ResidentBook(), new VenueBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getUserPrefDataFilePath() {
        return userPrefs.getDataStorageFilePath();
    }

    @Override
    public void setUserPrefDataFilePath(Path residentBookFilePath) {
        requireNonNull(residentBookFilePath);
        userPrefs.setDataStorageFilePath(residentBookFilePath);
    }

    //=========== ResidentBook ===============================================================================

    public void setResidentBook(ReadOnlyResidentBook residentBook) {
        this.residentBook.resetData(residentBook);
    }

    @Override
    public ReadOnlyResidentBook getResidentBook() {
        return residentBook;
    }

    @Override
    public boolean hasResident(Resident person) {
        requireNonNull(person);
        return residentBook.hasResident(person);
    }

    @Override
    public void deleteResident(Resident target) {
        residentBook.removeResident(target);
    }

    @Override
    public void addResident(Resident person) throws DuplicateResidentException {
        residentBook.addResident(person);
        updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);
    }

    @Override
    public void setResident(Resident target, Resident editedResident) {
        requireAllNonNull(target, editedResident);
        residentBook.setResident(target, editedResident);
    }

    //=========== Filtered Resident List Accessors ===========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Resident} backed by the internal list of
     * {@code versionedResidentBook}
     */
    @Override
    public ObservableList<Resident> getFilteredResidentList() {
        return filteredResidents;
    }

    @Override
    public void updateFilteredResidentList(Predicate<Resident> predicate) {
        requireNonNull(predicate);
        filteredResidents.setPredicate(predicate);
    }

    //=========== Venue Book ==================================================================================

    @Override
    public void setVenueBook(ReadOnlyVenueBook venueBook) {
        this.venueBook.resetData(venueBook);
        List<Venue> newVenues = this.venueBook.getVenueList();
        if (newVenues.isEmpty()) {
            currentlyDisplayedVenue.setValue(null);
        } else {
            Venue firstVenueInList = newVenues.get(0);
            currentlyDisplayedVenue.setValue(firstVenueInList);
        }
    }

    @Override
    public ReadOnlyVenueBook getVenueBook() {
        return venueBook;
    }

    @Override
    public boolean hasVenue(Venue venue) {
        requireNonNull(venue);
        return venueBook.hasVenue(venue);
    }

    @Override
    public void deleteVenue(VenueName venueName) {
        requireNonNull(venueName);
        venueBook.removeVenue(venueName);
    }

    @Override
    public void addVenue(Venue venue) throws DuplicateVenueException {
        requireNonNull(venue);
        venueBook.addVenue(venue);
    }

    @Override
    public void addBooking(VenueName venueName, Booking booking)
            throws VenueNotFoundException, BookingClashesException {
        requireAllNonNull(venueName, booking);
        venueBook.addBooking(venueName, booking);
    }

    @Override
    public void removeBooking(BookingDescriptor bookingDescriptor)
            throws VenueNotFoundException, BookingNotFoundException {
        requireNonNull(bookingDescriptor);
        venueBook.removeBooking(bookingDescriptor);
    }

    //=========== End of venue book methods =============================================

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return residentBook.equals(other.residentBook)
                && venueBook.equals(other.venueBook)
                && userPrefs.equals(other.userPrefs)
                && filteredResidents.equals(other.filteredResidents);
    }

    //=========== Observable Field List Accessors =============================================================

    @Override
    public ObservableList<String> getUnmodifiableVisibleFields() {
        return unmodifiableVisibleFields;
    }

    @Override
    public void setVisibleFields(List<String> fieldsToShow) {
        visibleFields.setAll(fieldsToShow);
    }

    @Override
    public ObservableList<String> getUnmodifiableHiddenFields() {
        return unmodifiableHiddenFields;
    }

    @Override
    public void setHiddenFields(List<String> fieldsToHide) {
        hiddenFields.setAll(fieldsToHide);
    }

    //=========== Observable Venue List Accessors =============================================================

    @Override
    public ObservableList<Venue> getObservableVenues() {
        return venueBook.getVenueList();
    }

    //=========== Currently Displayed Venue Accessors =========================================================

    @Override
    public ObservableItem<Venue> getCurrentlyDisplayedVenue() {
        return currentlyDisplayedVenue;
    }

    @Override
    public void setCurrentlyDisplayedVenue(VenueName venueName) {
        currentlyDisplayedVenue.setValue(venueBook.getVenueWithName(venueName));
    }

}
