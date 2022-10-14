package seedu.address.model.listing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.offer.Offer;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Listing object contains a currently listed property, its owner, asking price, and offers and clients.
 */
public class Listing {

    // Identity fields
    private final String id;
    private final Address address;
    private final Person owner;
    private final int askingPrice;

    // Data fields
    private int highestOffer;
    private final List<Person> interestedClients;
    private final List<Offer> currentOffers;
    private final Set<Tag> tags = new HashSet<>();

    /**
     ** Constructor for Listing
     * @param address Address
     * @param owner Person
     * @param askingPrice int
     */
    public Listing(String id, Address address, Person owner, int askingPrice) {
        this.id = id;
        this.address = address;
        this.owner = owner;
        this.askingPrice = askingPrice;
        this.highestOffer = 0;
        interestedClients = new ArrayList<>();
        currentOffers = new ArrayList<>();
    }

    /**
     * Gets the id of this listing.
     * @return id of listing
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the name of this owner.
     * @return name of owner
     */
    public Name getName() {
        return this.owner.getName();
    }

    /**
     * Gets the owner.
     * @return owner
     */
    public Person getOwner() {
        return this.owner;
    }

    /**
     * Gets the address of this listing.
     * @return address of listing
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * Gets the asking price of this listing.
     * @return asling price of listing
     */
    public int getAskingPrice() {
        return this.askingPrice;
    }

    /**
     * Getter for a list of interested clients.
     * @return List(Person)
     */
    public List<Person> getInterestedClients() {
        return interestedClients;
    }

    /**
     * Adds prospective client to the interestedClients list.
     * @param client the interested client
     */
    public void addInterestedClient(Person client) {
        this.interestedClients.add(client);
    }

    /**
     * Getter for a list of Offers.
     * @return List(Offer)
     */
    public List<Offer> getCurrentOffers() {
        return currentOffers;
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
     * @param tags to be added
     */
    public void addTags(Set<Tag> tags) {
        this.tags.addAll(tags);
    }

    /**
     * Adds a new offer to the currentOffers list.
     * @param offer the new offer made
     */
    public void addOffer(Offer offer) {
        this.currentOffers.add(offer);
        if (offer.getOfferPrice() > this.highestOffer) {
            this.highestOffer = offer.getOfferPrice();
        }
    }

    /**
     * Returns the highest offer made.
     * @return the highest offer made
     */
    public int getHighestOffer() {
        return this.highestOffer;
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

    /**
     * String representation of Listing.
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

        List<Person> interestedClients = getInterestedClients();
        if (!interestedClients.isEmpty()) {
            builder.append("; Interested Clients: ");
            for (Person client : interestedClients) {
                builder.append(client).append("\n");
            }
        }

        List<Offer> currentOffers = getCurrentOffers();
        if (!currentOffers.isEmpty()) {
            builder.append("; Current Offers: ");
            for (Offer offer : currentOffers) {
                builder.append(offer).append("\n");
            }
        }

        return builder.toString();
    }
}
