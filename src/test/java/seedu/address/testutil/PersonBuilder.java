package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.StudentClass;
import seedu.address.model.person.subject.Attendance;
import seedu.address.model.person.subject.Grade;
import seedu.address.model.person.subject.SubjectHandler;
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
    public static final String DEFAULT_STUDENTCLASS = "1.3";
    public static final String DEFAULT_PERSONALITY = "Hardworking";
    public static final String DEFAULT_ATTENDANCE = "9/10";
    public static final String DEFAULT_SUBJECT = "Math";
    public static final String DEFAULT_GRADE = "83.4";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private StudentClass studentClass;
    private Set<Remark> remarks;
    private Attendance attendance;
    private SubjectHandler subjectHandler;
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
        studentClass = new StudentClass(DEFAULT_STUDENTCLASS);
        remarks = new HashSet<>();
        subjectHandler = new SubjectHandler();
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
        studentClass = personToCopy.getStudentClass();
        remarks = new HashSet<>(personToCopy.getRemarks());
        subjectHandler = personToCopy.getSubjectHandler();
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
    public PersonBuilder withTags(String... tags) {
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
    public PersonBuilder withStudentClass(String withClass) {
        this.studentClass = new StudentClass(withClass);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemarks(String... remarks) {
        this.remarks = SampleDataUtil.getRemarkSet(remarks);
        return this;
    }

    /**
     * Builds a person object given the attributes
     */
    public Person build() {
        return new Person(name, phone, email, address, studentClass, remarks,
                          subjectHandler, tags);
    }
}
