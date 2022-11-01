package seedu.address.model.listing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.offer.Offer;
import seedu.address.model.offer.Price;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Listing object contains a currently listed property, its owner, asking price, and offers and clients.
 */
public class Listing implements Comparable<Listing> {

    // Identity fields
    private final ListingId id;
    private final Address address;
    private final Name owner;
    private final Price askingPrice;

    // Data fields
    private final List<Name> interestedClients;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * * Constructor for Listing
     *
     * @param address     Address
     * @param owner       Person
     * @param askingPrice int
     */
    public Listing(ListingId id, Address address, Name owner, Price askingPrice) {
        this.id = id;
        this.address = address;
        this.owner = owner;
        this.askingPrice = askingPrice;
        interestedClients = new ArrayList<>();
    }

    /**
     * Gets the id of this listing.
     *
     * @return id of listing
     */
    public ListingId getId() {
        return this.id;
    }

    /**
     * Gets the name of this owner.
     *
     * @return name of owner
     */
    public Name getName() {
        return this.owner;
    }


    /**
     * Gets the address of this listing.
     *
     * @return address of listing
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * Gets the asking price of this listing.
     *
     * @return asking price of listing
     */
    public Price getAskingPrice() {
        return this.askingPrice;
    }

    /**
     * Getter for a list of interested clients.
     *
     * @return List(Person)
     */
    public List<Name> getInterestedClients() {
        return interestedClients;
    }

    /**
     * Adds prospective client to the interestedClients list.
     *
     * @param client the interested client
     */
    public void addInterestedClient(Name client) {
        this.interestedClients.add(client);
    }

    /**
     * Checks if the client is already in the listing.
     *
     * @param client name of the client
     * @return true if the client is in the listing, false otherwise
     */
    public boolean hasInterestedClient(Name client) {
        return this.interestedClients.contains(client);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Adds the tags to the listing.
     *
     * @param tags to be added
     */
    public void addTags(Set<Tag> tags) {
        this.tags.addAll(tags);
    }

    /**
     * Checks if the tag already exists for this listing.
     *
     * @param toCheck the tags to be checked
     * @return true if the tag exists, false otherwise
     */
    public boolean hasTag(Set<Tag> toCheck) {
        for (Tag tag : toCheck) {
            if (tags.contains(tag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if both Listings have the same Address.
     * This defines a weaker notion of equality between two Listings.
     */
    public boolean isSameListing(Listing otherListing) {
        if (otherListing == this) {
            return true;
        }

        return otherListing != null
                && (otherListing.address.equals(this.address)
                || otherListing.id.equals(this.id));
    }

    public boolean doNotHaveMeeting(Meeting toCheck) {
        return !this.getName().equals(toCheck.getClient());
    }

    public boolean doNotHaveOffer(Offer toCheck) {
        return !this.getName().equals(toCheck.getClient());
    }

    /**
     * Compares current listing to l
     */
    @Override
    public int compareTo(Listing l) {
        return this.id.value.compareTo(l.id.value);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Listing)) {
            return false;
        }

        Listing otherListing = (Listing) other;
        return otherListing.getId().equals(getId())
                || otherListing.getAddress().equals(getAddress());
    }

    /**
     * String representation of Listing.
     *
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("; ID: ")
                .append(this.id)
                .append("; Address: ")
                .append(this.address)
                .append("; Owner: ")
                .append(this.owner)
                .append("; Asking Price: ")
                .append(this.askingPrice);

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        List<Name> interestedClients = getInterestedClients();

        if (!interestedClients.isEmpty()) {
            builder.append("; Interested Clients: ");
            for (Name client : interestedClients) {
                builder.append(client).append("\n");
            }
        }

        return builder.toString();
    }
}
