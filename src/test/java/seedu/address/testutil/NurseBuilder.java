package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Nurse objects.
 */
public class NurseBuilder {

    public static final String DEFAULT_UID = "101";
    public static final String DEFAULT_CATEGORY = "N";
    public static final String DEFAULT_NAME = "Sarah Bee";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_PHONE = "85352255";
    public static final String DEFAULT_EMAIL = "sarah@gmail.com";
    public static final String DEFAULT_ADDRESS = "124, Jurong West Ave 6, #08-111";

    private Uid uid;
    private String category;
    private Name name;
    private Gender gender;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code NurseBuilder} with the default details.
     */
    public NurseBuilder() {
        uid = new Uid(DEFAULT_UID);
        name = new Name(DEFAULT_NAME);
        gender = new Gender(DEFAULT_GENDER);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the NurseBuilder with the data of {@code nurseToCopy}.
     */
    public NurseBuilder(Person nurseToCopy) {
        category = "N";
        uid = nurseToCopy.getUid();
        name = nurseToCopy.getName();
        gender = nurseToCopy.getGender();
        phone = nurseToCopy.getPhone();
        email = nurseToCopy.getEmail();
        address = nurseToCopy.getAddress();
        tags = new HashSet<>(nurseToCopy.getTags());
    }

    /**
     * Sets the {@code Uid} of the {@code Nurse} that we are building.
     */
    public NurseBuilder withUid(String uid) {
        this.uid = new Uid(uid);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Nurse} that we are building.
     */
    public NurseBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Nurse} that we are building.
     */
    public NurseBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Nurse} that we are building.
     */
    public NurseBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Nurse} that we are building.
     */
    public NurseBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Nurse} that we are building.
     */
    public NurseBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Nurse} that we are building.
     */
    public NurseBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Build a nurse.
     */
    public Nurse build() {
        return new Nurse(uid, name, gender, phone, email, address, tags);
    }
}
