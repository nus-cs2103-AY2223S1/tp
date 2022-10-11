package seedu.taassist.model.student;

/**
 * Represents a student's data for a session.
 */
public class StudentSessionData {

    private final double grade;

    public StudentSessionData(double grade) {
        this.grade = grade;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return String.format("%.2f", grade);
    }
}
