package seedu.realtime.testutil;

import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.offer.Price;
import seedu.realtime.model.person.Name;

/**
 * A utility class to help with building Offer objects.
 */
public class OfferBuilder {

    public static final String DEFAULT_NAME = "John Doe";
    public static final String DEFAULT_ID = "1";
    public static final String DEFAULT_OFFER_PRICE = "1";

    private Name buyer;
    private ListingId listing;
    private Price offerPrice;

    /**
     * Creates a {@code OfferBuilder} with the default details.
     */
    public OfferBuilder() {
        buyer = new Name(DEFAULT_NAME);
        listing = new ListingId(DEFAULT_ID);
        offerPrice = new Price(DEFAULT_OFFER_PRICE);
    }

    /**
     * Initializes the OfferBuilder with the data of {@code OfferToCopy}.
     */
    public OfferBuilder(Offer offerToCopy) {
        buyer = offerToCopy.getClient();
        listing = offerToCopy.getListing();
        offerPrice = offerToCopy.getOfferPrice();
    }

    /**
     * Sets the {@code id} of the {@code Offer} that we are building.
     */
    public OfferBuilder withBuyer(String buyer) {
        this.buyer = new Name(buyer);
        return this;
    }

    /**
     * Sets the {@code owner} of the {@code Offer} that we are building.
     */
    public OfferBuilder withListing(String id) {
        this.listing = new ListingId(id);
        return this;
    }

    /**
     * Sets the {@code askingPrice} of the {@code Offer} that we are building.
     */
    public OfferBuilder withOfferPrice(String offerPrice) {
        this.offerPrice = new Price(offerPrice);
        return this;
    }

    /**
     * Builds a offer
     */
    public Offer build() {
        return new Offer(buyer, listing, offerPrice);
    }

}
