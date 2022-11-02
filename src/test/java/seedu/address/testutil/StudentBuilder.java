package seedu.address.testutil;

import java.util.ArrayList;

import seedu.address.model.person.Assignment;
import seedu.address.model.person.position.Student;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {
    public static final String DEFAULT_ATTENDANCE = "0/0";
    public static final String DEFAULT_OVERALL_GRADE = "0/0";
    public static final ArrayList<Assignment> DEFAULT_ASSIGNMENT_LIST = new ArrayList<>();
    public static final String DEFAULT_FILE_PATH = "./data/CS2103T.txt";

    private String attendance;
    private String overallGrade;
    private ArrayList<Assignment> assignmentsList;
    private String filePath;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        attendance = DEFAULT_ATTENDANCE;
        overallGrade = DEFAULT_OVERALL_GRADE;
        assignmentsList = DEFAULT_ASSIGNMENT_LIST;
        filePath = DEFAULT_FILE_PATH;
    }

    /**
     * Sets the {@code attendance} of the {@code Student} that we are building.
     */
    public StudentBuilder withAttendance(String attendance) {
        this.attendance = attendance;
        return this;
    }

    /**
     * Sets the {@code assignmentsList} of the {@code Student} that we are building.
     */
    public StudentBuilder withAssignmentsList(ArrayList<Assignment> assignmentsList) {
        this.assignmentsList = assignmentsList;
        return this;
    }

    public Student build() {
        return new Student(filePath);
    }
}
