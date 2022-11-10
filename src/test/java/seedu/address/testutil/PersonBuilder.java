package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Supplier;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_PERSON_CATEGORY = "Buyer";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_LOCATION = "Singapore";

    private PersonCategory personCategory;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Location location;
    private Set<Order> orders;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        personCategory = PersonCategory.getFromString(DEFAULT_PERSON_CATEGORY); // Person Category instantiated later
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        location = new Location(DEFAULT_LOCATION);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        personCategory = personToCopy.getPersonCategory();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        location = personToCopy.getLocation();
    }

    /**
     * Sets the {@code PersonCategory} of the {@code Person} that we are building.
     */
    public PersonBuilder withPersonCategory(String personCategory) {
        this.personCategory = PersonCategory.getFromString(personCategory);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Location} that we are building.
     */
    public PersonBuilder withLocation(String loc) {
        this.location = new Location(loc);
        return this;
    }

    public Person build() {
        return new Person(personCategory, name, phone, email, address, location);
    }

    /**
     * Builds {@code Buyer} with the specified Buyer attributes.
     * @return Created Buyer object.
     */
    public Buyer buildBuyer() {
        personCategory = PersonCategory.BUYER;
        return new Buyer(name, phone, email, address, location, new ArrayList<>());
    }

    /**
     * Builds {@code Deliverer} with the specified Deliverer attributes.
     * @return Created Deliverer object.
     */
    public Deliverer buildDeliverer() {
        personCategory = PersonCategory.DELIVERER;
        return new Deliverer(name, phone, email, address, location, new ArrayList<>());
    }

    /**
     * Builds {@code Supplier} with the specified Supplier attributes.
     * @return Created Supplier object.
     */
    public Supplier buildSupplier() {
        personCategory = PersonCategory.SUPPLIER;
        return new Supplier(name, phone, email, address, location, new ArrayList<>());
    }
}
