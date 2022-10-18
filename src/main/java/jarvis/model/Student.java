package jarvis.model;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Student in the student book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final StudentName studentName;
    private final MatricNum matricNum;

    // Data fields
    private final MasteryCheckStatus mcStatus;

    /**
     * Every field must be present and not null.
     */
    public Student(StudentName studentName, MatricNum matricNum, MasteryCheckStatus mcStatus) {
        requireAllNonNull(studentName, matricNum, mcStatus);
        this.studentName = studentName;
        this.matricNum = matricNum;
        this.mcStatus = mcStatus;
    }

    public StudentName getName() {
        return studentName;
    }

    public MatricNum getMatricNum() {
        return matricNum;
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
     * Returns true if both students have the same matriculation number.
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
        return otherStudent.getMatricNum().equals(getMatricNum());
    }

    @Override
    public int hashCode() {
        return matricNum.hashCode();
    }

    @Override
    public String toString() {
        return getName().toString();
    }

}
