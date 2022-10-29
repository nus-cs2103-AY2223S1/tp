package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.profile.EventsAttending;
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
     * Returns the user prefs' NUScheduler file path.
     */
    Path getNuSchedulerFilePath();

    /**
     * Sets the user prefs' NUScheduler file path.
     */
    void setNuSchedulerFilePath(Path nuSchedulerFilePath);

    /**
     * Replaces NUScheduler data with the data in {@code nuScheduler}.
     */
    void setNuScheduler(ReadOnlyNuScheduler nuScheduler);

    /** Returns the NuScheduler */
    ReadOnlyNuScheduler getNuScheduler();

    /**
     * Returns true if a profile with the same email as {@code profile} exists in the NUScheduler.
     */
    boolean hasEmail(Profile profile);

    /**
     * Returns true if a profile with the same phone as {@code profile} exists in the NUScheduler.
     */
    boolean hasPhone(Profile profile);

    /**
     * Returns true if a profile with the same telegram as {@code profile} exists in the NUScheduler.
     */
    boolean hasTelegram(Profile profile);

    /**
     * Deletes the given profile.
     * The profile must exist in the NUScheduler.
     */
    void deleteProfile(Profile target);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the NUScheduler.
     * The event identity of {@code editedEvent} must not be the same as another existing
     * event in the NUScheduler.
     * Ensures the change is updated for all event attendees.
     */
    void setEventForAttendees(Event target, Event editedEvent);

    /**
     * Adds the given profile.
     * {@code profile} must not already exist in the NUScheduler.
     */
    void addProfile(Profile profile);

    /**
     * Replaces the given profile {@code target} with {@code editedProfile}.
     * {@code target} must exist in the NUScheduler.
     * The profile identity of {@code editedProfile} must not be the same as another existing
     * profile in the NUScheduler.
     */
    void setProfile(Profile target, Profile editedProfile);

    /** Returns an unmodifiable view of the filtered profile list */
    ObservableList<Profile> getFilteredProfileList();

    /**
     * Updates the filter of the filtered profile list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProfileList(Predicate<Profile> predicate);

    /**
     * Returns true if an event with the same identity as {@code event} exists in the NUScheduler.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in the NUScheduler.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the NUScheduler.
     */
    void addEvent(Event event);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the NUScheduler.
     * The event identity of {@code editedEvent} must not be the same as another existing
     * event in the NUScheduler.
     */
    void setEvent(Event target, Event editedEvent);

    /**
     * Adds the given list of profiles {@code profilesToAdd} to the given event's list of attendees.
     * {@code event} must exist in the NUScheduler.
     * Profiles in {@code profilesToAdd} must also exist in the NUScheduler.
     */
    void addEventAttendees(Event event, List<Profile> profilesToAdd);

    /**
     * Deletes the given list of profiles {@code profilesToDelete} from the given event's list of attendees.
     * {@code event} must exist in the NUScheduler.
     * Profiles in {@code profilesToDelete} must also exist in the NUScheduler.
     */
    void deleteEventAttendees(Event event, List<Profile> profilesToDelete);

    /**
     * Adds the given event {@code event} to every profile in the given list of profiles {@code profilesToAddEventTo}.
     * {@code event} must exist in the NUScheduler.
     * Profiles in {@profilesToAddEventTo} must also exist in the NUScheduler.
     */
    void addEventToAttendees(Event event, List<Profile> profilesToAddEventTo);

    /**
     * Deletes the event {@code target} from list of profiles {@code profilesToEdit}.
     * {@code target} must exist in the NUScheduler.
     * Profiles in {@code profilesToEdit} must also exist in the NUScheduler.
     */
    void removeEventFromAttendees(Event target, List<Profile> profilesToEdit);

    /**
     * Replaces the events in {@code eventsToRefresh} with another copy
     * to refresh the UI.
     */
    void refreshEvents(EventsAttending eventsToRefresh);

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);
}
