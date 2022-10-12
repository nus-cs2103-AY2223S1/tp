package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.address.Address;
import seedu.address.model.desiredcharacteristics.DesiredCharacteristics;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.pricerange.PriceRange;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import static java.util.Objects.requireNonNull;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private PriceRange priceRange;
    private DesiredCharacteristics desiredCharacteristics;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        priceRange = null;
        desiredCharacteristics = null;
        tags = new HashSet<>();
    }
    
    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        priceRange = personToCopy.getPriceRange().orElse(null);
        desiredCharacteristics = personToCopy.getDesiredCharacteristics().orElse(null);
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
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
     * Sets the {@code Price Range} of the {@code Person} that we are building.
     */
    public PersonBuilder withPriceRange(String priceRange) {
        this.priceRange = new PriceRange(priceRange);
        return this;
    }

    /**
     * Removes the {@code Price Range} of the {@code Person} that we are building.
     */
    public PersonBuilder withNoPriceRange() {
        this.priceRange = null;
        return this;
    }

    /**
     * Sets the {@code DesiredCharacteristics} of the {@code Person} that we are building.
     */
    public PersonBuilder withDesiredCharacteristics(String desiredCharacteristics) {
        this.desiredCharacteristics = new DesiredCharacteristics(desiredCharacteristics);
        return this;
    }

    /**
     * Removes the {@code DesiredCharacteristics} of the {@code Person} that we are building.
     */
    public PersonBuilder withNoDesiredCharacteristics() {
        this.desiredCharacteristics = null;
        return this;
    }
    
    public Person build() {
        return new Person(name, phone, email, address, priceRange, desiredCharacteristics, tags);
    }

}
