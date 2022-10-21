package seedu.address.model.meeting;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.listing.Listing;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Meeting class is a meeting a client has at a listing on a specific dateTime.
 */
public class Meeting {

    /**
     * Date and Time of meeting.
     */
    private final LocalDateTime dateTime;
    /**
     * Client involved in the meeting.
     */
    private final Person client;
    /**
     * Listing involved in the meeting.
     */
    private final Listing listing;

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
    public Meeting(Person client, Listing listing, LocalDateTime dateTime) {
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
    public Meeting(Person client, Listing listing, LocalDateTime dateTime, Set<Tag> tags) {
        this.client = client;
        this.listing = listing;
        this.dateTime = dateTime;
        this.tags = tags;
    }

    /**
     * Getter for client.
     * @return Person
     */
    public Person getClient() {
        return client;
    }

    /**
     * Getter for listing.
     * @return Listing
     */
    public Listing getListing() {
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
     * String representation of meeting.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s is meeting at %s on %s", client.getName(), listing.getAddress(), dateTime);
    }
}
