package seedu.travelr.model;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.travelr.model.trip.TripComparators.COMPARE_BY_COMPLETION;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.travelr.commons.core.GuiSettings;
import seedu.travelr.commons.core.LogsCenter;
import seedu.travelr.model.event.AllInBucketListPredicate;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.ObservableTrip;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.model.trip.TripCompletedPredicate;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Travelr travelr;
    private final UserPrefs userPrefs;
    private final FilteredList<Trip> filteredTrips;
    private final ObservableTrip selectedTrip;
    private final FilteredList<Event> filteredEvents;
    private final FilteredList<Event> bucketList;

    /**
     * For use by Summary Command only.
     */
    private final SummaryVariables summaryVariables;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyTravelr travelr, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(travelr, userPrefs);

        logger.fine("Initializing with Travelr: " + travelr + " and user prefs " + userPrefs);

        this.travelr = new Travelr(travelr);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTrips = new FilteredList<>(this.travelr.getTripList());
        filteredEvents = new FilteredList<>(this.travelr.getAllEventList());
        bucketList = new FilteredList<>(this.travelr.getEventList());

        // Initialize the selected trip
        selectedTrip = new ObservableTrip();

        // Initialize the summary variables
        summaryVariables = new SummaryVariables();

        // Set initial view to Bucket List
        filteredEvents.setPredicate(getBucketPredicate());
    }

    public ModelManager() {
        this(new Travelr(), new UserPrefs());
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
    public Path getTravelrFilePath() {
        return userPrefs.getTravelrFilePath();
    }

    @Override
    public void setTravelrFilePath(Path travelrFilePath) {
        requireNonNull(travelrFilePath);
        userPrefs.setTravelrFilePath(travelrFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setTravelr(ReadOnlyTravelr travelr) {
        this.travelr.resetData(travelr);
    }

    @Override
    public ReadOnlyTravelr getTravelr() {
        return travelr;
    }

    @Override
    public boolean hasTrip(Trip trip) {
        requireNonNull(trip);
        return travelr.hasTrip(trip);
    }

    @Override
    public boolean tripHasEvent(Trip trip, Event event) {
        requireNonNull(event);
        requireNonNull(trip);
        return trip.containsEvent(event);
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return travelr.hasEvent(event);
    }

    @Override
    public boolean bucketlistHasEvent(Event event) {
        requireNonNull(event);
        return travelr.bucketlistHasEvent(event);
    }

    @Override
    public void deleteTrip(Trip target) {
        travelr.removeTrip(target);
        if (selectedTrip.isEqual(target)) {
            resetSelectedTrip();
        }
    }

    @Override
    public void deleteEvent(Event e) {
        travelr.removeEvent(e);
    }

    @Override
    public void addTrip(Trip trip) {
        travelr.addTrip(trip);
    }

    /**
     * This is when we create a new event
     *
     * @param event
     */
    @Override
    public void addEvent(Event event) {
        travelr.addEventToBucketListAndAllEventsList(event);
    }

    @Override
    public void returnToBucketList(Event event) {
        travelr.returnToBucketList(event);
    }

    @Override
    public void removeFromBucketList(Event event) {
        travelr.removeFromBucketList(event);
    }

    public Event getEvent(Event event) {
        return travelr.getEvent(event);
    }

    public Trip getTrip(Trip trip) {
        return travelr.getTrip(trip);
    }

    //=========== Summary Variables Accessors =============================================================

    @Override
    public void refreshSummaryVariables() {
        updateFilteredTripList(Model.PREDICATE_SHOW_ALL_TRIPS);
        updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        summaryVariables.refresh(filteredTrips, filteredEvents);

        updateFilteredTripList(new TripCompletedPredicate());

        // resets to AllTrips and bucketList
        updateFilteredTripList(Model.PREDICATE_SHOW_ALL_TRIPS);
        updateFilteredEventList(getBucketPredicate());
        resetSelectedTrip();
    }

    @Override
    public SummaryVariables getSummaryVariables() {
        return summaryVariables;
    }

    //=========== Selected Trip Accessors =============================================================

    @Override
    public ObservableTrip getSelectedTrip() {
        return selectedTrip;
    }

    @Override
    public void updateSelectedTrip(Trip trip) {
        selectedTrip.setTrip(trip);
    }

    @Override
    public void resetSelectedTrip() {
        selectedTrip.resetTrip();
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
    public AllInBucketListPredicate getBucketPredicate() {
        return new AllInBucketListPredicate(bucketList);
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredTripList(Predicate<Trip> predicate) {
        requireNonNull(predicate);
        filteredTrips.setPredicate(predicate);
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    @Override
    public void resetView() {
        updateFilteredEventList(getBucketPredicate());
        updateFilteredTripList(PREDICATE_SHOW_ALL_TRIPS);
        resetSelectedTrip();
        sortTripsByComparator(COMPARE_BY_COMPLETION);
    }


    @Override
    public ObservableList<Event> getBucketList() {
        return bucketList;
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
        return travelr.equals(other.travelr)
                && userPrefs.equals(other.userPrefs)
                && filteredTrips.equals(other.filteredTrips);
    }

    @Override
    public void sortTripsByComparator(Comparator<Trip> comp) {
        travelr.sortTrips(comp);
    }

    @Override
    public void sortEvents(Comparator<Event> comp) {
        travelr.sortEvents(comp);
    }

    @Override
    public boolean hasEventInBucketList(Event event) {
        return bucketList.contains(event);
    }

}
