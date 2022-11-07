package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Tutorial;
import seedu.address.model.social.Social;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_SOCIAL = "<none>";
    public static final String DEFAULT_OCCUPATION = "STUDENT";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TUTORIAL = "T08";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Occupation occupation;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Tutorial tutorial;
    private Set<Tag> tags;
    private Social social;
    private Set<Group> groups;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        occupation = new Occupation(DEFAULT_OCCUPATION);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        tutorial = new Tutorial(DEFAULT_TUTORIAL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        social = new Social();
        groups = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        occupation = personToCopy.getOccupation();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        tutorial = personToCopy.getTutorial();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        social = personToCopy.getSocial();
        groups = new HashSet<>(personToCopy.getGroups());
    }

    /**
     * Sets the {@code Occupation} of the {@code Person} that we are building.
     */
    public PersonBuilder withOccupation(String occupation) {
        this.occupation = new Occupation(occupation);
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code groups} into a {@code Set<Group>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withGroups(String ... groups) {
        this.groups = SampleDataUtil.getGroupSet(groups);
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
     * Sets the {@code Tutorial} of the {@code Person} that we are building.
     *
     * @param tutorial input tutorial
     * @return person with tutorial
     */
    public PersonBuilder withTutorial(String tutorial) {
        this.tutorial = new Tutorial(tutorial);
        return this;
    }

    /**
     * Sets the {@code Social} of the {@code Person} that we are building.
     *
     * @param social input social
     * @return person with social
     */
    public PersonBuilder withSocial(Social social) {
        this.social = social;
        return this;
    }

    public Person build() {
        return new Person(occupation, name, phone, email, tutorial, address, tags, social, groups);
    }

}
