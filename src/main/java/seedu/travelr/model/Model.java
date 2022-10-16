package seedu.travelr.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.travelr.commons.core.GuiSettings;
import seedu.travelr.model.event.AllInBucketListPredicate;
import seedu.travelr.model.event.Event;
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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasTrip(Trip trip);

    /**
     * Returns true if the event already exists in Travelr.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
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
     * {@code person} must not already exist in the address book.
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
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
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
}
