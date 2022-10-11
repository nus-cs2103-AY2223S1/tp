package seedu.address.model.listing;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.offer.Offer;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;

/**
 * Listing object contains a currently listed property, its owner, asking price, and offers and clients.
 */
public class Listing {
    private final Address address;
    private final Name owner;
    private final int askingPrice;
    private final List<Client> interestedClients;
    private final List<Offer> currentOffers;

    /**
     ** Constructor for Listing
     * @param address Address
     * @param owner Client
     * @param askingPrice int
     */
    public Listing(Address address, Name owner, int askingPrice) {
        this.address = address;
        this.owner = owner;
        this.askingPrice = askingPrice;
        interestedClients = new ArrayList<>();
        currentOffers = new ArrayList<>();
    }

    /**
     * Getter for address.
     * @return Address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Getter for owner.
     * @return Client
     */
    public Name getOwner() {
        return owner;
    }

    /**
     * Getter for asking price.
     * @return int
     */
    public int getAskingPrice() {
        return askingPrice;
    }

    /**
     * Getter for a list of interested clients.
     * @return List(Client)
     */
    public List<Client> getInterestedClients() {
        return interestedClients;
    }

    /**
     * Getter for a list of Offers.
     * @return List(Offer)
     */
    public List<Offer> getCurrentOffers() {
        return currentOffers;
    }

    /**
     * Returns true if both listings have the same address.
     * This defines a weaker notion of equality between two listings.
     */
    public boolean isSameListing(Listing otherListing) {
        if (otherListing == this) {
            return true;
        }

        return otherListing != null
                && otherListing.getAddress().equals(getAddress());
    }


    /**
     * String representation of Listing.
     * @return String
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("; Adress: ")
                .append(getAddress())
                .append("; Owner: ")
                .append(getOwner())
                .append("; Asking Price: ")
                .append(getAskingPrice());

        List<Client> interstedClients = getInterestedClients();
        if (!interstedClients.isEmpty()) {
            builder.append("; Interested Clients: ");
            interstedClients.forEach(builder::append);
        }

        List<Offer> currentOffers = getCurrentOffers();
        if (!currentOffers.isEmpty()) {
            builder.append("; Current Offers: ");
            currentOffers.forEach(builder::append);
        }

        return builder.toString();
    }
}
