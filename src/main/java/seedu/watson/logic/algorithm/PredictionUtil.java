package seedu.watson.logic.algorithm;

import java.util.Arrays;

import seedu.watson.model.student.Attendance;
import seedu.watson.model.student.subject.Grades;

/**
 * Utility class to handle grade prediction algorithm
 */
public class PredictionUtil {

    private static final double MAXIMUM_SCORE_POSSIBLE = 100.0;

    // TEST COMMAND: predict n/Alice Pauline s/english

    /**
     * "Predicts" a student's grade for a subject based on the grades they have received so far.
     * The prediction algorithm works off a few parameters.
     * 1) Assessment difficulty is based at 1.0. The higher the assessment difficulty,
     * the higher the weightage the student's score will have to calculate the predicted
     * grade.
     * @param grades the Grade object with which to predict with
     * @param attendance the Attendance object with which to predict with
     * @return the predicted grade as a double representing the percentage
     */
    public static double predictGrade(Grades grades, Attendance attendance, double assessmentDifficulty) {
        // v5.0: use attendance to factor in amount of learning completed
        double[] rawPercents = grades.getRawPercentages();
        double[] difficulties = grades.getDifficulties();
        double[] normalizedScores = new double[rawPercents.length];
        for (int i = 0; i < rawPercents.length; i++) {
            double learningRating = getAttendanceBonus(attendance);
            normalizedScores[i] = (rawPercents[i] * 100)
                + getDifficultyBonus(difficulties[i], learningRating);
        }
        double finalScore = Arrays.stream(normalizedScores).sum() / normalizedScores.length
            + getDifficultyPenalty(assessmentDifficulty);
        // score cannot be more than 100%
        return Math.min(MAXIMUM_SCORE_POSSIBLE, finalScore);
    }

    private static double getDifficultyPenalty(double difficulty) {
        return difficulty * -1.25;
    }

    private static double getDifficultyBonus(double difficulty, double learningRating) {
        // exponential model
        if (difficulty <= 0) {
            return 5; // paper is too easy -> extra 5 marks
        }
        return Math.pow((1 / difficulty), learningRating);
    }

    private static double getAttendanceBonus(Attendance attendance) {
        double totalAttendance = Arrays.stream(attendance.getAttendanceDetails()).sum();
        int totalClasses = attendance.getAttendanceDetails().length;
        double attendancePercentage = totalAttendance / totalClasses;
        double bonus = 2;
        // arbitrary bonuses at the moment
        if (attendancePercentage <= 0.5) {
            bonus += 0.65;
        } else if (attendancePercentage <= 0.75) {
            bonus += 0.75;
        } else if (attendancePercentage <= 0.9) {
            bonus += 0.85;
        } else {
            bonus += 0.95;
        }
        return bonus;
    }
}
