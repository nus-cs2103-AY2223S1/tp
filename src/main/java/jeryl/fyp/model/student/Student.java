package jeryl.fyp.model.student;

import static jeryl.fyp.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jeryl.fyp.model.tag.Tag;

/**
 * Represents a Student in the FYP manager.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final StudentName studentName;
    private final StudentId studentId;
    private final Email email;
    private final ProjectName projectName;
    private final ProjectStatus projectStatus;
    private final DeadlineList deadlineList;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field except deadlineList must be present and not null.
     */
    public Student(StudentName studentName, StudentId studentId, Email email, ProjectName projectName,
                   ProjectStatus projectStatus, Set<Tag> tags) {
        this(studentName, studentId, email, projectName, projectStatus, new DeadlineList(), tags);
    }

    /**
     * Every field must be present and not null. This is for restoration.
     */
    public Student(StudentName studentName, StudentId studentId, Email email, ProjectName projectName,
                   ProjectStatus projectStatus, DeadlineList deadlineList, Set<Tag> tags) {
        requireAllNonNull(studentName, studentId, email, projectName, projectStatus, tags);
        this.studentName = studentName;
        this.studentId = studentId;
        this.email = email;
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        this.deadlineList = deadlineList;
        this.tags.addAll(tags);
    }

    public StudentName getStudentName() {
        return studentName;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public Email getEmail() {
        return email;
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public DeadlineList getDeadlineList() {
        return deadlineList;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both students have the same ID.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudentId(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getStudentId().equals(getStudentId());
    }

    /**
     * Returns true if both students have the same email.
     * This is to avoid student with the same email being added to the system.
     */
    public boolean hasSameEmail(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && !(this.isSameStudentId(otherStudent))
                && otherStudent.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getStudentName().equals(getStudentName())
                && otherStudent.getStudentId().equals(getStudentId())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getProjectName().equals(getProjectName())
                && otherStudent.getDeadlineList().equals(getDeadlineList())
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentName, studentId, email, projectName, projectStatus, deadlineList, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getStudentName())
                .append("; Student ID: ")
                .append(getStudentId())
                .append("; Email: ")
                .append(getEmail())
                .append("; ProjectName: ")
                .append(getProjectName())
                .append("; ProjectStatus: ")
                .append(getProjectStatus())
                .append("; Deadlines: ")
                .append(getDeadlineList());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
