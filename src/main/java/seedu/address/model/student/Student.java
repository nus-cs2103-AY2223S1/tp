package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.attendance.Attendance;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the studentId book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final ClassGroup classGroup;
    private final StudentId studentId;
    private final Set<Tag> tags = new HashSet<>();

    private final Attendance attendance;

    /**
     * Every field must be present and not null.
     * Profile picture is initially set to the default profile picture.
     */
    public Student(Name name, Phone phone, Email email,
                   ClassGroup classGroup, StudentId studentId, Set<Tag> tags, Attendance attendance) {
        requireAllNonNull(name, studentId);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.classGroup = classGroup;
        this.studentId = studentId;
        this.tags.addAll(tags);
        this.attendance = attendance;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public ClassGroup getClassGroup() {
        return classGroup;
    }

    public StudentId getStudentId() {
        return studentId;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }

    /**
     * Returns true is both students have the same studentId
     * @param otherStudent
     * @return Whether this and provided student have the same studentId
     */
    public boolean hasSameId(Student otherStudent) {
        return otherStudent.studentId.equals(studentId);
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
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getClassGroup().equals(getClassGroup())
                && otherStudent.getStudentId().equals(getStudentId())
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, classGroup, studentId, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Class Group: ")
                .append(getClassGroup())
                .append("; StudentId: ")
                .append(getStudentId())
                .append("; Attendance: ")
                .append(getAttendance());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
