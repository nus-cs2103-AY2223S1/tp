package seedu.address.model;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.profile.EventsAttending;
import seedu.address.model.profile.Profile;
import seedu.address.model.profile.UniqueProfileList;

/**
 * Wraps all data at the NUScheduler level
 * Similar Profiles are not allowed (by .isSameEmail, .isSamePhone and .isSameTelegramNotEmpty comparison)
 */
public class NuScheduler implements ReadOnlyNuScheduler {

    private final UniqueProfileList profiles;
    private final UniqueEventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        profiles = new UniqueProfileList();
        events = new UniqueEventList();
    }

    public NuScheduler() {}

    /**
     * Creates a NUScheduler using the Profiles in the {@code toBeCopied}
     */
    public NuScheduler(ReadOnlyNuScheduler toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the profile list with {@code profiles}.
     * {@code profiles} must not contain similar profiles.
     */
    public void setProfiles(List<Profile> profiles) {
        this.profiles.setProfiles(profiles);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Resets the existing data of this {@code NuScheduler} with {@code newData}.
     */
    public void resetData(ReadOnlyNuScheduler newData) {
        requireNonNull(newData);
        setProfiles(newData.getProfileList());
        setEvents(newData.getEventList());
    }

    //// profile-level operations

    /**
     * Returns true if a profile with the same email as {@code profile} exists in the NUScheduler.
     */
    public boolean hasEmail(Profile profile) {
        requireNonNull(profile);
        return profiles.containsEmail(profile);
    }

    /**
     * Returns true if a profile with the same phone as {@code profile} exists in the NUScheduler.
     */
    public boolean hasPhone(Profile profile) {
        requireNonNull(profile);
        return profiles.containsPhone(profile);
    }

    /**
     * Returns true if a profile with the same telegram as {@code profile} exists in the NUScheduler.
     */
    public boolean hasTelegram(Profile profile) {
        requireNonNull(profile);
        return profiles.containsTelegram(profile);
    }

    /**
     * Adds a profile to the NUScheduler.
     * The profile must not already exist in the NUScheduler.
     */
    public void addProfile(Profile p) {
        profiles.add(p);
    }

    /**
     * Replaces the given profile {@code target} in the list with {@code editedProfile}.
     * {@code target} must exist in the NUScheduler.
     * The profile identity of {@code editedProfile} must not be the same as an existing profile in the NUScheduler.
     */
    public void setProfile(Profile target, Profile editedProfile) {
        requireNonNull(editedProfile);
        profiles.setProfile(target, editedProfile);
    }

    /**
     * Removes {@code key} from this {@code NuScheduler}.
     * {@code key} must exist in the NUScheduler.
     */
    public void removeProfile(Profile key) {
        profiles.remove(key);
    }

    /**
     * Returns true if a profile with the same identity as {@code profile} exists in the NUScheduler.
     */
    public boolean hasProfile(Profile profile) {
        requireNonNull(profile);
        return profiles.contains(profile);
    }

    //// event-level operations

    /**
     * Returns true if an event with the same identity as {@code event} exists in the NUScheduler.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds an event to the NUScheduler.
     * The event must not already exist in the NUScheduler.
     */
    public void addEvent(Event p) {
        events.add(p);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the NUScheduler.
     * The event identity of {@code editedEvent} must not be the same as an existing event in the NUScheduler.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);
        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} from this {@code NuScheduler}.
     * {@code key} must exist in the NUScheduler.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    /**
     * Adds profiles in {@code profilesToAdd} to the given event.
     * {@code event} must exist in the NUScheduler.
     * Profiles in {@code profilesToAdd} must also exist in the NUScheduler.
     */
    public void addEventAttendees(Event event, List<Profile> profilesToAdd) {
        requireAllNonNull(event, profilesToAdd);
        events.addEventAttendees(event, profilesToAdd);
    }

    /**
     * Deletes profiles in {@code profilesToDelete} from the given event.
     * {@code event} must exist in the NUScheduler.
     * Profiles in {@code profilesToDelete} must also exist in the NUScheduler.
     */
    public void deleteEventAttendees(Event event, List<Profile> profilesToDelete) {
        requireAllNonNull(event, profilesToDelete);
        events.deleteEventAttendees(event, profilesToDelete);
    }

    /**
     * Adds event {@code event} to the profiles in list of profiles {@code profilesToAddEventTo}.
     * {@code event} must exist in the NUScheduler.
     * Profiles in {@code profilesToAddEventTo} must also exist in the NUScheduler.
     */
    public void addEventToAttendees(Event event, List<Profile> profilesToAddEventTo) {
        requireAllNonNull(event, profilesToAddEventTo);
        events.addEventToAttendees(event, profilesToAddEventTo);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the NUScheduler.
     * The event identity of {@code editedEvent} must not be the same as an existing event in the NUScheduler.
     * Ensures the change is updated for all event attendees.
     */
    public void setEventForAttendees(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        events.setEventForAttendees(target, editedEvent);
    }

    /**
     * Deletes the event {@code target} from given list of profiles {@code profilesToEdit}.
     * {@code target} must exist in the NUScheduler.
     * Profiles in {@code profilesToEdit} must also exist in the NUScheduler.
     */
    public void removeEventFromAttendees(Event target, List<Profile> profilesToEdit) {
        requireAllNonNull(target, profilesToEdit);
        events.removeEventFromAttendees(target, profilesToEdit);
    }

    /**
     * Replaces each event in {@code eventsToRefresh} with another copy.
     * Events in {@code eventsToRefresh} must exist in the address book.
     */
    public void refreshEvents(EventsAttending eventsToRefresh) {
        requireNonNull(eventsToRefresh);
        events.refreshEvents(eventsToRefresh);
    }

    //// util methods

    @Override
    public String toString() {
        return profiles.asUnmodifiableObservableList().size() + " profiles "
                + "and "
                + events.asUnmodifiableObservableList().size() + " events";
        // TODO: refine later
    }

    @Override
    public ObservableList<Profile> getProfileList() {
        return profiles.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NuScheduler // instanceof handles nulls
                && profiles.equals(((NuScheduler) other).profiles)
                && events.equals(((NuScheduler) other).events));
    }

    @Override
    public int hashCode() {
        return hash(profiles, events);
    }
}
