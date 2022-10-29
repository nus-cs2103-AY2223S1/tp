package seedu.address.model.profile;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.event.Event;
import seedu.address.model.tag.Tag;

/**
 * Represents a Profile in the NUScheduler.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Profile implements Comparable<Profile> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Telegram telegram;
    private final Set<Tag> tags = new HashSet<>();
    private final EventsAttending eventsToAttend;

    /**
     * Every field must be present and not null.
     */
    public Profile(Name name, Phone phone, Email email, Telegram telegram, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.tags.addAll(tags);
        eventsToAttend = new EventsAttending();
    }

    /**
     * Every field must be present and not null.
     */
    public Profile(Name name, Phone phone, Email email, Telegram telegram, Set<Tag> tags,
                   EventsAttending eventsToAttend) {
        requireAllNonNull(name, phone, email, tags, eventsToAttend);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.tags.addAll(tags);
        this.eventsToAttend = eventsToAttend;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public EventsAttending getEventsToAttend() {
        return eventsToAttend;
    }

    public List<Event> getEventsAttendingList() {
        return eventsToAttend.getEventsList();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    // eventsAttending operations

    /**
     * Adds the specified event to the profile's list of events to attend if it
     * has not already been added.
     */
    public void addAttendingEvent(Event eventToAdd) {
        requireNonNull(eventToAdd);
        eventsToAttend.addEvent(eventToAdd);
    }

    /**
     * Removes the specified event from the profile's list of events to attend if it exists in the list.
     */
    public void removeAttendingEvent(Event eventToRemove) {
        requireNonNull(eventToRemove);
        eventsToAttend.removeEvent(eventToRemove);
    }

    /**
     * Returns true if the specified event is in the profile's list of events to attend.
     */
    public boolean isAttendingEvent(Event event) {
        return eventsToAttend.hasEvent(event);
    }

    /**
     * Removes the profile from each event in its own list of events to attend {@code eventsToAttend}.
     */
    public void removeFromAttendingEvents() {
        eventsToAttend.removeAttendeeFromEvents(this);
    }

    // event operations

    /**
     * Removes each specified event in {@code eventsToRemove} from the profile's list of events to attend
     * if they exist in the list.
     */
    public void removeAttendingEvents(List<Event> eventsToRemove) {
        requireNonNull(eventsToRemove);
        eventsToRemove.forEach(this::removeAttendingEvent);
    }

    /**
     * Adds the profile to the list of attendees of each event in its own list of events to attend
     * if it has not already been added.
     */
    public void addToAllEvents() {
        eventsToAttend.addAttendeeToEvents(this);
    }

    /**
     * Adds the profile to the list of attendees of each event in {@code eventsToAdd} if it has not
     * already been added.
     */
    public void addToAllEvents(List<Event> eventsToAdd) {
        requireNonNull(eventsToAdd);
        eventsToAdd.forEach(event -> event.addAttendee(this));
    }

    // equality checks

    /**
     * Returns true if both profiles have the same email.
     */
    public boolean isSameEmail(Profile otherProfile) {
        if (otherProfile == this) {
            return true;
        }

        return otherProfile != null
                && otherProfile.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both profiles have the same phone.
     */
    public boolean isSamePhone(Profile otherProfile) {
        if (otherProfile == this) {
            return true;
        }

        return otherProfile != null
                && otherProfile.getPhone().equals(getPhone());
    }

    /**
     * Returns true if both profiles have the same telegram.
     */
    public boolean isSameTelegram(Profile otherProfile) {
        if (otherProfile == this) {
            return true;
        }

        return otherProfile != null
                && otherProfile.getTelegram().equals(getTelegram());
    }

    /**
     * Returns true if both profiles have the same telegram and field is not empty.
     */
    public boolean isSameTelegramNotEmpty(Profile otherProfile) {
        return isSameTelegram(otherProfile) && !this.getTelegram().isEmpty();
    }

    /**
     * Returns true if both profiles have the same identity and data fields.
     * This defines a stronger notion of equality between two profiles.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Profile)) {
            return false;
        }

        Profile otherProfile = (Profile) other;
        return otherProfile.getName().equals(getName())
                && isSamePhone(otherProfile)
                && isSameEmail(otherProfile)
                && isSameTelegram(otherProfile)
                && otherProfile.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, tags);
    }

    @Override
    public int compareTo(Profile other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail());

        if (!getTelegram().isEmpty()) {
            builder.append("; Telegram: ")
                    .append(getTelegram());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
