package seedu.address.testutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.client.Address;
import seedu.address.model.client.Birthday;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.product.Product;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final LocalDate DEFAULT_BIRTHDAY = LocalDate.of(2000, 1, 1);

    private Name name;
    private Phone phone;
    private Optional<Email> email;
    private Optional<Address> address;
    private Optional<Birthday> birthday;
    private Set<Product> products;
    private List<Meeting> meetings;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = Optional.of(new Email(DEFAULT_EMAIL));
        address = Optional.of(new Address(DEFAULT_ADDRESS));
        birthday = Optional.empty();
        meetings = new ArrayList<>();
        products = new HashSet<>();
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
        address = clientToCopy.getAddress();
        birthday = clientToCopy.getBirthday();
        meetings = new ArrayList<>(clientToCopy.getMeetings());
        products = new HashSet<>(clientToCopy.getProducts());
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building.
     */
    public ClientBuilder withAddress(String address) {
        this.address = Optional.of(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Client} that we are building.
     */
    public ClientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientBuilder withEmail(String email) {
        this.email = Optional.of(new Email(email));
        return this;
    }
    /**
     * Sets the {@code Birthday} of the {@code Client} that we are building.
     */
    public ClientBuilder withBirthday(LocalDate birthday) {
        this.birthday = Optional.of(new Birthday(birthday));
        return this;
    }

    /**
     * Parses the {@code products} into a {@code Set<Product>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withProducts(String ... products) {
        this.products = SampleDataUtil.getProductSet(products);
        return this;
    }

    /**
     * Appends new meeting to the {@code meetings} of the {@code Client} that we are building.
     */
    public ClientBuilder withMeeting(Meeting meeting) {
        this.meetings.add(meeting);
        return this;
    }

    public Client build() {
        return new Client(name, phone, email, address, birthday, meetings, products);
    }

}
