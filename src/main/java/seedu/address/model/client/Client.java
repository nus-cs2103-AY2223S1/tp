package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.parser.DateKeyword;
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
    private final Optional<Address> address;
    private final Set<Product> products;
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
        this.products = Set.copyOf(products);
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
        this.products = Set.copyOf(products);
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

    public boolean hasMeeting(Meeting meeting) {
        return meetings.contains(meeting);
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
     * Returns true if client has the given product.
     */
    public boolean hasProduct(Product product) {
        return products.contains(product);
    }

    /**
     * Returns an immutable product set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Product> getProducts() {
        return products;
    }

    /**
     * Returns true if client's birthday is within the given period {@code dateKeyword}
     */
    public boolean isBirthdayInPeriod(DateKeyword dateKeyword) {
        if (birthday.isEmpty()) {
            return false;
        }
        return birthday.get().isBirthdayInPeriod(dateKeyword);
    }

    public boolean isBirthdayValid() {
        return birthday.isEmpty() ? true : Birthday.isDateInValidPeriod(birthday.get());
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
            builder.append("; Email: ").append(email.get());
        }

        if (address.isPresent()) {
            builder.append("; Address: ").append(address.get());
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
