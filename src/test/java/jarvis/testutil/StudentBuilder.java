package jarvis.testutil;

import jarvis.model.GradeProfile;
import jarvis.model.MatricNum;
import jarvis.model.Student;
import jarvis.model.StudentName;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_MATRIC_NUM = "A0123456Z";

    private StudentName studentName;
    private MatricNum matricNum;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        studentName = new StudentName(DEFAULT_NAME);
        matricNum = new MatricNum(DEFAULT_MATRIC_NUM);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        studentName = studentToCopy.getName();
        matricNum = studentToCopy.getMatricNum();
    }

    /**
     * Sets the {@code StudentName} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.studentName = new StudentName(name);
        return this;
    }

    /**
     * Sets the {@code MatricNum} of the {@code Student} that we are building.
     */
    public StudentBuilder withMatricNum(String matricNum) {
        this.matricNum = new MatricNum(matricNum);
        return this;
    }

    /**
     * Returns the {@code Student} that we have built.
     */
    public Student build() {
        return new Student(studentName, matricNum, new GradeProfile());
    }

}
