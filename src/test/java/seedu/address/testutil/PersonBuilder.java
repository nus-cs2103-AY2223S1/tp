package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Clazz;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Personality;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Subject;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

// need to edit based on new person model
/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_CLAZZ = "1.3";
    public static final String DEFAULT_PERSONALITY = "Hardworking";
    public static final String DEFAULT_ATTENDANCE = "9/10";
    public static final String DEFAULT_SUBJECT = "Math";
    public static final String DEFAULT_GRADE = "83.4";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Clazz clazz;
    private Personality personality;
    private Attendance attendance;
    private Subject subject;
    private Grade grade;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        clazz = new Clazz(DEFAULT_CLAZZ);
        personality = new Personality(DEFAULT_PERSONALITY);
        attendance = new Attendance(DEFAULT_ATTENDANCE);
        subject = new Subject(DEFAULT_SUBJECT);
        grade = new Grade(DEFAULT_GRADE);
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
        clazz = personToCopy.getClazz();
        personality = personToCopy.getPersonality();
        attendance = personToCopy.getAttendance();
        subject = personToCopy.getSubject();
        grade = personToCopy.getGrade();
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
     * Sets the {@code Clazz} of the {@code Person} that we are building.
     */
    public PersonBuilder withClazz(String clazz) {
        this.clazz = new Clazz(clazz);
        return this;
    }

    /**
     * Sets the {@code Personality} of the {@code Person} that we are building.
     */
    public PersonBuilder withPersonality(String personality) {
        this.personality = new Personality(personality);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Person} that we are building.
     */
    public PersonBuilder withAttendance(String attendance) {
        this.attendance = new Attendance(attendance);
        return this;
    }

    /**
     * Sets the {@code Subject} of the {@code Person} that we are building.
     */
    public PersonBuilder withSubject(String subject) {
        this.subject = new Subject(subject);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Person} that we are building.
     */
    public PersonBuilder withGrade(String grade) {
        this.grade = new Grade(grade);
        return this;
    }

    /**
     * Builds a person object given the attributes
     */
    public Person build() {
        return new Person(name, phone, email, address, clazz, personality, attendance,
                subject, grade, tags);
    }

}
