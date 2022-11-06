package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.teammate.Address;
import seedu.address.model.teammate.Email;
import seedu.address.model.teammate.Name;
import seedu.address.model.teammate.Phone;
import seedu.address.model.teammate.Teammate;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Teammate objects.
 */
public class TeammateBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code TeammateBuilder} with the default details.
     */
    public TeammateBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TeammateBuilder with the data of {@code teammateToCopy}.
     */
    public TeammateBuilder(Teammate teammateToCopy) {
        name = teammateToCopy.getName();
        phone = teammateToCopy.getPhone();
        email = teammateToCopy.getEmail();
        address = teammateToCopy.getAddress();
        tags = new HashSet<>(teammateToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Teammate} that we are building.
     */
    public TeammateBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Teammate} that we are building.
     */
    public TeammateBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Teammate} that we are building.
     */
    public TeammateBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Teammate} that we are building.
     */
    public TeammateBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Teammate} that we are building.
     */
    public TeammateBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Teammate build() {
        return new Teammate(name, phone, email, address, tags);
    }

}
