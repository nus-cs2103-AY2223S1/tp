package seedu.travelr.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.list.BucketList;
import seedu.travelr.model.list.UniqueEventList;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.model.trip.UniqueTripList;

/**
 * Wraps all data at the Travelr level
 * Duplicates are not allowed (by .isSameTrip and isSameEvent comparison)
 */
public class Travelr implements ReadOnlyTravelr {

    private final BucketList bucketList;
    private final UniqueEventList allEventsList;
    private final UniqueTripList trips;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        trips = new UniqueTripList();
        bucketList = new BucketList();
        allEventsList = new UniqueEventList();
    }

    public Travelr() {}

    /**
     * Creates an Travelr using the Trips and Events in the {@code toBeCopied}
     */
    public Travelr(ReadOnlyTravelr toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Trips list with {@code trips}.
     * {@code trips} must not contain duplicate Trips.
     */

    public void setTrips(List<Trip> trips) {
        this.trips.setTrips(trips);
    }

    /**
     * Replaces the contents of the Events list with {@code events}.
     * {@code events} must not contain duplicate Events.
     */
    public void setEvents(List<Event> events) {
        this.bucketList.setEvents(events);
    }

    public void setAllEventsList(List<Event> events) {
        this.allEventsList.setEvents(events);
    }

    /**
     * Resets the existing data of this {@code Travelr} with {@code newData}.
     */
    public void resetData(ReadOnlyTravelr newData) {
        requireNonNull(newData);
        setAllEventsList(newData.getAllEventList());
        setTrips(newData.getTripList());
        setEvents(newData.getEventList());
    }

    //// person-level operations

    /**
     * Returns true if the specified Trip with the same identity as a {@code Trip} that currently exists in Travelr.
     */
    public boolean hasTrip(Trip trip) {
        requireNonNull(trip);
        return trips.contains(trip);
    }

    /**
     * Returns true if this Event already exists in Travelr.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return allEventsList.contains(event);
    }

    /**
     * Returns true if this Event exists in the BucketList.
     */
    public boolean bucketlistHasEvent(Event event) {
        requireNonNull(event);
        return bucketList.contains(event);
    }


    /**
     * Adds a Trip to Travelr
     * The Trip must not already exist in Travelr.
     */
    public void addTrip(Trip p) {
        trips.add(p);
    }

    /**
     * Adds an Event to Travelr.
     */
    public void addEventToBucketListAndAllEventsList(Event e) {
        bucketList.add(e);
        addEventToAllEventsList(e);
    }

    public void addEventToAllEventsList(Event e) {
        allEventsList.add(e);
    }

    /**
     * Adds an Event back into the BucketList after deleting it from a Trip.
     */
    public void returnToBucketList(Event e) {
        bucketList.add(e);
    }

    /**
     * Removes an Event from BucketList before adding it to Trip.
     */
    public void removeFromBucketList(Event e) {
        bucketList.remove(e);
    }

    /**
     * Removes {@code key} from {@code Travelr}.
     * {@code key} must exist in Travelr.
     */
    public void removeTrip(Trip key) {
        for (Event event : key.getEvents()) {
            bucketList.add(event);
        }
        trips.remove(key);
    }

    /**
     * Gets the desired Event
     * {@code key} must exist in the BucketList.
     */
    public Event getEvent(Event key) {

        return bucketList.getEvent(key);
    }

    /**
     * Gets the desired Trip
     * {@code key} must exist in the Trips.
     */
    public Trip getTrip(Trip key) {
        return trips.getTrip(key);
    }

    /**
     * Removes {@code key} from {@code Travelr}.
     * {@code key} must exist in Travelr.
     */
    public void removeEvent(Event key) {
        bucketList.remove(key);
        allEventsList.remove(key);
    }

    public void sortTrips(Comparator<Trip> comp) {
        trips.sort(comp);
    }

    //// util methods

    @Override
    public String toString() {
        return trips.asUnmodifiableObservableList().size() + " trips";
        // TODO: refine later
    }

    @Override
    public ObservableList<Trip> getTripList() {
        return trips.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return bucketList.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getAllEventList() {
        return allEventsList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Travelr // instanceof handles nulls
                && trips.equals(((Travelr) other).trips) && bucketList.equals(((Travelr) other).bucketList));
    }

    @Override
    public int hashCode() {
        return trips.hashCode();
    }

    /**
     * Sorts the Event list according to provided Comparator.
     * @param comp
     */
    public void sortEvents(Comparator<Event> comp) {
        bucketList.sort(comp);
        allEventsList.sort(comp);
    }
}
