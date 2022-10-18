package seedu.address.model.person.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Represents the grades of a student stored by subject
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
        "Grades should only contain an alphabet, and it should not be blank";
    private static final String EXCEPTION_NO_GRADE_FOUND = "No grade found for assessment %s";

    private HashMap<String, double[]> marks;

    /**
     * Constructs a {@code Grade}.
     */
    public Grade() {
        marks = new HashMap<>();
    }

    public static boolean isValidGrade(String test) {
        return true;
    }

    public void updateAssessment(Assessment updatedAssessment) {
        if (marks.containsKey(updatedAssessment.getAssessmentName())) {
            double[] updatedMarks = new double[2];
            updatedMarks[0] = updatedAssessment.getAssessmentScore();
            updatedMarks[1] = updatedAssessment.getAssessmentWeightage();
            marks.put(updatedAssessment.getAssessmentName(), updatedMarks);
        } else {
            marks.put(updatedAssessment.getAssessmentName(), updatedAssessment.getScoreArray());
        }
    }

    public double getCurrentPercentageObtained(HashMap<String, double[]> subjectMarks) {
        ArrayList<double[]> totalMarksArray = new ArrayList<>();
        totalMarksArray.addAll(subjectMarks.values());
        int length = totalMarksArray.size();
        double totalMarks = 0;
        double totalWeightage = 0;
        for (int i = 0; i < length; i++) {
            totalMarks += totalMarksArray.get(i)[0] * totalMarksArray.get(i)[1];
            totalWeightage += totalMarksArray.get(i)[1];
        }
        return (totalMarks / totalWeightage);
    }

    public double getGradeForAssessment(String assessment) {
        if (marks.containsKey(assessment)) {
            return marks.get(assessment)[0];
        } else {
            throw new NoSuchElementException(String.format(EXCEPTION_NO_GRADE_FOUND, assessment));
        }
    }

    @Override
    public int hashCode() {
        return marks.hashCode();
    }

    @Override
    public String toString() {
        double currentPercentageObtained = getCurrentPercentageObtained(marks);
        return String.format("Grade: %.1f", currentPercentageObtained);
    }

}
