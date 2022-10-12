package jeryl.fyp.testutil;

import java.util.HashSet;
import java.util.Set;

import jeryl.fyp.model.student.Email;
import jeryl.fyp.model.student.Name;
import jeryl.fyp.model.student.ProjectName;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.tag.Tag;
import jeryl.fyp.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_ID = "A1355255B";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_PROJECT_NAME = "CS2103 SE";

    private Name name;
    private StudentId id;
    private Email email;
    private ProjectName projectName;
    private Set<Tag> tags;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        id = new StudentId(DEFAULT_STUDENT_ID);
        email = new Email(DEFAULT_EMAIL);
        projectName = new ProjectName(DEFAULT_PROJECT_NAME);
        tags = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        id = studentToCopy.getStudentId();
        email = studentToCopy.getEmail();
        projectName = studentToCopy.getProjectName();
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
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentId(String id) {
        this.id = new StudentId(id);
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
     * Sets the {@code ProjectName} of the {@code Student} that we are building.
     */
    public StudentBuilder withProjectName(String projectName) {
        this.projectName = new ProjectName(projectName);
        return this;
    }

    public Student build() {
        return new Student(name, id, email, projectName, tags);
    }

}
