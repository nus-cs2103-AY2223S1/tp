package seedu.address.model.person;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.listing.Listing;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.offer.Offer;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable
 * except meetinglist, listinglist and offerlist.
 */

public class Client extends Person implements Comparable<Client> {


    // Identity fields
    private final Set<Tag> tags = new HashSet<>();


    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);

    }

    /**
     * Constructor for sample client
     */
    public Client() {
        super(
            new Name("Amy Bee"),
            new Phone("85355255"),
            new Email("amy@gmail.com"),
            new Address("123, Jurong West Ave 6, #08-111"), new HashSet<>());
    }


    /**
     * Returns true if both client have the same name.
     * This defines a weaker notion of equality between two client.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * Returns true if this Listing is not owned by toCheck.
     */
    public boolean doNotOwn(Listing toCheck) {
        return !this.equals(toCheck.getName());
    }

    public boolean doNotHaveMeeting(Meeting toCheck) {
        return !this.getName().equals(toCheck.getClient());
    }

    public boolean doNotHaveOffer(Offer toCheck) {
        return !this.getName().equals(toCheck.getClient());
    }

    @Override
    public int compareTo(Client o) {
        return this.getName().fullName.compareTo(o.getName().fullName);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());


        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
