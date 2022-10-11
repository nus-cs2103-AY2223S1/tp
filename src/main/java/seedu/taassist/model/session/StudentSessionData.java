package seedu.taassist.model.session;

/**
 * Represents a student's data for a session.
 */
public class StudentSessionData {

    private double grade;

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
