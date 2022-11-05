package seedu.realtime.model.meeting;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.person.Name;
import seedu.realtime.model.tag.Tag;

/**
 * Meeting class is a meeting a client has at a listing on a specific dateTime.
 */
public class Meeting implements Comparable<Meeting> {

    /**
     * Date and Time of meeting.
     */
    private final LocalDateTime dateTime;
    /**
     * Client involved in the meeting.
     */
    private final Name client;
    /**
     * Listing involved in the meeting.
     */
    private final ListingId listing;

    /**
     * Tag for type of Meeting.
     */
    private Set<Tag> tags = new HashSet<>();

    /**
     * Constructor for meeting.
     * @param client Person
     * @param listing Listing
     * @param dateTime dateTime
     */
    public Meeting(Name client, ListingId listing, LocalDateTime dateTime) {
        this.client = client;
        this.listing = listing;
        this.dateTime = dateTime;
    }

    /**
     * Constructor for meeting with tags.
     * @param client Person
     * @param listing Listing
     * @param dateTime dateTime
     */
    public Meeting(Name client, ListingId listing, LocalDateTime dateTime, Set<Tag> tags) {
        this.client = client;
        this.listing = listing;
        this.dateTime = dateTime;
        this.tags = tags;
    }

    /**
     * Getter for client.
     * @return Person
     */
    public Name getClient() {
        return client;
    }

    /**
     * Getter for listing.
     * @return Listing
     */
    public ListingId getListing() {
        return listing;
    }

    /**
     * Getter for date time.
     * @return LocalDatetime
     */
    public LocalDateTime getdateTime() {
        return dateTime;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both Meeting have the same identity and data fields.
     * This defines a stronger notion of equality between two meetings.
     */
    public boolean isSameMeeting(Meeting other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }
        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getClient().equals(getClient())
                && otherMeeting.getListing().equals(getListing())
                && otherMeeting.getdateTime().equals(getdateTime());
    }

    /**
     * Compares this meeting to another meeting
     */
    @Override
    public int compareTo(Meeting o) {
        if (this.dateTime.isAfter(o.dateTime)) {
            return 1;
        } else if (this.dateTime.isBefore(o.dateTime)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * String representation of meeting.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s is meeting at %s on %s", getClient(), getListing(), dateTime);
    }

}
