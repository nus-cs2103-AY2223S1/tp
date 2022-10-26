package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void add(Profile profile) {
        if (!hasAttendee(profile)) {
            this.attendees.add(profile);
        }
    }

    public void remove(Profile profile) {
        this.attendees.remove(profile);
    }

    public boolean hasAttendee(Profile profile) {
        return this.attendees.contains(profile);
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
