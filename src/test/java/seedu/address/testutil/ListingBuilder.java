package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.listing.Listing;
import seedu.address.model.listing.ListingId;
import seedu.address.model.offer.Price;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Listing objects.
 */
public class ListingBuilder {

    public static final String DEFAULT_ID = "House";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Client DEFAULT_CLIENT = new ClientBuilder().build();
    public static final String DEFAULT_ASKING_PRICE = "1";
    public static final Set<Tag> DEFAULT_TAGS = new HashSet<>();

    private ListingId id;
    private Address address;
    private Name owner;
    private Price askingPrice;
    private Set<Tag> tags;

    /**
     * Creates a {@code ListingBuilder} with the default details.
     */
    public ListingBuilder() {
        this.id = new ListingId(DEFAULT_ID);
        this.address = new Address(DEFAULT_ADDRESS);
        this.owner = DEFAULT_CLIENT.getName();
        this.askingPrice = new Price(DEFAULT_ASKING_PRICE);
        this.tags = DEFAULT_TAGS;
    }

    /**
     * Initializes the ListingBuilder with the data of {@code ListingToCopy}.
     */
    public ListingBuilder(Listing listingToCopy) {
        id = listingToCopy.getId();
        address = listingToCopy.getAddress();
        owner = listingToCopy.getName();
        askingPrice = listingToCopy.getAskingPrice();
    }

    /**
     * Sets the {@code id} of the {@code Listing} that we are building.
     */
    public ListingBuilder withId(ListingId id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the {@code owner} of the {@code Listing} that we are building.
     */
    public ListingBuilder withOwner(Name owner) {
        this.owner = owner;
        return this;
    }

    /**
     * Sets the {@code askingPrice} of the {@code Listing} that we are building.
     */
    public ListingBuilder withAskingPrice(Price askingPrice) {
        this.askingPrice = askingPrice;
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Listing} that we are building.
     */
    public ListingBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }


    /**
     * Sets the {@code Tag} of the {@code Listing} that we are building.
     */
    public ListingBuilder withTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Listing build() {
        return new Listing(id, address, owner, askingPrice);
    }

}
