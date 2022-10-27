package seedu.watson.logic.algorithm;

import seedu.watson.model.person.subject.Grades;

/**
 * Utility class to handle grade prediction algorithm
 */
public class PredictionUtil {

    /**
     * "Predicts" a student's grade for a subject based on the grades they have received so far.
     * @param grades the Grade object with which to predict with
     * @return the predicted grade as a double representing the percentagewa
     */
    public static double predictGrade(Grades grades) {
        double totalSum = 0;
        double totalAssessmentsTaken = 0;
        double percentageObtained = 0;
        for (String assessment : grades.getAllAssessments()) {
            double[] gradeMatrix = grades.getGradeForAssessment(assessment);
            System.out.println(grades.getGradeForAssessment(assessment));
            totalSum += gradeMatrix[0];
            totalAssessmentsTaken++;
            percentageObtained += gradeMatrix[0] / gradeMatrix[1] * gradeMatrix[2];
        }
        return totalSum / totalAssessmentsTaken;
    }
}
