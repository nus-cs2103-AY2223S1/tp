package seedu.studmap.model.student;

import static seedu.studmap.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.studmap.model.tag.Tag;

/**
 * Represents a Student in the student map.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Attendance> attendances = new HashSet<>();

    /**
     * Constructor using a StudentData parameter object.
     * Requires name, phone, email, studmap, tags to be non-null.
     *
     * @param studentData StudentData parameter object.
     */
    public Student(StudentData studentData) {
        requireAllNonNull(studentData.getName(), studentData.getPhone(),
                studentData.getEmail(), studentData.getAddress(),
                studentData.getTags(), studentData.getAttendances());

        this.name = studentData.getName();
        this.phone = studentData.getPhone();
        this.email = studentData.getEmail();
        this.address = studentData.getAddress();
        this.tags.addAll(studentData.getTags());
        this.attendances.addAll(studentData.getAttendances());
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

    public String getPhoneString() {
        return phone.toString();
    }

    public Email getEmail() {
        return email;
    }

    public String getEmailString() {
        return email.toString();
    }

    public Address getAddress() {
        return address;
    }

    public String getAddressString() {
        return address.toString();
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

    public StudentData getStudentData() {
        StudentData studentData = new StudentData();
        studentData.setName(this.getName());
        studentData.setPhone(this.getPhone());
        studentData.setEmail(this.getEmail());
        studentData.setAddress(this.getAddress());
        studentData.setTags(this.getTags());
        studentData.setAttendances(this.getAttendances());
        return studentData;
    }

    /**
     * Returns attendance in percentage.
     */
    public float getAttendancePercentage() {
        float numOfClasses = getAttendances().size();
        float presentFor = (float) getAttendances().stream().filter(x -> x.hasAttended).count();
        return presentFor / numOfClasses * 100;
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null && otherStudent.getName().equals(getName());
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
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getTags().equals(getTags())
                && otherStudent.getAttendances().equals(getAttendances());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, attendances);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName()).append("; Phone: ").append(getPhone()).append("; Email: ")
                .append(getEmail()).append("; Address: ").append(getAddress());

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
