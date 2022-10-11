package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the record.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Student fields
    private final Name studentName;
    private final Id id;

    // Parent fields
    private final Name parentName;
    private final Phone phone;
    private final Address address;

    // Class
    private final Class className;

    // Additional fields - may not be implemented
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name studentName, Id id, Class className, Name parentName, Phone phone, Address address, Set<Tag> tags) {
        requireAllNonNull(studentName, id, className, parentName, phone, address, tags);
        this.studentName = studentName;
        this.id = id;
        this.className = className;
        this.parentName = parentName;
        this.phone = phone;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getStudentName() {
        return studentName;
    }

    public Id getId() {
        return id;
    }

    public Class getClassName() {
        return className;
    }

    public Name getParentName() {
        return parentName;
    }

    public Phone getPhone() {
        return phone;
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
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getStudentName().equals(getStudentName());
    }

    /**
     * Returns true if both students have the same identity, class and parent fields.
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
                && otherStudent.getId().equals(getId())
                && otherStudent.getClassName().equals(getClassName())
                && otherStudent.getParentName().equals(getParentName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentName, id, className, parentName, phone, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getStudentName())
                .append("; Id: ")
                .append(getId())
                .append("; Class: ")
                .append(getClassName())
                .append("; Parent Name: ")
                .append(getParentName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
