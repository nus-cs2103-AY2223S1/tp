package jarvis.model;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Student in the student book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final StudentName studentName;
    private final MasteryCheckStatus mcStatus;

    /**
     * Every field must be present and not null.
     */
    public Student(StudentName studentName, MasteryCheckStatus mcStatus) {
        requireAllNonNull(studentName);
        this.studentName = studentName;
        this.mcStatus = mcStatus;
    }

    public StudentName getName() {
        return studentName;
    }

    public MasteryCheckStatus getMcStatus() {
        return mcStatus;
    }

    /**
     * Updates the student's mastery check status with the given mastery check result.
     */
    public void updateMcStatus(MasteryCheckResult mcResult) {
        mcStatus.updateMcResult(mcResult);
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
        return otherStudent.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentName);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        return builder.toString();
    }

}
