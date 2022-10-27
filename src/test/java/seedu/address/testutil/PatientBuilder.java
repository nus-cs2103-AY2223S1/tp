package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_UID = "100";
    public static final String DEFAULT_CATEGORY = "P";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Uid uid;
    private final String category;
    private Name name;
    private Gender gender;
    private Phone phone;
    private Email email;
    private Address address;
    private List<DateSlot> dateSlots;
    private Set<Tag> tags;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        uid = new Uid(DEFAULT_UID);
        category = DEFAULT_CATEGORY;
        name = new Name(DEFAULT_NAME);
        gender = new Gender(DEFAULT_GENDER);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        dateSlots = new ArrayList<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Person patientToCopy) {
        category = "P";
        uid = patientToCopy.getUid();
        name = patientToCopy.getName();
        gender = patientToCopy.getGender();
        phone = patientToCopy.getPhone();
        email = patientToCopy.getEmail();
        address = patientToCopy.getAddress();
        dateSlots = new ArrayList<>(((Patient) patientToCopy).getDatesSlots());
        tags = new HashSet<>(patientToCopy.getTags());
    }

    /**
     * Sets the {@code Uid} of the {@code Patient} that we are building.
     */
    public PatientBuilder withUid(String uid) {
        this.uid = new Uid(uid);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Patient} that we are building.
     */
    public PatientBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Patient} that we are building.
     */
    public PatientBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Patient} that we are building.
     */
    public PatientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Patient} that we are building.
     */
    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Patient} that we are building.
     */
    public PatientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Parses the {@code datesSlots} into a {@code Set<DateSlot>} and
     * set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withDatesSlots(String... datesSlots) {
        this.dateSlots = SampleDataUtil.getDatesSlotsList(datesSlots);
        return this;
    }

    /**
     * Build a patient.
     */
    public Patient build() {
        return new Patient(uid, name, gender, phone, email, address, tags, dateSlots);
    }
}
