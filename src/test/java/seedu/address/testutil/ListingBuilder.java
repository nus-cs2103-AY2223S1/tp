package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.listing.Listing;
import seedu.address.model.listing.ListingId;
import seedu.address.model.offer.Price;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Listing objects.
 */
public class ListingBuilder {

    public static final String DEFAULT_ID = "House";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_CLIENT = "John Doe";
    public static final String DEFAULT_ASKING_PRICE = "1";

    private ListingId id;
    private Address address;
    private Name owner;
    private Price askingPrice;
    private Set<Tag> tags;

    /**
     * Creates a {@code ListingBuilder} with the default details.
     */
    public ListingBuilder() {
        id = new ListingId(DEFAULT_ID);
        address = new Address(DEFAULT_ADDRESS);
        owner = new Name(DEFAULT_CLIENT);
        askingPrice = new Price(DEFAULT_ASKING_PRICE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ListingBuilder with the data of {@code ListingToCopy}.
     */
    public ListingBuilder(Listing listingToCopy) {
        id = listingToCopy.getId();
        address = listingToCopy.getAddress();
        owner = listingToCopy.getName();
        askingPrice = listingToCopy.getAskingPrice();
        tags = new HashSet<>(listingToCopy.getTags());
    }

    /**
     * Sets the {@code id} of the {@code Listing} that we are building.
     */
    public ListingBuilder withId(String id) {
        this.id = new ListingId(id);
        return this;
    }

    /**
     * Sets the {@code owner} of the {@code Listing} that we are building.
     */
    public ListingBuilder withOwner(String owner) {
        this.owner = new Name(owner);
        return this;
    }

    /**
     * Sets the {@code askingPrice} of the {@code Listing} that we are building.
     */
    public ListingBuilder withAskingPrice(String askingPrice) {
        this.askingPrice = new Price(askingPrice);
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
    public ListingBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Builds a listing
     */
    public Listing build() {
        Listing listing = new Listing(id, address, owner, askingPrice);
        listing.addTags(tags);
        return listing;
    }

}
