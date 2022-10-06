package seedu.travelr.model;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.travelr.commons.core.GuiSettings;
import seedu.travelr.commons.core.LogsCenter;
import seedu.travelr.model.trip.Trip;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Trip> filteredTrips;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTrips = new FilteredList<>(this.addressBook.getTripList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasTrip(Trip trip) {
        requireNonNull(trip);
        return addressBook.hasTrip(trip);
    }

    @Override
    public void deleteTrip(Trip target) {
        addressBook.removeTrip(target);
    }

    @Override
    public void addTrip(Trip trip) {
        addressBook.addTrip(trip);
        updateFilteredTripList(PREDICATE_SHOW_ALL_TRIPS);
    }

    @Override
    public void setTrip(Trip target, Trip editedTrip) {
        requireAllNonNull(target, editedTrip);

        addressBook.setTrip(target, editedTrip);
    }

    //=========== Filtered Trip List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Trip} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Trip> getFilteredTripList() {
        return filteredTrips;
    }

    @Override
    public void updateFilteredTripList(Predicate<Trip> predicate) {
        requireNonNull(predicate);
        filteredTrips.setPredicate(predicate);
    }

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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredTrips.equals(other.filteredTrips);
    }

}
