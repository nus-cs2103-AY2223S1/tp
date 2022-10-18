package seedu.address.model.person.subject;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class Assessment {

    public static final String MESSAGE_CONSTRAINTS =
        "Assessment name should only contain letters and numbers, and it should not be blank";

    private String assessmentName;
    private double assessmentWeightage;
    private double assessmentScore;

    public Assessment(String assessmentName, double assessmentWeightage, double assessmentScore) {
        checkArgument(isValidAssessment(assessmentName), MESSAGE_CONSTRAINTS);
        this.assessmentName = assessmentName;
        this.assessmentWeightage = assessmentWeightage;
        this.assessmentScore = assessmentScore;
    }

    public static boolean isValidAssessment(String test) {
        // TODO: replace with valid regex check that checks for alphanumeric characters
        return true;
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

    @Override
    public String toString() {
        return assessmentName + " " + assessmentWeightage + " " + assessmentScore;
    }
}
