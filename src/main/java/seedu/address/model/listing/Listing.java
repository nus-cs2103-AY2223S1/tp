package seedu.address.model.listing;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.offer.Offer;
import seedu.address.model.person.Address;
import seedu.address.model.person.Person;

/**
 * Listing object contains a currently listed property, its owner, asking price, and offers and clients.
 */
public class Listing {
    private final Address address;
    private final Person owner;
    private final int askingPrice;
    private final List<Person> interestedClients;
    private final List<Offer> currentOffers;

    /**
     ** Constructor for Listing
     * @param address Address
     * @param owner Person
     * @param askingPrice int
     */
    public Listing(Address address, Person owner, int askingPrice) {
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
     * @return Person
     */
    public Person getOwner() {
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
     * @return List(Person)
     */
    public List<Person> getInterestedClients() {
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

        List<Person> interstedClients = getInterestedClients();
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
