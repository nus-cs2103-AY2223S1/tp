package seedu.address.model.offer;

import seedu.address.model.listing.Listing;
import seedu.address.model.client.Client;

/**
 * Offer Class represents a clients offer for a listing, containing an offer price.
 */
public class Offer {

    /**
     * Client making the offer.
     */
    private final Client client;
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
     * @param client Client
     * @param listing Listing
     * @param offerPrice int
     */
    public Offer(Client client, Listing listing, int offerPrice) {
        this.client = client;
        this.listing = listing;
        this.offerPrice = offerPrice;
    }

    /**
     * Getter for client.
     * @return Client
     */
    public Client getClient() {
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
     * Getter for offer price.
     * @return int
     */
    public int getOfferPrice() {
        return offerPrice;
    }

    /**
     * String representation of offer.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s is offering $%s for %s",
                this.client.getName(), this.offerPrice, this.listing.getAddress());
    }
}
