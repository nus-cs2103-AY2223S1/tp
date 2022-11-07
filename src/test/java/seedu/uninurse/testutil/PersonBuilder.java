package seedu.uninurse.testutil;

import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Person;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.tag.TagList;
import seedu.uninurse.model.util.SampleDataUtil;

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
    private TagList tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        this.name = new Name(DEFAULT_NAME);
        this.phone = new Phone(DEFAULT_PHONE);
        this.email = new Email(DEFAULT_EMAIL);
        this.address = new Address(DEFAULT_ADDRESS);
        this.tags = new TagList();
    }

    /**
     * Initializes the PersonBuilder with the data of personToCopy.
     */
    public PersonBuilder(Person personToCopy) {
        this.name = personToCopy.getName();
        this.phone = personToCopy.getPhone();
        this.email = personToCopy.getEmail();
        this.address = personToCopy.getAddress();
        this.tags = personToCopy.getTags();
    }

    /**
     * Sets the Name of the Person that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the Phone of the Person that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the Email of the Person that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the Address of the Person that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Parses the tags into a TagList and set it to the Person that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagList(tags);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, tags);
    }

}
