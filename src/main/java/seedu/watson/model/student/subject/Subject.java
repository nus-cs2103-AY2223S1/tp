package seedu.watson.model.student.subject;

import static java.util.Objects.requireNonNull;
import static seedu.watson.commons.util.AppUtil.checkArgument;

import java.util.Locale;

/**
 * Represents a subject taken by the student
 */
public class Subject {

    public static final String MESSAGE_CONSTRAINTS =
        "Subjects should only contain alphabets, and it should not be blank";

    /*
     * The first character of the watson must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z]*$";

    public final String subjectName;
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
     *
     * @param updatedAssessment the Assessment object to add to the Grades object
     */
    public void updateGradeAssessment(Assessment updatedAssessment) {
        grades.updateAssessment(updatedAssessment);
    }

    /**
     * Gets the grade for the specified assessment
     *
     * @param assessmentName the assessment to get the grade for
     * @return the grade for the assessment specified
     */
    public double[] getGradeForAssessment(String assessmentName) {
        return grades.getGradeForAssessment(assessmentName);
    }

    public Grades getGrades() {
        return grades;
    }

    public double getTotalPercentage() {
        return grades.getCurrentPercentage();
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

    /**
     * Returns the subject into a String datatype to be stored in Json.
     *
     * @return a String which represents the data of the subject taken by the student.
     */
    public String dataString() {
        String str = "";
        str += subjectName.toUpperCase(Locale.ROOT) + ": ";
        str += grades.dataString() + "%%";
        return str;
    }

    @Override
    public String toString() {
        return subjectName.toUpperCase(Locale.ROOT) + ": [ " + grades.toString() + " ]";
    }

}
