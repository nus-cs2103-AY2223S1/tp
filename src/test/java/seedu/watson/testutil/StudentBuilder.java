package seedu.watson.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.student.Address;
import seedu.watson.model.student.Attendance;
import seedu.watson.model.student.Email;
import seedu.watson.model.student.Name;
import seedu.watson.model.student.Phone;
import seedu.watson.model.student.Remark;
import seedu.watson.model.student.Student;
import seedu.watson.model.student.StudentClass;
import seedu.watson.model.student.subject.SubjectHandler;
import seedu.watson.model.tag.Tag;
import seedu.watson.model.util.SampleDataUtil;

// need to edit based on new student model

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_STUDENTCLASS = "1.3";
    public static final String DEFAULT_SUBJECTHANDLER = "english: CA1:[80.0, 100.0, 0.2, 1.0], "
                                                        + "CA2:[30.0, 56.0, 0.4, 2.0]";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private StudentClass studentClass;
    private SubjectHandler subjectHandler;
    private Set<Remark> remarks;
    private Attendance attendance;
    private Set<Tag> tags;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        studentClass = new StudentClass(DEFAULT_STUDENTCLASS);
        attendance = new Attendance();
        subjectHandler = new SubjectHandler(DEFAULT_SUBJECTHANDLER);
        remarks = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        studentClass = studentToCopy.getStudentClass();
        attendance = studentToCopy.getAttendance();
        remarks = new HashSet<>(studentToCopy.getRemarks());
        subjectHandler = studentToCopy.getSubjectHandler();
        tags = new HashSet<>(studentToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Clazz} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentClass(String withClass) {
        this.studentClass = new StudentClass(withClass);
        return this;
    }

    /**
     * Sets the {@code SubjectHandler} of the {@code Student} that we are building.
     */
    public StudentBuilder withSubjectHandler(String withSubjectHandler) {
        this.subjectHandler = new SubjectHandler(withSubjectHandler);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Student} that we are building.
     */
    public StudentBuilder withAttendance(String withAttendance) {
        try {
            this.attendance = new Attendance(Attendance.parseAttendanceFromJson(withAttendance));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Student} that we are building.
     */
    public StudentBuilder withRemarks(String... remarks) {
        this.remarks = SampleDataUtil.getRemarkSet(remarks);
        return this;
    }

    /**
     * Builds a student object given the attributes
     */
    public Student build() {
        return new Student(name, phone, email, address, studentClass, attendance, remarks, subjectHandler,
                          tags);
    }
}
