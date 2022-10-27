package seedu.watson.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.person.Address;
import seedu.watson.model.person.Attendance;
import seedu.watson.model.person.Email;
import seedu.watson.model.person.Name;
import seedu.watson.model.person.Person;
import seedu.watson.model.person.Phone;
import seedu.watson.model.person.Remark;
import seedu.watson.model.person.StudentClass;
import seedu.watson.model.person.subject.Grades;
import seedu.watson.model.person.subject.Subject;
import seedu.watson.model.person.subject.SubjectHandler;
import seedu.watson.model.tag.Tag;
import seedu.watson.model.util.SampleDataUtil;

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
    public static final String DEFAULT_SUBJECTHANDLER = "english: CA1:[80.0, 100.0, 0.2, 1.0], "
                                                        + "CA2:[30.0, 56.0, 0.4, 2.0]";
    public static final String DEFAULT_PERSONALITY = "Hardworking";
    public static final String DEFAULT_ATTENDANCE = "9/10";
    public static final String DEFAULT_SUBJECT = "Math";
    public static final String DEFAULT_GRADE = "83.4";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private StudentClass studentClass;
    private SubjectHandler subjectHandler;
    private Set<Remark> remarks;
    private Attendance attendance;
    private Set<Subject> subjects;
    private Grades grades;
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
        attendance = new Attendance();
        subjectHandler = new SubjectHandler(DEFAULT_SUBJECTHANDLER);
        remarks = new HashSet<>();
        subjects = new HashSet<>();
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
        attendance = personToCopy.getAttendance();
        remarks = new HashSet<>(personToCopy.getRemarks());
        subjectHandler = personToCopy.getSubjectHandler();
        subjects = personToCopy.getSubjectsTaken();
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
     * Sets the {@code SubjectHandler} of the {@code Person} that we are building.
     */
    public PersonBuilder withSubjectHandler(String withSubjectHandler) {
        this.subjectHandler = new SubjectHandler(withSubjectHandler);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Person} that we are building.
     */
    public PersonBuilder withAttendance(String withAttendance) {
        try {
            this.attendance = new Attendance(Attendance.parseAttendanceFromJson(withAttendance));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        return new Person(name, phone, email, address, studentClass, attendance, remarks, subjectHandler,
                          tags);
    }
}
