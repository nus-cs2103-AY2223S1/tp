package seedu.studmap.model.student;

import static seedu.studmap.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.studmap.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final StudentID id;
    private final TeleHandle teleHandle;
    private final GitName gitName;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Attendance> attendances = new HashSet<>();

    /**
     * Constructor using a StudentData parameter object.
     * Requires name, phone, email, address, tags to be non-null.
     *
     * @param studentData StudentData parameter object.
     */
    public Student(StudentData studentData) {
        requireAllNonNull(studentData.getId(), studentData.getGitUser(),
                studentData.getTeleHandle(), studentData.getName(), studentData.getPhone(),
                studentData.getEmail(), studentData.getAddress(),
                studentData.getTags(), studentData.getAttendances());

        this.id = studentData.getId();
        this.teleHandle = studentData.getTeleHandle();
        this.gitName = studentData.getGitUser();
        this.name = studentData.getName();
        this.phone = studentData.getPhone();
        this.email = studentData.getEmail();
        this.address = studentData.getAddress();
        this.tags.addAll(studentData.getTags());
        this.attendances.addAll(studentData.getAttendances());
    }

    public StudentID getId() {
        return id;
    }

    public GitName getGitName() {
        return gitName;
    }

    public TeleHandle getTeleHandle() {
        return teleHandle;
    }

    public Name getName() {
        return name;
    }

    public String getNameString() {
        return name.toString();
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable Attendances set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Attendance> getAttendances() {
        return Collections.unmodifiableSet(attendances);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null && otherStudent.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
                && otherStudent.getId().equals(getId())
                && otherStudent.getGitName().equals(getGitName())
                && otherStudent.getTeleHandle().equals(getTeleHandle())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getTags().equals(getTags())
                && otherStudent.getAttendances().equals(getAttendances());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, gitName, teleHandle, name, phone, email, address, tags, attendances);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName()).append("; Phone: ").append(getPhone()).append("; Email: ")
                .append(getEmail()).append("; StudentID: ")
                .append(getId()).append("; GitHub Username: ")
                .append(getGitName()).append("; TeleHandle: ").append(getTeleHandle())
                .append("; Address: ").append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Attendance> attendances = getAttendances();
        if (!attendances.isEmpty()) {
            builder.append("; Attendance: ");
            attendances.forEach(builder::append);
        }
        return builder.toString();
    }

}
