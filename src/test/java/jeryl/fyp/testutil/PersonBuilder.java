package jeryl.fyp.testutil;

import java.util.HashSet;
import java.util.Set;

import jeryl.fyp.model.student.*;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.tag.Tag;
import jeryl.fyp.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENTID = "A1355255B";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_PROJECTNAME = "CS2103 SE";

    private Name name;
    private StudentID id;
    private Email email;
    private Address address;
    private String projectName;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        id = new StudentID(DEFAULT_STUDENTID);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        projectName = DEFAULT_PROJECTNAME;
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        id = studentToCopy.getStudentID();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        projectName = studentToCopy.getProjectName();
        tags = new HashSet<>(studentToCopy.getTags());
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
     * Sets the {@code StudentID} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentID(String id) {
        this.id = new StudentID(id);
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
     * Sets the {@code Project} of the {@code Person} that we are building.
     */
    public PersonBuilder withProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public Student build() {
        return new Student(name, id, email, address, projectName, tags);
    }

}
