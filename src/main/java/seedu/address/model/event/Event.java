package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.profile.Profile;
import seedu.address.model.tag.Tag;

/**
 * Represents an Event in the NUScheduler.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event implements Comparable<Event> {

    // Identity fields
    private final Title title;
    private final DateTime startDateTime;
    private final DateTime endDateTime;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final Attendees attendees;

    /**
     * Every field must be present and not null.
     */
    public Event(Title title, DateTime startDateTime, DateTime endDateTime, Set<Tag> tags) {
        requireAllNonNull(title, startDateTime, endDateTime, tags);
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.tags.addAll(tags);
        attendees = new Attendees();
    }

    /**
     * Every field must be present and not null.
     */
    public Event(Title title, DateTime startDateTime, DateTime endDateTime, Set<Tag> tags, Attendees attendees) {
        requireAllNonNull(title, startDateTime, endDateTime, tags, attendees);
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.tags.addAll(tags);
        this.attendees = attendees;
    }

    public Title getTitle() {
        return title;
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public DateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Attendees getAttendees() {
        return attendees;
    }

    public List<Profile> getAttendeesList() {
        return attendees.getAttendeesList();
    }

    // attendee operations

    /**
     * Adds the specified profile to the event's list of attendees if it has not already been added.
     */
    public void addAttendee(Profile profileToAdd) {
        requireNonNull(profileToAdd);
        attendees.addProfile(profileToAdd);
    }

    /**
     * Adds the specified profiles in {@code profilesToAdd} to the event's list of attendees if
     * they have not already been added.
     */
    public void addAttendees(List<Profile> profilesToAdd) {
        requireNonNull(profilesToAdd);
        attendees.addProfiles(profilesToAdd);
    }

    /**
     * Removes the specified profile from the event's list of attendees if exists in the list.
     */
    public void removeAttendee(Profile profileToRemove) {
        requireNonNull(profileToRemove);
        attendees.removeAttendee(profileToRemove);
    }

    /**
     * Removes each specified profile in {@code profilesToRemove} from the event's list of attendees if
     * they exist in the list.
     */
    public void removeAttendees(List<Profile> attendeesToRemove) {
        requireNonNull(attendeesToRemove);
        attendees.removeAttendees(attendeesToRemove);
    }

    public int numberOfAttendees() {
        return attendees.size();
    }

    public Profile getAttendee(int index) {
        return attendees.getAttendee(index);
    }

    /**
     * Returns true if the specified profile is in the event's list of attendees.
     */
    public boolean hasAttendee(Profile profile) {
        return attendees.hasAttendee(profile);
    }

    /**
     * Removes the event from each attendee in its own list of attendees {@code attendees}.
     */
    public void removeFromAttendees() {
        attendees.removeEventFromAttendees(this);
    }

    // profile operations

    /**
     * Removes the event from the list of eventsAttending of each attendee in {@code attendeesToRemoveFrom}
     * if it exists in the list.
     */
    public void removeFromAttendees(List<Profile> attendeesToRemoveFrom) {
        requireNonNull(attendeesToRemoveFrom);
        attendeesToRemoveFrom.forEach(attendee -> attendee.removeAttendingEvent(this));
    }

    /**
     * Adds the event to the list of eventsAttending of each profile in its own list of attendees
     * if it has not already been added.
     */
    public void addToAllAttendees() {
        attendees.addEventToAttendees(this);
    }

    /**
     * Adds the event to the list of eventsAttending of each profile in {@code profilesToAddEventTo} if
     * it has not already been added.
     */
    public void addToAllAttendees(Attendees profilesToAddEventTo) {
        requireNonNull(profilesToAddEventTo);
        profilesToAddEventTo.addEventToAttendees(this);
    }

    /**
     * Adds the event to the list of eventsAttending of each profile in {@code profilesToAddEventTo} if
     * it has not already been added.
     */
    public void addToAllAttendees(List<Profile> profilesToAddEventTo) {
        requireNonNull(profilesToAddEventTo);
        profilesToAddEventTo.forEach(profile -> profile.addAttendingEvent(this));
    }

    // equality checks

    /**
     * Returns true if both events have the same title, start, and end times.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getTitle().equals(getTitle())
                && otherEvent.getStartDateTime().equals(getStartDateTime())
                && otherEvent.getEndDateTime().equals(getEndDateTime());
    }

    /**
     * Returns true if start date is before or equal the end date.
     */
    public boolean isValidStartEnd() {
        return startDateTime.isBeforeOrEqual(endDateTime);
    }

    /**
     * Returns true if start date and end dates either both have time
     * or both do not have time.
     */
    public boolean isHasTimeEqual() {
        return startDateTime.hasTime() == endDateTime.hasTime();
    }

    /**
     * Returns true if both event have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return isSameEvent(otherEvent)
                && otherEvent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, startDateTime, endDateTime, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Title: ")
                .append(getTitle())
                .append("; Start: ")
                .append(getStartDateTime())
                .append("; End: ")
                .append(getEndDateTime());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (!attendees.isEmpty()) {
            builder.append(System.lineSeparator())
                    .append(attendees);
        }

        return builder.toString();
    }

    @Override
    public int compareTo(Event other) {
        int compareValue = this.getStartDateTime().compareTo(other.getStartDateTime());
        if (compareValue == 0) {
            compareValue = this.getEndDateTime().compareTo(other.getEndDateTime());
        }
        if (compareValue == 0) {
            compareValue = this.getTitle().compareTo(other.getTitle());
        }
        return compareValue;
    }
}
