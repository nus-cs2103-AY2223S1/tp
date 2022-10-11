package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

import seedu.address.model.listing.Listing;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.offer.Offer;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final Name name;

    // Data fields
    private final ArrayList<Meeting> meetingList;
    private final ArrayList<Offer> offerList;
    private final ArrayList<Listing> listingList;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, ArrayList<Meeting> meetingList, ArrayList<Offer> offerList,
                  ArrayList<Listing> listingList, Remark remark, Set<Tag> tags) {
        requireAllNonNull(name, meetingList, offerList, listingList, tags);
        this.name = name;
        this.meetingList = meetingList;
        this.offerList = offerList;
        this.listingList = listingList;
        this.remark = remark;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public ArrayList<Meeting> getMeetingList() {
        return meetingList;
    }

    public ArrayList<Offer> getOfferList() {
        return offerList;
    }

    public ArrayList<Listing> getlistingList() {
        return listingList;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getName().equals(getName())
                && otherClient.getMeetingList().equals(getMeetingList())
                && otherClient.getOfferList().equals(getOfferList())
                && otherClient.getlistingList().equals(getlistingList())
                && otherClient.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, meetingList, offerList, listingList, tags);
    }

    public Remark getRemark() {
        return remark;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Number of Meetings: ")
                .append(getMeetingList().size())
                .append("; Number of Offers: ")
                .append(getOfferList().size())
                .append("; Number of Listings: ")
                .append(getlistingList().size())
                .append(" Remark: ")
                .append(getRemark());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
