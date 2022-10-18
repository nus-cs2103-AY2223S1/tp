package seedu.address.model.person.subject;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an assessment that is part of a Subject object
 */
public class Assessment {

    public static final String MESSAGE_CONSTRAINTS =
        "Assessment name should only contain letters and numbers, and it should not be blank";

    // Checks for alphanumeric characters and includes spaces after the first word.
    // Also accepts dots and underscores.
    public static final String VALIDATION_REGEX = "([\\w.]+\\s*)+";

    private String assessmentName;
    private double assessmentWeightage;
    private double assessmentScore;
    private double assessmentTotalScore;

    /**
     * Constructs an {@code Assessment} object.
     * @param assessmentName the name of the assessment
     * @param assessmentWeightage the weightage of the assessment
     * @param assessmentScore the student's score of the assessment
     * @param assessmentTotalScore the total score of the assessment
     */
    public Assessment(String assessmentName, double assessmentWeightage, double assessmentScore,
        double assessmentTotalScore) {
        checkArgument(isValidAssessment(assessmentName), MESSAGE_CONSTRAINTS);
        this.assessmentName = assessmentName;
        this.assessmentWeightage = assessmentWeightage;
        this.assessmentScore = assessmentScore;
        this.assessmentTotalScore = assessmentTotalScore;
    }

    public static boolean isValidAssessment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public double getAssessmentWeightage() {
        return assessmentWeightage;
    }

    public double getAssessmentScore() {
        return assessmentScore;
    }

    public double getAssessmentTotalScore() {
        return assessmentTotalScore;
    }

    public double[] getScoreArray() {
        return new double[]{assessmentScore, assessmentWeightage};
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public void setAssessmentWeightage(double assessmentWeightage) {
        this.assessmentWeightage = assessmentWeightage;
    }

    public void setAssessmentScore(double assessmentScore) {
        this.assessmentScore = assessmentScore;
    }

    public void setAssessmentTotalScore(double assessmentTotalScore) {
        this.assessmentTotalScore = assessmentTotalScore;
    }

    @Override
    public String toString() {
        return "ASSESSMENT NAME: " + assessmentName + "\nWEIGHTAGE: " + assessmentWeightage
            + "\nSTUDENT SCORE: " + assessmentScore + "\nTOTAL SCORE: " + assessmentTotalScore;
    }
}
