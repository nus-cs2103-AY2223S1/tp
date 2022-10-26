package seedu.address.logic.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import seedu.address.model.person.subject.Grades;

/**
 * Utility class to handle grade prediction algorithm
 */
public class PredictionUtil {

    /**
     * "Predicts" a student's grade for a subject based on the grades they have received so far.
     * The prediction algorithm works off a few parameters.
     * 1) Assessment difficulty is based at 1.0. The higher the assessment difficulty,
     * the higher the weightage the student's score will have to calculate the predicted
     * grade.
     * @param grades the Grade object with which to predict with
     * @return the predicted grade as a double representing the percentage
     */
    public static double predictGrade(Grades grades) {
        // v4.0: add in assessment difficulty heuristic to give assessment
        // scores an arbitrary weightage
        double[] rawPercents = grades.getRawPercentages();
        double[] difficulties = grades.getDifficulties();
        double[] normalizedScores = new double[rawPercents.length];
        for (int i = 0; i < rawPercents.length; i++) {
            normalizedScores[i] = (rawPercents[i] * 100) + getDifficultyBonus(difficulties[i]);
        }
        return Arrays.stream(normalizedScores).sum() / normalizedScores.length;
    }

    private static double getDifficultyBonus(double difficulty) {
        // Initial model: y = 2 * difficulty + Math.PI
        return 2 * difficulty + Math.PI;
    }
}
