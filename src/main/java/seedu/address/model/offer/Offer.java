package seedu.address.model.offer;

import seedu.address.model.person.Address;
import seedu.address.model.person.Name;

/**
 * Offer Class represents a clients offer for a listing, containing an offer price.
 */
public class Offer {

    /**
     * Client making the offer.
     */
    private final Name client;
    /**
     * Listing the offer is for.
     */
    private final Address listing;
    /**
     * Price client is offering.
     */
    private final Price offerPrice;

    /**
     * Constructor for offer object.
     *
     * @param client Name
     * @param listing Address
     * @param offerPrice Price
     */
    public Offer(Name client, Address listing, Price offerPrice) {
        this.client = client;
        this.listing = listing;
        this.offerPrice = offerPrice;
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
    public Address getListing() {
        return listing;
    }

    /**
     * Getter for offer price.
     * @return int
     */
    public Price getOfferPrice() {
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
        final StringBuilder builder = new StringBuilder();
        builder.append("Listing address: ")
                .append(getListing())
                .append("; Client: ")
                .append(getClient())
                .append("; Offer Price: S$")
                .append(getOfferPrice());
        return builder.toString();
    }
}
