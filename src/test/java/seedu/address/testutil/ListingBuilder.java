package seedu.address.testutil;

import seedu.address.model.listing.Listing;
import seedu.address.model.offer.Price;
import seedu.address.model.person.Address;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Listing objects.
 */
public class ListingBuilder {

    public static final String DEFAULT_ID = "House";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Person DEFAULT_PERSON = TypicalPersons.BOB;
    public static final String DEFAULT_ASKING_PRICE = "1";

    private String id;
    private Address address;
    private Person owner;
    private Price askingPrice;

    /**
     * Creates a {@code ListingBuilder} with the default details.
     */
    public ListingBuilder() {
        this.id = DEFAULT_ID;
        this.address = new Address(DEFAULT_ADDRESS);
        this.owner = DEFAULT_PERSON;
        this.askingPrice = new Price(DEFAULT_ASKING_PRICE);
    }

    /**
     * Initializes the ListingBuilder with the data of {@code ListingToCopy}.
     */
    public ListingBuilder(Listing listingToCopy) {
        address = listingToCopy.getAddress();
        owner = listingToCopy.getOwner();
        askingPrice = listingToCopy.getAskingPrice();
    }

    /**
     * Sets the {@code id} of the {@code Listing} that we are building.
     */
    public ListingBuilder withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the {@code owner} of the {@code Listing} that we are building.
     */
    public ListingBuilder withOwner(Person owner) {
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

    public Listing build() {
        return new Listing(id, address, owner, askingPrice);
    }

}
