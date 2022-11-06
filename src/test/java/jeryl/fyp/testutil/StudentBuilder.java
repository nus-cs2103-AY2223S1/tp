package jeryl.fyp.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jeryl.fyp.model.student.*;
import jeryl.fyp.model.tag.Tag;
import jeryl.fyp.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_STUDENT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_ID = "A1355255B";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_PROJECT_NAME = "CS2103 SE";
    public static final String DEFAULT_PROJECT_STATUS = "YTS";

    private StudentName studentName;
    private StudentId id;
    private Email email;
    private ProjectName projectName;
    private ProjectStatus projectStatus;
    private DeadlineList deadlineList;
    private Set<Tag> tags;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        studentName = new StudentName(DEFAULT_STUDENT_NAME);
        id = new StudentId(DEFAULT_STUDENT_ID);
        email = new Email(DEFAULT_EMAIL);
        projectName = new ProjectName(DEFAULT_PROJECT_NAME);
        projectStatus = new ProjectStatus(DEFAULT_PROJECT_STATUS);
        deadlineList = new DeadlineList();
        tags = new HashSet<>();
    }


    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        studentName = studentToCopy.getStudentName();
        id = studentToCopy.getStudentId();
        email = studentToCopy.getEmail();
        projectName = studentToCopy.getProjectName();
        projectStatus = studentToCopy.getProjectStatus();
        deadlineList = studentToCopy.getDeadlineList();
        tags = new HashSet<>(studentToCopy.getTags());
    }

    /**
     * Sets the {@code StudentName} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentName(String studentName) {
        this.studentName = new StudentName(studentName);
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
     * Parses the {@code deadlines} into a {@code Set<Deadline>} and set it to the {@code Student} that we are building.
     * @param deadlines a list of Deadlines represented by a list of string.
     * @return StudentBuilder with deadlines.
     */
    public StudentBuilder withDeadlines(String[] deadlines) {
        Arrays.stream(deadlines)
                .map(ddl -> SampleDataUtil.getDeadline(ddl.split(", deadline: ")[0],
                                ddl.split(", deadline: ")[1])).collect(Collectors.toList())
                .forEach(ddl -> this.deadlineList.add(ddl));
        return this;
    }

    /**
     * Parses the {@code deadlines} into a {@code Set<Deadline>} and set it to the {@code Student} that we are building.
     * @param deadlines a list of Deadlines represented by a list of Deadline Class.
     * @return StudentBuilder with deadlines.
     */
    public StudentBuilder withDeadlines(List<Deadline> deadlines) {
        deadlines.stream().forEach(ddl -> this.deadlineList.add(ddl));
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
     * Sets the {@code ProjectStatus} of the {@code Student} that we are building.
     */
    public StudentBuilder withProjectStatus(String projectStatus) {
        this.projectStatus = new ProjectStatus(projectStatus);
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
        return new Student(studentName, id, email, projectName, projectStatus, tags);
    }

}
