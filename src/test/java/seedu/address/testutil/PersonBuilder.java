package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.job.Id;
import seedu.address.model.job.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cap;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GraduationDate;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.University;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GENDER = "Female";
    public static final String DEFAULT_GRADUATION_DATE = "05-2024";
    public static final double DEFAULT_CAP_VALUE = 4.99;
    public static final double DEFAULT_CAP_MAXIMUM_VALUE = 5.0;
    public static final String DEFAULT_UNIVERSITY = "NUS";
    public static final String DEFAULT_MAJOR = "Computer Science";
    public static final String DEFAULT_JOB_ID = "17839";
    public static final String DEFAULT_JOB_TITLE = "Intern, Software Engineer";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Gender gender;
    private GraduationDate graduationDate;
    private Cap cap;
    private University university;
    private Major major;
    private Id id;
    private Title title;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        gender = new Gender(DEFAULT_GENDER);
        graduationDate = new GraduationDate(DEFAULT_GRADUATION_DATE);
        cap = new Cap(DEFAULT_CAP_VALUE, DEFAULT_CAP_MAXIMUM_VALUE);
        university = new University(DEFAULT_UNIVERSITY);
        major = new Major(DEFAULT_MAJOR);
        id = new Id(DEFAULT_JOB_ID);
        title = new Title(DEFAULT_JOB_TITLE);
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
        gender = personToCopy.getGender();
        graduationDate = personToCopy.getGraduationDate();
        cap = personToCopy.getCap();
        university = personToCopy.getUniversity();
        major = personToCopy.getMajor();
        id = personToCopy.getJob().getId();
        title = personToCopy.getJob().getTitle();
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
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Cap} of the {@code Person} that we are building.
     */
    public PersonBuilder withCap(double capValue, double maximumValue) {
        this.cap = new Cap(capValue, maximumValue);
        return this;
    }

    /**
     * Sets the {@code GraduationDate} of the {@code Person} that we are building.
     */
    public PersonBuilder withGraduationDate(String graduationDate) {
        this.graduationDate = new GraduationDate(graduationDate);
        return this;
    }

    /**
     * Sets the {@code University} of the {@code Person} that we are building.
     */
    public PersonBuilder withUniversity(String university) {
        this.university = new University(university);
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code Person} that we are building.
     */
    public PersonBuilder withMajor(String major) {
        this.major = new Major(major);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Person} that we are building.
     */
    public PersonBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code Title} of the {@code Person} that we are building.
     */
    public PersonBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Builds the {@code Person} that we are building.
     */
    public Person build() {
        return new Person(name, phone, email,
                address,
                gender,
                graduationDate,
                cap,
                university,
                major,
                id,
                title,
                tags);
    }

}
