package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.student.Address;
import seedu.address.model.student.Class;
import seedu.address.model.student.Id;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_STUDENT_NAME = "Amy Bee";
    public static final String DEFAULT_ID = "123A";
    public static final String DEFAULT_CLASS = "1A";
    public static final String DEFAULT_PARENT_NAME = "John Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name studentName;
    private Id id;
    private Class className;
    private Name parentName;
    private Phone phone;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        studentName = new Name(DEFAULT_STUDENT_NAME);
        id = new Id(DEFAULT_ID);
        className = new Class(DEFAULT_CLASS);
        parentName = new Name(DEFAULT_PARENT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        studentName = studentToCopy.getStudentName();
        id = studentToCopy.getId();
        className = studentToCopy.getClassName();
        parentName = studentToCopy.getParentName();
        phone = studentToCopy.getPhone();
        address = studentToCopy.getAddress();
        tags = new HashSet<>(studentToCopy.getTags());
    }

    /**
     * Sets the parent {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentName(String name) {
        this.studentName = new Name(name);
        return this;
    }

    /**
     * Sets the student {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withParentName(String name) {
        this.parentName = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
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
     * Sets the {@code Id} of the {@code Student} that we are building.
     */
    public StudentBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the class {@code Class} of the {@code Student} that we are building.
     */
    public StudentBuilder withClassName(String className) {
        this.className = new Class(className);
        return this;
    }
    public Student build() {
        return new Student(studentName, id, className, parentName, phone, address, tags);
    }

}
