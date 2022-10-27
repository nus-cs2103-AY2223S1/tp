package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.model.profile.Profile;

/**
 * Represents an Event's attendees in NUScheduler.
 */
public class Attendees {
    private final List<Profile> attendees;

    /**
     * Constructs an empty {@code Attendees}.
     */
    public Attendees() {
        this.attendees = new ArrayList<>();
    }

    /**
     * Constructs an {@code Attendees} with the given list.
     *
     * @param attendees A list of attendees.
     */
    public Attendees(List<Profile> attendees) {
        requireNonNull(attendees);
        this.attendees = new ArrayList<>();
        this.attendees.addAll(attendees);
    }

    /**
     * Returns an immutable profile set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Profile> getAttendeesList() {
        Collections.sort(attendees);
        return Collections.unmodifiableList(attendees);
    }

    /**
     * Adds the given profile if it has not already been added.
     */
    public void addProfile(Profile profile) {
        if (!hasAttendee(profile)) {
            this.attendees.add(profile);
        }
    }

    /**
     * Adds the list of profiles in {@code profilesToAdd} if they have not already been added.
     */
    public void addProfiles(List<Profile> profilesToAdd) {
        profilesToAdd.forEach(this::addProfile);
    }

    /**
     * Removes the given profile if it exists.
     */
    public void removeAttendee(Profile attendeeToRemove) {
        if (hasAttendee(attendeeToRemove)) {
            this.attendees.remove(attendeeToRemove);
        }
    }

    /**
     * Removes each profile in {@code attendeesToRemove} if they exist.
     */
    public void removeAttendees(List<Profile> attendeesToRemove) {
        attendeesToRemove.forEach(this::removeAttendee);
    }

    /**
     * Returns true if the given profile is in the list of profiles.
     */
    public boolean hasAttendee(Profile profile) {
        requireNonNull(profile);
        return this.attendees.contains(profile);
    }

    /**
     * Adds the given event to each profile in the list of profiles.
     */
    public void addEventToAttendees(Event eventToAdd) {
        requireNonNull(eventToAdd);
        attendees.forEach(attendee -> attendee.addAttendingEvent(eventToAdd));
    }

    /**
     * Removes the given event from each profile in the list of profiles.
     */
    public void removeEventFromAttendees(Event eventToRemove) {
        requireNonNull(eventToRemove);
        attendees.forEach(attendee -> attendee.removeAttendingEvent(eventToRemove));
    }

    public int size() {
        return this.attendees.size();
    }

    public Profile getAttendee(int index) {
        return this.attendees.get(index);
    }

    public boolean isEmpty() {
        return this.attendees.isEmpty();
    }

    public Stream<Profile> stream() {
        return attendees.stream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Attendees)) {
            return false;
        }

        Attendees other = (Attendees) o;
        return this.attendees.equals(other.attendees);
    }

    @Override
    public int hashCode() {
        return attendees.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Attendees: ");
        List<Profile> sortedAttendees = this.getAttendeesList();

        for (int i = 0; i < sortedAttendees.size() - 1; i++) {
            Profile attendee = sortedAttendees.get(i);
            builder.append(attendee.getName() + ", ");
        }
        Profile lastAttendee = sortedAttendees.get(sortedAttendees.size() - 1);
        builder.append(lastAttendee.getName());

        return builder.toString();
    }
}
