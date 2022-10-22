package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.product.Product;

/**
 * Represents a Client in MyInsuRec.
 * Guarantees: Name and phone are present and not null and the rest are optional, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Optional<Email> email;
    private final Optional<Birthday> birthday;

    // Data fields
    private final Optional<Address> address;
    private final Set<Product> products = new HashSet<>();
    private final List<Meeting> meetings;

    /**
     * Every field must be present.
     * Birthday may be null.
     */
    public Client(Name name, Phone phone, Optional<Email> email, Optional<Address> address,
                  Optional<Birthday> birthday, Set<Product> products) {
        requireAllNonNull(name, phone, products);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.products.addAll(products);
        this.meetings = new ArrayList<>();
    }

    /**
     * Construct a client with meetings.
     * Birthday may be null.
     */
    public Client(Name name, Phone phone, Optional<Email> email, Optional<Address> address,
                  Optional<Birthday> birthday, List<Meeting> meetings, Set<Product> products) {
        requireAllNonNull(name, phone, email, address, meetings, products);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.meetings = meetings;
        this.products.addAll(products);
    }


    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Optional<Email> getEmail() {
        return email;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public Optional<Birthday> getBirthday() {
        return birthday;
    }

    public boolean hasMeeting() {
        return !meetings.isEmpty();
    }

    public List<Meeting> getMeetings() {
        return Collections.unmodifiableList(meetings);
    }


    /**
     * Adds a meeting to the client's meeting list.
     * @param meeting the meeting to be added
     */
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    /**
     * Removes a meeting from client's meeting list.
     * @param meeting the meeting to be removed
     */
    public void removeMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * Returns an immutable product set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getName().equals(getName())
                && otherClient.getPhone().equals(getPhone())
                && otherClient.getEmail().equals(getEmail())
                && otherClient.getAddress().equals(getAddress())
                && otherClient.getBirthday().equals(getBirthday())
                && otherClient.getProducts().equals(getProducts());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, birthday, products);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append("; Phone: ")
                .append(phone);

        if (email.isPresent()) {
            builder.append("; Email: ").append(email);
        }

        if (address.isPresent()) {
            builder.append("; Address: ").append(address);
        }

        if (birthday.isPresent()) {
            builder.append("; Birthday: ").append(birthday.get());
        }

        if (!products.isEmpty()) {
            builder.append("; Products: ");
            products.forEach(builder::append);
        }
        return builder.toString();
    }

}
