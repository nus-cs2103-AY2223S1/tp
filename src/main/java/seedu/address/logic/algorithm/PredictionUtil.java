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
        // v3: use simple linear regression with the grade for x-axis
        // and the assessment "number" for y-axis
        double[] runningGradients = new double[grades.getAllAssessments().size() + 1];
        ArrayList<String> assessments = grades.getAllAssessments();
        for (int i = 1; i < assessments.size(); i++) {
            double[] gradeMatrix = grades.getGradeForAssessment(assessments.get(i));
            double[] previousGradeMatrix = grades.getGradeForAssessment(assessments.get(i - 1));
            double normalizedCurrent = (gradeMatrix[0] / gradeMatrix[1]);
            double normalizedPrevious = (previousGradeMatrix[0] / previousGradeMatrix[1]);
            double gradient = normalizedCurrent - normalizedPrevious;
            runningGradients[i] = gradient;
        }
        double averageGradient = 0;
        for (double gradient : runningGradients) {
            averageGradient += gradient;
        }
        averageGradient /= (runningGradients.length - 1);
        // y = mx + c
        // c = y - mx
        double constant = runningGradients[1] - (averageGradient * 1);
        double predictedGrade = (averageGradient * (assessments.size() + 1)) + constant;
        return predictedGrade;
    }
}
