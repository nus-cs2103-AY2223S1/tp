package seedu.address.model.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.product.Product;

/**
 * This class is used to create a mutable copy of a {@code Client} if a field in {@code Client} needs to be updated
 * due to the immutability of {@code Client}.
 */
public class ClientRebuilder {
    private Name name;
    private Phone phone;
    private Optional<Email> email;
    private Optional<Address> address;
    private Optional<Birthday> birthday;
    private Set<Product> products;
    private List<Meeting> meetings;

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientRebuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
        address = clientToCopy.getAddress();
        birthday = clientToCopy.getBirthday();
        products = new HashSet<>(clientToCopy.getProducts()); // mutable unlike in Client
        meetings = new ArrayList<>(clientToCopy.getMeetings());
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientRebuilder withName(Name name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Client} that we are building.
     */
    public ClientRebuilder withPhone(Phone phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientRebuilder withEmail(Email email) {
        this.email = Optional.ofNullable(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building.
     */
    public ClientRebuilder withAddress(Address address) {
        this.address = Optional.ofNullable(address);
        return this;
    }


    /**
     * Sets the {@code Birthday} of the {@code Client} that we are building.
     */
    public ClientRebuilder withBirthday(Birthday birthday) {
        this.birthday = Optional.ofNullable(birthday);
        return this;
    }

    /**
     * Sets the {@code Set<Product>} of the {@code Client} that we are building.
     */
    public ClientRebuilder withProducts(Set<Product> products) {
        this.products = products;
        return this;
    }

    /**
     * Adds a {@code Product} to the {@code Set<Product>} of the {@code Client} that we are building.
     */
    public ClientRebuilder addProduct(Product product) {
        this.products.add(product);
        return this;
    }

    /**
     * Removes a {@code Product} to the {@code Set<Product>} of the {@code Client} that we are building.
     */
    public ClientRebuilder removeProduct(Product product) {
        assert this.products.contains(product);
        this.products.remove(product);
        return this;
    }

    /**
     * Sets the {@code List<Meeting>} of the {@code Client} that we are building.
     */
    public ClientRebuilder withMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
        return this;
    }


    /**
     * Adds a {@code Meeting} to the {@code List<Meeting>} of the {@code Client} that we are building.
     */
    public ClientRebuilder addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
        return this;
    }

    /**
     * Removes a {@code Meeting} to the {@code List<Meeting>} of the {@code Client} that we are building.
     */
    public ClientRebuilder removeMeeting(Meeting meeting) {
        assert this.meetings.contains(meeting);
        this.meetings.remove(meeting);
        return this;
    }

    /**
     * Return an immutable {@code Client} object with all the specified attributes.
     */
    public Client build() {
        return new Client(name, phone, email, address, birthday, meetings, products);
    }
}
