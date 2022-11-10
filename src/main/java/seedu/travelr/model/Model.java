package seedu.travelr.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.travelr.commons.core.GuiSettings;
import seedu.travelr.model.event.AllInBucketListPredicate;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.ObservableTrip;
import seedu.travelr.model.trip.Trip;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Trip> PREDICATE_SHOW_ALL_TRIPS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

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
     * Returns the user prefs' Travelr file path.
     */
    Path getTravelrFilePath();

    /**
     * Sets the user prefs' Travelr file path.
     */
    void setTravelrFilePath(Path travelrFilePath);

    /**
     * Replaces Travelr data with the data in {@code addressBook}.
     */
    void setTravelr(ReadOnlyTravelr travelr);

    /**
     * Returns Travelr
     */
    ReadOnlyTravelr getTravelr();

    /**
     * Returns true if a Trip with the same identity as {@code trip} exists in Travelr.
     */
    boolean hasTrip(Trip trip);


    boolean tripHasEvent(Trip trip, Event event);

    /**
     * Returns true if the Event already exists in Travelr.
     */
    boolean hasEvent(Event event);


    /**
     * Returns true if the BucketList in Travelr has the specified Event.
     */
    boolean bucketlistHasEvent(Event event);

    /**
     * Deletes the given Trip.
     * The Trip must exist in Travelr.
     */
    void deleteTrip(Trip target);

    /**
     * Deletes the given Event.
     * The Event must exist in Travelr.
     */
    void deleteEvent(Event target);

    Event getEvent(Event event);

    Trip getTrip(Trip trip);

    /**
     * Adds the given Trip.
     * {@code trip} must not already exist in Travelr.
     */
    void addTrip(Trip trip);

    /**
     * Adds the given Event.
     * The Event must not already exist in Travelr.
     */
    void addEvent(Event event);

    /**
     * Returns the given Event to the BucketList.
     */
    void returnToBucketList(Event event);

    /**
     * Removes the given Event from the BucketList.
     */
    void removeFromBucketList(Event event);

    void updateSelectedTrip(Trip trip);

    /**
     * Returns an unmodifiable view of the filtered trip list.
     */
    ObservableList<Trip> getFilteredTripList();

    AllInBucketListPredicate getBucketPredicate();

    /**
     * Returns an unmodifiable view of the filtered events list.
     */
    ObservableList<Event> getFilteredEventList();

    /**
     * Returns an unmodifiable view of the BucketList.
     */
    ObservableList<Event> getBucketList();

    /**
     * Updates the filter of the filtered trip list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTripList(Predicate<Trip> predicate);

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    /**
     * Resets the filtered event list to buckets and filtered trips list to show all trips {@code predicate}.
     */
    void resetView();


    ObservableTrip getSelectedTrip();

    void resetSelectedTrip();

    void sortTripsByComparator(Comparator<Trip> comp);

    SummaryVariables getSummaryVariables();

    void refreshSummaryVariables();

    void sortEvents(Comparator<Event> comparator);

}
