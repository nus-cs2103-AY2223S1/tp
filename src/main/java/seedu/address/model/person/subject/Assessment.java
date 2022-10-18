package seedu.address.model.person.subject;

public class Assessment {

    private String assessmentName;
    private double assessmentWeightage;
    private double assessmentScore;

    public Assessment(String assessmentName, double assessmentWeightage, double assessmentScore) {
        this.assessmentName = assessmentName;
        this.assessmentWeightage = assessmentWeightage;
        this.assessmentScore = assessmentScore;
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
