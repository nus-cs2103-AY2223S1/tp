package foodwhere.testutil;

import java.util.HashSet;
import java.util.Set;

import foodwhere.model.detail.Detail;
import foodwhere.model.stall.Address;
import foodwhere.model.stall.Name;
import foodwhere.model.stall.Stall;
import foodwhere.model.stall.Phone;
import foodwhere.model.util.SampleDataUtil;

/**
 * A utility class to help with building Stall objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Address address;
    private Set<Detail> details;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        address = new Address(DEFAULT_ADDRESS);
        details = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code stallToCopy}.
     */
    public PersonBuilder(Stall stallToCopy) {
        name = stallToCopy.getName();
        phone = stallToCopy.getPhone();
        address = stallToCopy.getAddress();
        details = new HashSet<>(stallToCopy.getDetails());
    }

    /**
     * Sets the {@code Name} of the {@code Stall} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code details} into a {@code Set<Detail>} and set it to the {@code Stall} that we are building.
     */
    public PersonBuilder withDetails(String ... details) {
        this.details = SampleDataUtil.getDetailSet(details);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Stall} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Stall} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    public Stall build() {
        return new Stall(name, phone, address, details);
    }

}
