package seedu.address.model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Profile> PREDICATE_SHOW_ALL_PROFILES = unused -> true;

    /** {@code Predicate} that always evaluate to true */
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

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a profile with the same identity as {@code profile} exists in the address book.
     */
    boolean hasProfile(Profile profile);

    /**
     * Deletes the given profile.
     * The profile must exist in the address book.
     */
    void deleteProfile(Profile target);

    /**
     * Adds the given profile.
     * {@code profile} must not already exist in the address book.
     */
    void addProfile(Profile profile);

    /**
     * Replaces the given profile {@code target} with {@code editedProfile}.
     * {@code target} must exist in the address book.
     * The profile identity of {@code editedProfile} must not be the same as another existing
     * profile in the address book.
     */
    void setProfile(Profile target, Profile editedProfile);

    /**
     * Adds the given list of events {@code eventsToAdd} to the given profile's list of eventsAttending.
     * {@code profile} must exist in the address book .
     * Events in {@eventsToAdd} must also exist in the address book.
     */
    void addEventsAttending(Profile profile, List<Event> eventsToAdd);

    /** Returns an unmodifiable view of the filtered profile list */
    ObservableList<Profile> getFilteredProfileList();

    /**
     * Updates the filter of the filtered profile list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProfileList(Predicate<Profile> predicate);

    /**
     * Returns true if an event with the same identity as {@code event} exists in the address book.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in the address book.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the address book.
     */
    void addEvent(Event event);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the address book.
     * The event identity of {@code editedEvent} must not be the same as another existing
     * event in the address book.
     */
    void setEvent(Event target, Event editedEvent);

    /**
     * Adds the given list of profiles {@code profilesToAdd} to the given event's list of attendees.
     * {@code event} must exist in the address book.
     * Profiles in {@profilesToAdd} must also exist in the address book.
     */
    void addEventAttendees(Event event, List<Profile> profilesToAdd);

    /**
     * Adds the given event {@code event} to every profile in the given list of profiles {@code profilesToAddEventTo}.
     * {@code event} must exist in the address book.
     * Profiles in {@profilesToAddEventTo} must also exist in the address book.
     */
    void addEventToAttendees(Event event, List<Profile> profilesToAddEventTo);

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);
}
