package seedu.watson.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.watson.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.watson.model.student.subject.Subject;
import seedu.watson.model.student.subject.SubjectHandler;
import seedu.watson.model.tag.Tag;

/**
 * Represents a Student in the watson book.
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

    // New fields
    private final StudentClass studentClass;
    private final Set<Remark> remarksList;
    private final SubjectHandler subjectHandler;
    private final Attendance attendance;

    /**
     * Every field must be present and not null (except attendance, remark and grade).
     */
    public Student(Name name, Phone phone, Email email, Address address,
                   StudentClass studentClass, Attendance attendance, Set<Remark> remarksList,
                   SubjectHandler subjectsTaken, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, studentClass, attendance,
                          remarksList, subjectsTaken, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);

        // Added in v1.2
        this.studentClass = studentClass;
        this.attendance = attendance;
        this.remarksList = remarksList;
        this.subjectHandler = subjectsTaken;
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

    public Address getAddress() {
        return address;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public Set<Remark> getRemarks() {
        return remarksList;
    }
    public String getRemarksString() {
        List<String> remarksStringList = new ArrayList<>();
        Iterator<Remark> remarkIterator = remarksList.iterator();
        while (remarkIterator.hasNext()) {
            remarksStringList.add(remarkIterator.next().toString());
        }
        String remarksString = String.join(", ", remarksStringList);
        return remarksString;
    }

    public SubjectHandler getSubjectHandler() {
        return subjectHandler;
    }


    public Set<Subject> getSubjectsTaken() {
        return subjectHandler.getSubjectsTaken();
    }

    // public String getGrades() {return getSubjectsTaken().getGrades();}

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void setRemarks(Set<Remark> remarksList) {
        remarksList = (remarksList != null) ? new HashSet<>(remarksList) : null;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
               && otherStudent.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, studentClass, remarksList, subjectHandler, tags);
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
               && otherStudent.getPhone().equals(getPhone())
               && otherStudent.getEmail().equals(getEmail())
               && otherStudent.getAddress().equals(getAddress())
               && otherStudent.getTags().equals(getTags())
               && otherStudent.getStudentClass().equals(getStudentClass())
               && otherStudent.getAttendance().equals(getAttendance())
               && otherStudent.getRemarks().equals(getRemarks());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
               .append("; Phone: ")
               .append(getPhone())
               .append("; Email: ")
               .append(getEmail())
               .append("; Address: ")
               .append(getAddress())
               .append("; Class: ")
               .append(getStudentClass())
               .append("; Attendance: ")
               .append(getAttendance())
               .append("; Remarks: ")
               .append(getRemarks())
               .append("; Subject: ")
               .append(getSubjectHandler());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param p the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *     is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    public int compareTo(Student p, String subjectName) {
        requireNonNull(p, subjectName);
        double thisScore = 0;
        double otherScore = 0;
        Subject thisSubject = this.getSubjectHandler().getSubject(subjectName);
        Subject otherSubject = p.getSubjectHandler().getSubject(subjectName);
        if (thisSubject != null) {
            thisScore = thisSubject.getTotalPercentage();
        }
        if (otherSubject != null) {
            otherScore = otherSubject.getTotalPercentage();
        }
        return thisScore - otherScore > 0 ? 1 : -1;
    }
}
