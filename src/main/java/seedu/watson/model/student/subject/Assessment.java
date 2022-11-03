package seedu.watson.model.student.subject;

import static seedu.watson.commons.util.AppUtil.checkArgument;

/**
 * Represents an assessment that is part of a Subject object
 */
public class Assessment {

    public static final String MESSAGE_CONSTRAINTS =
        "Assessment name should only contain letters and numbers, and it should not be blank";

    public static final String MESSAGE_CONSTRAINTS_MARKS =
        "Assessment score has to be more than 0 and less than the total score";

    public static final String MESSAGE_CONSTRAINTS_TOTAL_SCORE =
        "Assessment total score has to be more than 0";

    public static final String MESSAGE_CONSTRAINTS_WEIGHTAGE =
        "Assessment weightage has to be more than 0 and less than 1";

    public static final String MESSAGE_CONSTRAINTS_DIFFICULTY =
        "Assessment difficulty has to be more than 0 and less than 5";

    // Checks for alphanumeric characters and includes spaces after the first word.
    // Also accepts dots and underscores.
    public static final String VALIDATION_REGEX = "([\\w.]+\\s*)+";

    private String assessmentName;
    private double assessmentWeightage;
    private double assessmentScore;
    private double assessmentTotalScore;

    // Can create an arbitrary formula for this
    private double assessmentDifficulty;

    /**
     * Constructs an {@code Assessment} object.
     * @param assessmentName the name of the assessment
     * @param assessmentWeightage the weightage of the assessment
     * @param assessmentScore the student's score of the assessment
     * @param assessmentTotalScore the total score of the assessment
     */
    public Assessment(String assessmentName, double assessmentScore,
        double assessmentTotalScore, double assessmentWeightage, double assessmentDifficulty) {
        checkArgument(isValidAssessment(assessmentName), MESSAGE_CONSTRAINTS);
        checkArgument(isValidTotalScore(assessmentTotalScore), MESSAGE_CONSTRAINTS_TOTAL_SCORE);
        checkArgument(isValidWeightage(assessmentWeightage), MESSAGE_CONSTRAINTS_WEIGHTAGE);
        checkArgument(isValidDifficulty(assessmentDifficulty), MESSAGE_CONSTRAINTS_DIFFICULTY);
        checkArgument(isValidScore(assessmentScore, assessmentTotalScore), MESSAGE_CONSTRAINTS_MARKS);
        this.assessmentName = assessmentName;
        this.assessmentScore = assessmentScore;
        this.assessmentTotalScore = assessmentTotalScore;
        this.assessmentWeightage = assessmentWeightage;
        this.assessmentDifficulty = assessmentDifficulty;
    }

    public static boolean isValidAssessment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if the score typed is a valid score.
     *
     * @param score
     * @param totalScore
     * @return true if the score is valid and false if it is an invalid score input.
     */
    public static boolean isValidScore(double score, double totalScore) {
        if (score >= 0 && score <= totalScore) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the score typed is a valid score.
     *
     * @param assessmentTotalScore
     * @return true if the assessment total score is valid and false if it is an invalid
     *      assessment total score input.
     */
    public static boolean isValidTotalScore(double assessmentTotalScore) {
        if (assessmentTotalScore >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the score typed is a valid score.
     *
     * @param assessmentWeightage
     * @return true if the assessment weightage is valid and false if it is an invalid
     *      assessment weightage input.
     */
    public static boolean isValidWeightage(double assessmentWeightage) {
        if (assessmentWeightage >= 0 && assessmentWeightage <= 1) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the score typed is a valid score.
     *
     * @param assessmentDifficulty
     * @return true if the assessment difficulty is valid and false if it is an invalid
     *      assessment difficulty input.
     */
    public static boolean isValidDifficulty(double assessmentDifficulty) {
        if (assessmentDifficulty >= 0 && assessmentDifficulty <= 5) {
            return true;
        }
        return false;
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

    public double getAssessmentDifficulty() {
        return assessmentDifficulty;
    }

    public double[] getScoreArray() {
        return new double[]{assessmentScore, assessmentTotalScore, assessmentWeightage, assessmentDifficulty};
    }

    @Override
    public String toString() {
        return "ASSESSMENT NAME: " + assessmentName + "\nWEIGHTAGE: " + assessmentWeightage
            + "\nSTUDENT SCORE: " + assessmentScore + "\nTOTAL SCORE: " + assessmentTotalScore;
    }
}
