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
     * Returns the AddressBook
     */
    ReadOnlyTravelr getTravelr();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the Travelr.
     */
    boolean hasTrip(Trip trip);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the Travelr.
     */
    boolean tripHasEvent(Trip trip, Event event);

    /**
     * Returns true if the event already exists in Travelr.
     */
    boolean hasEvent(Event event);


    /**
     * Returns true if the bucketlist has event in Travelr.
     */
    boolean bucketlistHasEvent(Event event);

    /**
     * Deletes the given trip.
     * The trip must exist in the Travelr.
     */
    void deleteTrip(Trip target);

    /**
     * Deletes the given event.
     * The event must exist in Travelr.
     */
    void deleteEvent(Event target);

    Event getEvent(Event event);

    Trip getTrip(Trip trip);

    /**
     * Adds the given person.
     * {@code person} must not already exist in Travelr.
     */
    void addTrip(Trip trip);

    /**
     * Adds the given event.
     * The event must not already exist in Travelr.
     */
    void addEvent(Event event);

    /**
     * Returns the given event to the bucket list.
     */
    void returnToBucketList(Event event);

    /**
     * Removes the given event from the bucket list.
     */
    void removeFromBucketList(Event event);

    void updateSelectedTrip(Trip trip);

    /**
     * Replaces the given trip {@code target} with {@code editedTrip}.
     * {@code target} must exist in the Travelr.
     * The trip identity of {@code editedTrip} must not be the same as another existing trip in Travelr.
     */
    void setTrip(Trip target, Trip editedTrip);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in Travelr.
     * The event must not already exist in Travelr.
     */
    void setEvent(Event target, Event editedEvent);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Trip> getFilteredTripList();

    AllInBucketListPredicate getBucketPredicate();

    /**
     * Returns an unmodifiable view of the filtered events list
     */
    ObservableList<Event> getFilteredEventList();

    /**
     * Returns an unmodifiable view of the bucket list
     */
    ObservableList<Event> getBucketList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
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
     * Resets the filtered event list to buckets and filtered trips to show all events {@code predicate}.
     */
    void resetView();


    ObservableTrip getSelectedTrip();

    void resetSelectedTrip();

    void sortTripsByComparator(Comparator<Trip> comp);

    SummaryVariables getSummaryVariables();

    void refreshSummaryVariables();

    void sortBucketList(Comparator<Event> comparator);

    boolean hasEventInBucketList(Event event);

}
