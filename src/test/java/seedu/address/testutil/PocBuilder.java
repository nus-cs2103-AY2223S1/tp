package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.poc.Email;
import seedu.address.model.poc.PersonName;
import seedu.address.model.poc.Phone;
import seedu.address.model.poc.Poc;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Poc objects.
 */
public class PocBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    private PersonName name;
    private Phone phone;
    private Email email;
    private Set<Tag> tags;

    /**
     * Creates a {@code PocBuilder} with the default details.
     */
    public PocBuilder() {
        name = new PersonName(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PocBuilder with the data of {@code pocToCopy}.
     */
    public PocBuilder(Poc pocToCopy) {
        name = pocToCopy.getName();
        phone = pocToCopy.getPhone();
        email = pocToCopy.getEmail();
        tags = new HashSet<>(pocToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Poc} that we are building.
     */
    public PocBuilder withName(String name) {
        this.name = new PersonName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Poc} that we are building.
     */
    public PocBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Poc} that we are building.
     */
    public PocBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Poc} that we are building.
     */
    public PocBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Poc build() {
        return new Poc(name, phone, email, tags);
    }

}
