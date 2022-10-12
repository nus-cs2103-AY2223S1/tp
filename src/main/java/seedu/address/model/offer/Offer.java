package seedu.address.model.offer;

import seedu.address.model.listing.Listing;
import seedu.address.model.person.Person;

/**
 * Offer Class represents a clients offer for a listing, containing an offer price.
 */
public class Offer {

    /**
     * Client making the offer.
     */
    private final Person person;
    /**
     * Listing the offer is for.
     */
    private final Listing listing;
    /**
     * Price client is offering.
     */
    private final int offerPrice;

    /**
     * Constructor for offer object.
     *
     * @param client Person
     * @param listing Listing
     * @param offerPrice int
     */
    public Offer(Person client, Listing listing, int offerPrice) {
        this.person = client;
        this.listing = listing;
        this.offerPrice = offerPrice;
    }

    /**
     * Getter for client.
     * @return Person
     */
    public Person getClient() {
        return person;
    }

    /**
     * Getter for listing.
     * @return Listing
     */
    public Listing getListing() {
        return listing;
    }

    /**
     * Getter for offer price.
     * @return int
     */
    public Integer getOfferPrice() {
        return offerPrice;
    }

    /**
     * Returns true if both listings have the same address.
     * This defines a weaker notion of equality between two listings.
     */
    public boolean isSameOffer(Offer otherOffer) {
        if (otherOffer == this) {
            return true;
        }

        return otherOffer != null
                && otherOffer.getOfferPrice().equals(getOfferPrice());
    }

    /**
     * String representation of offer.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s is offering $%d for %s",
                this.person.getName(), this.offerPrice, this.listing.getAddress());
    }
}
