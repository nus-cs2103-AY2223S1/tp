package seedu.address.model.person.subject;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a subject taken by the student
 */
public class Subject {

    public static final String MESSAGE_CONSTRAINTS =
        "Subjects should only contain alphabets, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z]*$";

    public final String subjectName;

    private final Attendance attendance;

    private final Grades grades;

    /**
     * Constructs a {@code Subject}.
     *
     * @param subjectName A valid subject.
     */
    public Subject(String subjectName) {
        requireNonNull(subjectName);
        checkArgument(isValidSubject(subjectName), MESSAGE_CONSTRAINTS);

        this.subjectName = subjectName;
        this.attendance = new Attendance();
        this.grades = new Grades();
    }

    /**
     * Returns true if a given string is a valid Subject.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Gets the attendance for the specified day
     * @param updatedAssessment the Assessment object to add to the Grades object
     */
    public void updateGradeAssessment(Assessment updatedAssessment) {
        grades.updateAssessment(updatedAssessment);
    }

    /**
     * Gets the grade for the specified assessment
     * @param assessmentName the assessment to get the grade for
     * @return the grade for the assessment specified
     */
    public double[] getGradeForAssessment(String assessmentName) {
        return grades.getGradeForAssessment(assessmentName);
    }

    /**
     * Gets the attendance for this subject object
     * @return the Attendance object for this subject
     */
    public Attendance getAttendance() {
        return attendance;
    }

    public Grades getGrades() {
        return grades;
    }

    public void updateAttendance(String updatedAttendance) {
        attendance.updateAttendance(updatedAttendance);
    }

    @Override
    public int hashCode() {
        return subjectName.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof Subject // instanceof handles nulls
                   && subjectName.equals(((Subject) other).subjectName)); // state check
    }

    public String dataString() {
        String str = "";
        str += subjectName + ": ";
        str += grades.dataString() + "\n";
        return str;
    }

    @Override
    public String toString() {
        return subjectName;
    }

}
