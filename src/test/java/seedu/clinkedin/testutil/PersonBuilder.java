package seedu.clinkedin.testutil;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import seedu.clinkedin.commons.exceptions.DataConversionException;
import seedu.clinkedin.model.link.Link;
import seedu.clinkedin.model.person.Address;
import seedu.clinkedin.model.person.Email;
import seedu.clinkedin.model.person.Name;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.Phone;
import seedu.clinkedin.model.person.Rating;
import seedu.clinkedin.model.person.Status;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_STATUS = "Application Received";
    public static final String DEFAULT_RATING = "6";
    public static final String DEFAULT_NOTE = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private UniqueTagTypeMap tags;
    private Status status;
    private Note note;
    private Rating rating;

    private Set<Link> links;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new UniqueTagTypeMap();
        status = new Status(DEFAULT_STATUS);
        note = new Note(DEFAULT_NOTE);
        rating = new Rating(DEFAULT_RATING);
        links = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        UniqueTagTypeMap tagTypeMap = new UniqueTagTypeMap();
        tagTypeMap.setTagTypeMap(personToCopy.getTags());
        tags = tagTypeMap;
        status = personToCopy.getStatus();
        note = personToCopy.getNote();
        rating = personToCopy.getRating();
        links = new HashSet<>(personToCopy.getLinks());
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
        this.tags = SampleDataUtil.getTagTypeMap(tags);
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
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Person} that we are building.
     */
    public PersonBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Person} that we are building.
     * @param rating
     * @return
     */
    public PersonBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Parses the {@code links} into a {@code Set<Link>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withLinks(String... links) throws DataConversionException {
        this.links = SampleDataUtil.getLinkSet(SampleDataUtil.generateUrls(links));
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, tags, status, note, rating, links);
    }


}
